import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookTest {
    public List<String> authors = new ArrayList<>();

    @Test(expected = IllegalArgumentException.class)
    public void missingPublisherException(){
        authors.clear();
        authors.add("Erich Gamma");
        authors.add("Richard Helm");
        Book noPublisher = new Book("foo","Tech", "Paperback", 1994, authors, "", "978-0201633610");
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingTitleException(){
        authors.clear();
        authors.add("Erich Gamma");
        authors.add("Richard Helm");
        Book noPublisher = new Book("","Tech", "Paperback", 1994, authors, "Prentice Hall", "978-0201633610");
    }

    @Test(expected = IllegalArgumentException.class)
    public void missingISBNException(){
        authors.clear();
        authors.add("Erich Gamma");
        authors.add("Richard Helm");
        Book noPublisher = new Book("foo","Tech", "Paperback", 1994, authors, "Prentice Hall", "");
    }

    @Test
    public void createMovieTest(){
        authors.add("Erich Gamma");
        authors.add("Richard Helm");
        authors.add("Ralph Johnson");
        authors.add("John Vlissides");
        Book book = new Book("A Design Patterns: Elements of Reusable Object-Oriented Software","Tech","Paperback",1994 ,authors,"Prentice Hall", "978-0201633610");
        assertEquals("A Design Patterns: Elements of Reusable Object-Oriented Software",book.getTitle());
        assertEquals(1994,book.getYear());
        assertEquals("Tech",book.getGenre());
        assertEquals("Paperback",book.getFormat());
        assertEquals(authors,book.getAuthors());
        assertEquals("Prentice Hall",book.getPublisher());
        assertEquals("978-0201633610",book.getISBN());
    }

    @Test
    public void jsonTest(){
        authors.add("Erich Gamma");
        Book book = new Book("foo","Tech","Paperback",1994 ,authors,"Prentice Hall", "978-0201633610");
        String JS =
                "{\"" +
                        "name\":" + "\"" +"foo"+
                        "\",\"genre\":\"" + "Tech" +
                        "\",\"format\":\""+ "Paperback" +
                        "\",\"year\":" + "1994" +
                        ",\"authors\":\"" + "Erich Gamma" +
                        ",\"publisher\":\"" + "Prentice Hall" +
                        ",\"ISBN\":\"" + "978-0201633610" +
                        "\"}";
        String bookJS = book.getJSON();
        assertEquals(JS,bookJS);
    }
}