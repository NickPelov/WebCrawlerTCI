import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MusicCrawler extends Crawler {

    //map to store the music items' link and title
    public HashMap<String, String> music_list = new HashMap<String, String>();

    public List<Music> music_objects = new ArrayList<>();

    //patterns for matching
    private static final Pattern H1_REGEX = Pattern.compile("<h1>(.+?)</h1>");
    private static final Pattern TD_REGEX = Pattern.compile("<td>(.+?)</td>");
    private static final Pattern TH_REGEX = Pattern.compile("<th>(.+?)</th>");

    public MusicCrawler(String website_current_page, String website_root) throws IOException {
        super(website_current_page, website_root);
        //setUpTime(0);
        //super.setNumberOfPagesCrawled(1);
        /**
         * no need for calling th method once more as it is called in the master(super)
         */
        //this.setPageContent(this.crawlHTML(this.getCurrentPage()));
        String[] items = extractLinks(this.getPageContent(),"<ulclass=\"items\">");
        this.addItemsToHashMap(items);

        //looping thought the hash map and foreach iteration inflating an object
        Set set = music_list.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {

            Map.Entry m_entry = (Map.Entry) iterator.next();
            music_objects.add(inflateObject(super.getWebsiteRootLink() + m_entry.getValue().toString()));
        }

    }

    /**
     * used to search for the items' tag and extract the inner info
     * @param content String
     * @return Array with the items
     */
    public String[] extractLinks(String content,String start_tag){
        //removing all spaces and new lines
        String page = content.replaceAll("\\s+", "");

        // getting the index of the items wrapping element
        int index_of_items_start_tag = page.indexOf(start_tag);

        //removing everything before the wrapping element
        page = page.substring(index_of_items_start_tag, page.length() - 1);

        // indexing the closing ul tag of the items
        int closing_tag = page.indexOf("</ul>");
        //removing everything after the list
        page = page.substring(0, closing_tag);

        //splitting the items
        return page.split("<li>");
    }

    /**
     * extracting all the links with their titles for all the items in the page
     * @param item_array array to extract information from
     */
    public void addItemsToHashMap(String[] item_array){
        //starting from the 1st items because when splitting on the <li> tag the leading ul tag is left as item 0
        for (int i = 1; i < item_array.length; i++) {
            //getting the href of the element
            String music_link = item_array[i].substring(item_array[i].indexOf("<"), item_array[i].indexOf(">") + 1);
            // splitting the string by ' as it is used to wrap the link
            String[] music_link_split_string = music_link.split("'");
            //reassigning the link
            music_link = music_link_split_string[1];

            //removing the link from the string
            String new_string = item_array[i].replace(music_link, "");

            //getting the alt tag which includes the name
            String music_name = new_string.substring(new_string.indexOf("<"), new_string.indexOf("/>"));
            music_name = music_name.substring(music_name.indexOf("alt"), music_name.length() - 1);
            //splitting the alt tag by '
            String[] music_name_split_string = music_name.split("'");
            //getting the name
            music_name = music_name_split_string[music_name_split_string.length - 1];

            //adding the new music to the hash map
            music_list.put(music_name, music_link);
        }
    }

    /**
     * filling the object with data taken from the passed @param URL
     *
     * @param URL string with the URL of the item
     * @return Music Object
     * @throws IOException
     */
    public Music inflateObject(String URL) throws IOException {
        super.addNumberOfPagesCrawled();

        String details = this.crawlHTML(URL);
        //getting the content of the detail box
        details = details.substring(details.indexOf("media-details"), details.length() - 1);
        details = details.substring(0, details.indexOf("</div>"));

        //assigning the matching tag values
        String title = getTagValues(details, H1_REGEX).toArray()[0].toString();

        List<String> keys = getTagValues(details, TH_REGEX);
        List<String> values = getTagValues(details, TD_REGEX);

        // adding the object to the list
        return new Music(title,
                values.toArray()[1].toString(),
                values.toArray()[2].toString(),
                Integer.parseInt(values.toArray()[3].toString()),
                values.toArray()[4].toString());
    }

    /**
     * @param str     string to be searched in
     * @param pattern regex pattern to match the string by
     * @return all the values in between the pattern, if there are multiple matching
     * patters it will return all the values in between
     */
    public List<String> getTagValues(String str, Pattern pattern) {
        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = pattern.matcher(str);
        //while there are strings which match the regex it will add the in-between values
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }

    /**
     * transforming all the data to JSON
     *
     * @return JSON Object
     */
    public String getJSON() {
        String JSON_string = "{";
        for (int i = 0; i < music_objects.size(); i++) {
            if (i > 0) {
                JSON_string += ",";
            } else {
                JSON_string += "\"music\":[";
            }
            JSON_string += music_objects.get(i).getJSON();
        }
        if (JSON_string.length() > 2) {
            JSON_string += "]";
            JSON_string += ",\"time_elapsed\":\"" + getUpTime() + "\"" + ",\"number_of_pages_crawled\":\"" + super.getNumberOfPagesCrawled() + "\"";
        }
        JSON_string += "}";
        return JSON_string;
    }

    /**
     * returning the found object in JSON format, if no item is found an empty object is returned: "{}"
     *
     * @param music_name the name to be searched for
     * @return JSON Object
     */
    public String getMusicJSON(String music_name) {
        for (Music music : music_objects
                ) {
            if (music.getTitle().equals(music_name)) {
                return music.getJSON();
            }
        }
        return "{}";
    }


    /**
     * crawling the URL
     *
     * @param URL String of the url to get the html from
     * @return returns the html found as String
     * @throws IOException
     */
    @Override
    public String crawlHTML(String URL) throws IOException {
        return super.crawlHTML(URL);
    }
}
