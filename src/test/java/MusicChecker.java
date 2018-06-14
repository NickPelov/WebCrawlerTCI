import org.junit.Assert;
import org.junit.Test;

public class MusicChecker {
    Music music = new Music("title", "genre", "format", 1999, "artist");

    @Test
    public void testMusicPropTitle() {
        Assert.assertEquals("fail", "title", music.getTitle());
    }
    @Test
    public void testMusicPropGenre() {
        Assert.assertEquals("fail", "genre", music.getGenre());
    }
    @Test
    public void testMusicPropFormat() {
        Assert.assertEquals("fail", "format", music.getFormat());
    }
    @Test
    public void testMusicPropYear() {
        Assert.assertEquals("fail", 1999, music.getYear());
    }
    @Test
    public void testMusicPropArtist() {
        Assert.assertEquals("fail", "artist", music.getArtist());
    }

    @Test
    public void testJson() {
        String JSON = "{\"title\":\"title\",\"genre\":\"genre\",\"format\":\"format\",\"year\":1999,\"artist\":\"artist\"}";
        String result_JSON = music.getJSON();
        Assert.assertEquals("fail", JSON, result_JSON);
    }
}
