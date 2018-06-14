import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Crawler crawler = new Crawler("http://localhost/simple_site/catalog.php","http://localhost/simple_site/");

        System.out.println(crawler.getLink("books"));
        System.out.println(crawler.getLink("movies"));
        System.out.println(crawler.getLink("music"));
        String music_link = crawler.getLink("music");

//        Crawler music_crawler = new MusicCrawler(music_link,"http://localhost/simple_site/");
//
//        System.out.println(((MusicCrawler) music_crawler).getJSON());
//        System.out.println(((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));

    }
}
