import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://i349425.hera.fhict.nl/catalog.php","http://i349425.hera.fhict.nl/");
        MovieCrawler movieCrawler = new MovieCrawler("http://i349425.hera.fhict.nl/");
        List<Movie> movies = movieCrawler.getAllMovies("http://i349425.hera.fhict.nl/");
        System.out.println(movies);
//        System.out.println(crawler.getLink("books"));
//        System.out.println(crawler.getLink("movies"));
//        System.out.println(crawler.getLink("music"));
//        String music_link = crawler.getLink("music");

//        Crawler music_crawler = new MusicCrawler(music_link,"http://localhost/simple_site/");
//
//        System.out.println(((MusicCrawler) music_crawler).getJSON());
//        System.out.println(((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));

    }
}
