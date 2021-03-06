import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MoviesTest {
    public List<String> writers = new ArrayList<>();
    public List<String> stars = new ArrayList<>();

    @Test(expected = IllegalArgumentException.class)
    public void nullValuesShouldNotBeAccepted(){
        Movie wrongYear = new Movie(null,null,null,1870,null,null,null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void yearBelow1875ShouldThrowException(){
        writers.clear();
        stars.clear();
        writers.add("Mathew Price");
        stars.add("Mathew Price");
        Movie wrongYear = new Movie("Lord of Destruction","Comedy","DVD",1870,"Roger Brown",writers,stars);
    }
    @Test(expected = IllegalArgumentException.class)
    public void noDirectorShouldThrowException(){
        writers.clear();
        stars.clear();
        writers.add("David Seagull");
        stars.add("Rico Montoya");
        Movie wrongDirector = new Movie("Harry Potter","Comedy","DVD",1970,"",writers,stars);
    }
    @Test(expected = IllegalArgumentException.class)
    public void noWritersShouldThrowException(){
        writers.clear();
        stars.clear();
        stars.add("Dave Adkinson");
        Movie wrongWriters = new Movie("Warcraft","Comedy","DVD",1970,"Max Davids",writers,stars);
    }
    @Test
    public void shouldCreateValidMovie(){
        writers.clear();
        stars.clear();
        writers.add("Mark Regal");
        writers.add("David Seagull");
        stars.add("Dave Adkinson");
        stars.add("Rico Montoya");
        Movie movie = new Movie("Warcraft","Comedy","DVD",1970,"Max Davids",writers,stars);
        assertEquals("Warcraft",movie.getTitle());
        assertEquals("Comedy",movie.getGenre());
        assertEquals("DVD",movie.getFormat());
        assertEquals(1970,movie.getYear());
        assertEquals("Max Davids",movie.getDirector());
        assertEquals(writers,movie.getWriters());
        assertEquals(stars,movie.getStars());

    }

    @Test
    public void shouldReturnJSONFormat(){
        writers.clear();
        stars.clear();
        writers.add("Mark Regal");
        writers.add("David Seagull");
        stars.add("Dave Adkinson");
        stars.add("Rico Montoya");
        Movie movie = new Movie("Warcraft","Comedy","DVD",1970,"Max Davids",writers,stars);
        assertTrue(!movie.moviesToJSON().isEmpty());
    }

    @Test
    public void shouldReturnString(){
        writers.clear();
        stars.clear();
        writers.add("Leslie Regal");
        writers.add("Bill Seagull");
        stars.add("Carl Adkinson");
        stars.add("Mark Regal");
        Movie movie = new Movie("Warcraft","Comedy","DVD",1970,"Max Davids",writers,stars);
        assertTrue(!movie.toString().isEmpty());
    }
}
