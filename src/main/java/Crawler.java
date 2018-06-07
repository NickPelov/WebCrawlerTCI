import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Crawler {
    private String website_current_page;
    private String page_content;
    private String website_root_link;

    public Crawler(String website_current_page, String website_root) throws IOException {
        this.setCurrentPage(website_current_page);
        this.setWebsiteRootLink(website_root);
    }
    public String getIndexContent() {
        return page_content;
    }

    public void setIndexContent(String page_content) {
        this.page_content = page_content;
    }

    public String getWebsiteRootLink() {
        return website_root_link;
    }

    public void setWebsiteRootLink(String website_root_link) {
        this.website_root_link = website_root_link;
    }

    public String getCurrentPage() {
        return website_current_page;
    }

    public void setCurrentPage(String website_current_page) {
        this.website_current_page = website_current_page;
    }

    public void crawlHTMl() throws IOException {
        String temp_index = "";
        URL oracle = new URL(this.website_current_page);
        BufferedReader in;
        in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            temp_index += inputLine + "\n";
//        System.out.println(inputLine);
        in.close();
//        adding the contencts to the content var
        page_content = temp_index;
    }

    public String getLink(String page_name) throws IOException {
        BufferedReader bufReader = new BufferedReader(new StringReader(page_content));
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            line = line.toLowerCase();
            if (line.contains(page_name.toLowerCase())&&line.contains("href")) {
//                System.out.println(line);

                int index_of_href = line.indexOf("href");
                line = line.substring(index_of_href, line.length() - 1);
//                System.out.println(line);

                line = line.substring(0, line.indexOf('>'));
//                System.out.println(line);

                line = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
//                System.out.println(line);

                return getWebsiteRootLink() + line;
            }
        }
        return null;
    }

    public int getNumberOfPagesCrawled() {
        return 42;
    }

    public float getTimeElapsed() {
        return (float) 42;
    }

    public String getSearchDepth() {
        return "42";
    }
}
