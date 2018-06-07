import java.io.IOException;

public class BookCrawler extends Crawler {

    public BookCrawler(String website_current_page, String website_root) throws IOException {
        super(website_current_page, website_root);
    }

    @Override
    public String getIndexContent() {
        return super.getIndexContent();
    }

    @Override
    public void setIndexContent(String page_content) {
        super.setIndexContent(page_content);
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
    public void crawlHTMl() throws IOException {
        super.crawlHTMl();
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
