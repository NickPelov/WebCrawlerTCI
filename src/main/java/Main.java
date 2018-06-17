import java.io.Console;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://i349425.hera.fhict.nl//catalog.php","http://i349425.hera.fhict.nl/");

        System.out.println(crawler.getLink("books"));
        System.out.println(crawler.getLink("movies"));
        System.out.println(crawler.getLink("music"));
        String booklink = crawler.getLink("books");

        Crawler bookcrawler = new BookCrawler("http://i349425.hera.fhict.nl/catalog.php?cat=books", "http://i349425.hera.fhict.nl/");

//        Crawler book = new Crawler(booklink,"http://i349425.hera.fhict.nl/")
//        System.out.println(book.getPageContent());

//        Crawler music_crawler = new MusicCrawler(music_link,"http://localhost/simple_site/");
//
//        System.out.println(((MusicCrawler) music_crawler).getJSON());
//        System.out.println(((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));

    }
}
