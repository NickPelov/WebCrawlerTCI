import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    static String rootURL = "http://i349425.hera.fhict.nl/";
    public static void main(String[] args) throws IOException {
        initializeCrawlers(rootURL);
    }

    static void initializeCrawlers(String url) throws IOException {
        Crawler crawler = new Crawler(url+"catalog.php",url);

        // Initialize movie crawler
        MovieCrawler movie = new MovieCrawler(url);

        // Initialize music crawler
        String music_link = crawler.getLink("music");
        MusicCrawler music = new MusicCrawler(music_link,url);

        // Initialize book crawler
        String booklink = crawler.getLink("books");
        BookCrawler book = new BookCrawler(url+"catalog.php?cat=books", url);
        crawlWebsite(url,book,music,movie);
    }

    static void crawlWebsite(String url,BookCrawler book,MusicCrawler music, MovieCrawler movie) throws IOException {

        for (Movie m : movie.getAllMovies(url)){
            System.out.println(m.moviesToJSON());
        }
        System.out.println(book.getJSON());

        System.out.println(music.getJSON());
        System.out.println(music.getMusicJSON("Elvis Forever"));
    }
}
