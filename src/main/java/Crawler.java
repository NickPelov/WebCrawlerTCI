import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

public class Crawler {
    private String website_current_page;
    private String page_content;
    private String website_root_link;
    public int number_of_pages_crawled = 0;
    Thread timer;
    private float up_time;

    public Crawler(String website_current_page, String website_root) throws IOException {
        this.setCurrentPage(website_current_page);
        this.setWebsiteRootLink(website_root);
        up_time = 0;
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    up_time += 0.001;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        timer.start();
        this.setPageContent(this.crawlHTML(this.website_current_page));
    }

    public int getNumberOfPagesCrawled() {

        return number_of_pages_crawled;
    }

    public void setNumberOfPagesCrawled(int number_of_pages_crawled) {
        this.number_of_pages_crawled = number_of_pages_crawled;
    }

    public void addNumberOfPagesCrawled() {
        this.number_of_pages_crawled += 1;
    }

    public float getUpTime() {
        return up_time;
    }

    public String getPageContent() {
        return page_content;
    }

    public void setPageContent(String page_content) {
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

    public String crawlHTML(String URL) throws IOException {
        addNumberOfPagesCrawled();
        String temp_index = "";
        URL oracle = new URL(URL);
        BufferedReader in;
        in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            temp_index += inputLine + "\n";
        // System.out.println(inputLine);
        in.close();
        // adding the contencts to the content var
        return temp_index;
    }

    public String getLink(String page_name) throws IOException {
        BufferedReader bufReader = new BufferedReader(new StringReader(page_content));
        String line = null;
        while ((line = bufReader.readLine()) != null) {
            line = line.toLowerCase();
            if (line.contains(page_name.toLowerCase()) && line.contains("href")) {
//                System.out.println(line);

                int index_of_href = line.indexOf("href");
                line = line.substring(index_of_href, line.length() - 1);
//                System.out.println(line);

                line = line.substring(0, line.indexOf('>'));
//                System.out.println(line);

                line = line.substring(line.indexOf('\"') + 1, line.lastIndexOf('\"'));
//                System.out.println(line);

                System.out.println("link found at : " + up_time + " sec");
                return getWebsiteRootLink() + line;
            }
        }
        return null;
    }
}
