import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MainTests {
    @Test
    public void shouldCallCrawlers() throws IOException {
        MovieCrawler movie = mock(MovieCrawler.class);
        BookCrawler book = mock(BookCrawler.class);
        MusicCrawler music = mock(MusicCrawler.class);

        Main main = new Main();
        main.crawlWebsite("http://i349425.hera.fhict.nl/",book,music,movie);
        verify(book).getJSON();
        verify(music).getJSON();
        verify(movie).getAllMovies("http://i349425.hera.fhict.nl/");
    }
}
