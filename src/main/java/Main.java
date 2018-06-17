import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    static String rootURL = "http://i349425.hera.fhict.nl/";
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler(rootURL+"catalog.php",rootURL);

        // Initialize movie crawler
        MovieCrawler movie = new MovieCrawler(rootURL);

        // Initialize music crawler
        String music_link = crawler.getLink("music");
        MusicCrawler music = new MusicCrawler(music_link,rootURL);

        // Initialize book crawler
        String booklink = crawler.getLink("books");
        BookCrawler book = new BookCrawler(rootURL+"catalog.php?cat=books", rootURL);
        crawlWebsite(rootURL,book,music,movie);
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
