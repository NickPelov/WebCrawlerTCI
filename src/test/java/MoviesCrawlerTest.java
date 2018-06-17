import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.List;

public class MoviesCrawlerTest {
    private String rootURI = "http://i349425.hera.fhict.nl/";
    private Integer numberOfLinkOnBaseURI = 9;
    private Integer numberOfLinkOutsideBaseURI = 0;
    private MovieCrawler crawler = new MovieCrawler(rootURI);

    @Test
    public void shouldSetBaseURI(){
        MovieCrawler crawler = new MovieCrawler(rootURI);
        assertEquals(rootURI,crawler.root);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWithWrongURI(){
        MovieCrawler crawler = new MovieCrawler("Not a URI");
    }

    @Test
    public void shouldRetrieveLinks(){
        MovieCrawler crawler = new MovieCrawler(rootURI);
        Integer links = 0;
        try {
            links = crawler.getLinksFromPage(rootURI).size();
        } catch (IOException e) {
            fail();
        }
        assertEquals(numberOfLinkOnBaseURI,links);
    }

    @Test
    public void shouldIgnoreNonDomainLinks(){
        Integer links = 0;
        try {
            links = crawler.getLinksFromPage("https://www.facebook.com/").size();
        } catch (IOException e) {
            fail();
        }
        assertEquals(numberOfLinkOutsideBaseURI,links);
    }

    @Test
    public void shouldAddPageToCrawl(){
        crawler.addPageToCrawl("http://i349425.hera.fhict.nl/catalog.php?cat=books");
        assertEquals(1,crawler.pagesToCrawl.size());
    }

    @Test
    public void shouldAddVisitedPages(){
        crawler.addVisitedPage("http://i349425.hera.fhict.nl/catalog.php?cat=books");
        assertEquals(1,crawler.pagesCrawled.size());
    }

    @Test
    public void shouldFindAMovie(){
        Movie m = null;
        try {
            m = crawler.searchMovie("http://i349425.hera.fhict.nl/details.php?id=203");
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
        Assert.assertNotNull(m);
    }

    @Test
    public void shouldNotFindAMovie(){
        Movie m = null;
        try {
            m = crawler.searchMovie("http://i349425.hera.fhict.nl/");
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
        Assert.assertNull(m);
    }

    @Test
    public void shouldFindABookAndNotRetrieveIt(){
        Movie m = null;
        try {
            m = crawler.searchMovie("http://i349425.hera.fhict.nl/details.php?id=103");
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
        Assert.assertNull(m);
    }

    @Test
    public void shouldAddMovieIfFound(){
        try {
            crawler.parseURL(rootURI);
            assertTrue(crawler.pagesToCrawl.isEmpty());
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
    }

    @Test
    public void shouldReturnAllMoviesOnAWebsite(){
        List<Movie> movies;
        try {
            movies = crawler.getAllMovies(rootURI);
            assertFalse(movies.isEmpty());
            assertEquals(4,movies.size());
        } catch (IOException e) {
            fail("Exception: "+ e.toString());
        }
    }
}
