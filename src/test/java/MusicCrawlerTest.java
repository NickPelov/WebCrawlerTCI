import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MusicCrawlerTest {
    Crawler music_crawler = new MusicCrawler("http://localhost/simple_site/catalog.php?cat=music", "http://localhost/simple_site/");

    public MusicCrawlerTest() throws IOException {
    }

    @Test
    public void findMusicFail() {
        Assert.assertNotEquals("fail", "{}", ((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));
    }
    @Test
    public void findMusicFailSecond() {
        Assert.assertEquals("fail", "{}", ((MusicCrawler) music_crawler).getMusicJSON("asd"));
    }

    @Test
    public void findMusicSuccess() {
        Assert.assertEquals("fail", "{\"title\":\"Elvis Forever\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":2015,\"artist\":\"Elvis Presley\"}", ((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));
    }

}
