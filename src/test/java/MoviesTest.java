import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MoviesTest {
    public List<String> writers = new ArrayList<>();
    public List<String> stars = new ArrayList<>();

    @Test(expected = IllegalArgumentException.class)
    public void yearBelow1875ShouldThrowException(){
        writers.clear();
        stars.clear();
        writers.add("Mathew Price");
        stars.add("Mathew Price");
        Movie wrongYear = new Movie("Comedy","DVD",1870,"Roger Brown",writers,stars);
    }
    @Test(expected = IllegalArgumentException.class)
    public void noDirectorShouldThrowException(){
        writers.clear();
        stars.clear();
        writers.add("David Seagull");
        stars.add("Rico Montoya");
        Movie wrongDirector = new Movie("Comedy","DVD",1970,null,writers,stars);
    }
    @Test(expected = IllegalArgumentException.class)
    public void noWritersShouldThrowException(){
        writers.clear();
        stars.clear();
        writers.add("Mark Regal");
        stars.add("Dave Adkinson");
        Movie wrongWriters = new Movie("Comedy","DVD",1970,"Max Davids",null,stars);
    }
    @Test
    public void shouldCreateValidMovie(){
        writers.clear();
        stars.clear();
        writers.add("Mark Regal");
        writers.add("David Seagull");
        stars.add("Dave Adkinson");
        stars.add("Rico Montoya");
        Movie movie = new Movie("Comedy","DVD",1970,"Max Davids",writers,stars);
        assertEquals("Comedy",movie.getGenre());
        assertEquals("DVD",movie.getFormat());
        assertEquals(1970,movie.getYear());
        assertEquals("Max Davids",movie.getDirector());
        assertEquals(writers,movie.getWriters());
        assertEquals(stars,movie.getStars());

    }
}
