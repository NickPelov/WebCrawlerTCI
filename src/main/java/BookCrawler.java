import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookCrawler extends Crawler {

    String[] links;
    List<Book> books = new ArrayList<>();

    public BookCrawler(String website_current_page, String website_root) throws IOException {
        super(website_current_page, website_root);

        MusicCrawler Bookcr = new MusicCrawler(website_current_page, website_root);
        links = Bookcr.extractLinks(this.getPageContent(),"<ulclass=\"items\">");
        /**
         * Using already created method extractLinks, so the redundant code is less
         */

        createBooks(getBookLink());

        System.out.println(getJSON());
    }

    /**
     * used to get the links of all of the books on the website
     * @return Array with the links of the books
     */
    public List<String> getBookLink()
    {
        List<String> l = new ArrayList<>();
        int t = 0;
        for (String s : links) {
            if (t != 0) {
                int startindex = s.indexOf("'");
                int endindex = s.indexOf(">");
                s = s.substring(startindex, endindex);
                s = s.substring(1, s.length()-1);
                s = getWebsiteRootLink() + s;
                l.add(s);
            }
            t++;
        }
        return l;
    }

    /**
     * used to go on each of the links and creating a book object with the information from the web page
     * @param links List<String> contains all of the links for the books
     */
    public void createBooks(List<String> links) throws IOException
    {
        for (String s:links) {
            super.addNumberOfPagesCrawled();

            String t = "";
            String g = "";
            String f = "";
            String y = "";
            String a = "";
            String p = "";
            String is = "";
            String[] auth;

            Document doc = Jsoup.connect(s).get();
            Elements title = doc.select("div.media-details").select("h1");
            Elements items = doc.select("table").select("td");
            for (Element h1 : title) {
                t = h1.text();
            }
            for (int i = 0; i < 7; i++) {
                g = items.get(1).text();
                f = items.get(2).text();
                y = items.get(3).text();
                a = items.get(4).text();
                p = items.get(5).text();
                is = items.get(6).text();
            }
            auth = a.split(", ");
            books.add(new Book(t,g,f,Integer.parseInt(y),Arrays.asList(auth),p,is));

        }
//        for (Book b:books) {
//            System.out.println(b.getTitle());
//            System.out.println(b.getGenre());
//            System.out.println(b.getFormat());
//            System.out.println(b.getYear());
//            System.out.println(b.getAuthors());
//            System.out.println(b.getPublisher());
//            System.out.println(b.getISBN());
//        }
    }

    /**
     * used to create the JSON format from all of the books
     * @return String with a JSON format
     */
    public String getJSON()
    {

        String s = "\"books\":[\n";
        for (Book b:books) {
            s += b.getJSON();
        }
        s += "],\n";
        return s;
    }
    @Override
    public String getPageContent() {
        return super.getPageContent();
    }

    @Override
    public void setPageContent(String page_content) {
        super.setPageContent(page_content);
    }

    @Override
    public String getWebsiteRootLink() {
        return super.getWebsiteRootLink();
    }

    @Override
    public void setWebsiteRootLink(String website_root_link) {
        super.setWebsiteRootLink(website_root_link);
    }

    @Override
    public void setCurrentPage(String website_current_page) {
        super.setCurrentPage(website_current_page);
    }

    @Override
    public String crawlHTML(String URL) throws IOException {
        return super.crawlHTML(URL);
    }


}
