import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://localhost/simple_site/catalog.php","http://localhost/simple_site/");

        crawler.crawlHTMl();
        System.out.println(crawler.getLink("books"));
        System.out.println(crawler.getLink("movies"));
        System.out.println(crawler.getLink("music"));
    }
}
