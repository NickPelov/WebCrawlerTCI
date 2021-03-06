import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class MusicCrawlerTest {
    Crawler music_crawler = new MusicCrawler("http://i349425.hera.fhict.nl/catalog.php?cat=music", "http://i349425.hera.fhict.nl/");

    Crawler crawler = new Crawler("http://i349425.hera.fhict.nl/catalog.php","http://i349425.hera.fhict.nl/");

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
        Assert.assertEquals("fail", "{\"title\":\"Elvis Forever\",\"genre\":\"Rock\",\"format\":\"Vinyl\",\"year\":2015,\"artist\":\"Elvis Presley\"}",
                ((MusicCrawler) music_crawler).getMusicJSON("Elvis Forever"));
    }

    @Test
    public void inflateMusicObjectTest() throws IOException {
        Music music_obj = new Music("The Very Thought of You","Jaz","MP3",2008,"Nat King Cole");
        Music extr_obj = ((MusicCrawler) music_crawler).inflateObject("http://i349425.hera.fhict.nl/details.php?id=304");

        Assert.assertEquals("fail",music_obj.getJSON() ,extr_obj.getJSON());
    }
    @Test
    public void extractTagInfoTest(){
        Pattern TITLE_ASD_REGEX = Pattern.compile("<asd>(.+?)</asd>");

        String string_to_extract_from = "<h1>asd</h1><div>asd<div>asd</div><div>asdasd<div>asd asd<div><asd>content</asd>asd</div></div></div><div>asd asd</div></div>";

        List<String> content = ((MusicCrawler)music_crawler).getTagValues(string_to_extract_from,TITLE_ASD_REGEX);

        List<String> correct_content = new ArrayList<>();
        correct_content.add("content");

        Assert.assertEquals("fail",correct_content.get(0),content.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void extractTagInfoFailTest(){
        Pattern TITLE_ASD_REGEX = Pattern.compile("<asd>(.+?)</asd>");

        String string_to_extract_from = "<h1>asd</h1><div>asd<div>asd</div><div>asdasd<div>asd asd<div><asd></asd>asd</div></div></div><div>asd asd</div></div>";

        List<String> empty_content = ((MusicCrawler)music_crawler).getTagValues(string_to_extract_from,TITLE_ASD_REGEX);

        List<String> empty_correct_content = new ArrayList<>();
        empty_correct_content.add("content");

        Assert.assertNotEquals("fail",empty_correct_content.get(0),empty_content.get(0));
    }
    @Test
    public void getPageLinks(){
        String[] array = new String[5];
        array[0] = "<ulclass=\"items\">";
        array[1] = "<ahref='details.php?id=301'><imgsrc='img/media/beethoven.jpg'alt='Beethoven:CompleteSymphonies'/><p>ViewDetails</p></a></li>";
        array[2] = "<ahref='details.php?id=302'><imgsrc='img/media/elvis_presley.jpg'alt='ElvisForever'/><p>ViewDetails</p></a></li>";
        array[3] = "<ahref='details.php?id=303'><imgsrc='img/media/garth_brooks.jpg'alt='NoFences'/><p>ViewDetails</p></a></li>";
        array[4] = "<ahref='details.php?id=304'><imgsrc='img/media/nat_king_cole.jpg'alt='TheVeryThoughtofYou'/><p>ViewDetails</p></a></li>";

        String[] test_array = ((MusicCrawler)music_crawler).extractLinks(((MusicCrawler)music_crawler).getPageContent(),"<ulclass=\"items\">");

        Assert.assertArrayEquals(array,test_array);
    }
    @Test
    public void shouldFindALink(){
        String link_string = null;
        try {
            link_string = crawler.getLink("music");
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
        Assert.assertNotNull(link_string);
    }
    @Test
    public void shouldNotFindALinkMock(){
        Crawler crawler = mock(Crawler.class);
        String link_string = null;
        try {
            link_string = crawler.getLink("music");
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
        Assert.assertNull(link_string);
    }
    @Test
    public void getCurrentLink(){
        String current_link = "http://i349425.hera.fhict.nl/catalog.php?cat=music";
        String expected_link = music_crawler.getCurrentPage();
        Assert.assertEquals("fail",expected_link,current_link);
    }
    @Test
    public void getNumberOfPagesTest(){
        int current_link = 9;
        int expected_link = music_crawler.getNumberOfPagesCrawled();
        Assert.assertEquals("fail",expected_link,current_link);
    }
    @Test
    public void setNumberOfPagesTest(){
        int current_link = 0;
        music_crawler.setNumberOfPagesCrawled(0);
        Assert.assertEquals("fail",music_crawler.getNumberOfPagesCrawled(),current_link);
    }
    @Test
    public void getJsonObjectsTest(){
        String json = ((MusicCrawler)music_crawler).getJSON();
        Assert.assertNotNull(json);
    }
}
