import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookCrawler extends Crawler {

    String[] links;
    String root;
    public BookCrawler(String website_current_page, String website_root) throws IOException {
        super(website_current_page, website_root);

        root = website_root;
        MusicCrawler Bookcr = new MusicCrawler(website_current_page, website_root);
        links = Bookcr.extractLinks(this.getPageContent(),"<ulclass=\"items\">");
        getInfo();
    }

    public List<String> getBookLink()
    {
        List<String> l = new ArrayList<String>();
        int t = 0;
        for (String s : links) {
            if (t != 0) {
                int startindex = s.indexOf("'");
                int endindex = s.indexOf(">");
                //removing everything before the wrapping element
                s = s.substring(startindex, endindex);
                s = s.substring(1, s.length()-1);
                s = root + s;
                l.add(s);
            }
            t++;
        }
        return l;
    }

    public void getInfo() throws IOException
    {
        for (String s:getBookLink()) {
            super.addNumberOfPagesCrawled();
            String content = this.crawlHTML(s);

            content = content.substring(content.indexOf("<h1>"), content.length() - 1);
            content = content.substring(0, content.indexOf("</table>"));

            System.out.println(content);

            
        }
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
    public String getCurrentPage() {
        return super.getCurrentPage();
    }

    @Override
    public void setCurrentPage(String website_current_page) {
        super.setCurrentPage(website_current_page);
    }

    @Override
    public String crawlHTML(String URL) throws IOException {
        return super.crawlHTML(URL);
    }

    @Override
    public String getLink(String page_name) throws IOException {
        return super.getLink(page_name);
    }

    @Override
    public int getNumberOfPagesCrawled() {
        return super.getNumberOfPagesCrawled();
    }

    @Override
    public float getTimeElapsed() {
        return super.getTimeElapsed();
    }

    @Override
    public String getSearchDepth() {
        return super.getSearchDepth();
    }

}
