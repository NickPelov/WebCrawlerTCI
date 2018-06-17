import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookCrawlerTest {

    Crawler bookcrawler = new BookCrawler("http://i349425.hera.fhict.nl/catalog.php?cat=books", "http://i349425.hera.fhict.nl/");

    public BookCrawlerTest() throws IOException {
    }

    @Test
    public void findBookLinksTest()
    {
        List<String> links = new ArrayList<>();
        links.add("http://i349425.hera.fhict.nl/details.php?id=102");
        links.add("http://i349425.hera.fhict.nl/details.php?id=104");
        links.add("http://i349425.hera.fhict.nl/details.php?id=101");
        links.add("http://i349425.hera.fhict.nl/details.php?id=103");
        Assert.assertEquals(links, ((BookCrawler) bookcrawler).getBookLink());
    }

    @Test
    public void CreateBookFromLinksTest()
    {
        List<String> authors = new ArrayList<>();
        authors.add("Robert C. Martin");
        List<String> links = new ArrayList<>();
        links.add("http://i349425.hera.fhict.nl/details.php?id=102");
        try{
            ((BookCrawler) bookcrawler).createBooks(links);
        }
      catch (IOException e) {
          fail();
      }
        Book realbook = ((BookCrawler) bookcrawler).books.get(0);
        Book fakebook = new Book("Clean Code: A Handbook of Agile Software Craftsmanship","Tech","Ebook",2008,authors,"Prentice Hall","978-0132350884");
        Assert.assertEquals(fakebook.getTitle(),realbook.getTitle());
        Assert.assertEquals(fakebook.getFormat(),realbook.getFormat());
        Assert.assertEquals(fakebook.getGenre(),realbook.getGenre());
        Assert.assertEquals(fakebook.getISBN(),realbook.getISBN());
        Assert.assertEquals(fakebook.getTitle(),realbook.getTitle());
        Assert.assertEquals(fakebook.getYear(),realbook.getYear());
        Assert.assertEquals(fakebook.getAuthors(),realbook.getAuthors());
    }

    @Test
    public void testForTheFinalJSON()
    {
        ((BookCrawler) bookcrawler).books.clear();
        List<String> authors = new ArrayList<>();
        authors.add("Robert C. Martin");
        Book book = new Book("Clean Code: A Handbook of Agile Software Craftsmanship","Tech","Ebook",2008,authors,"Prentice Hall","978-0132350884");
        ((BookCrawler) bookcrawler).books.add(book);
        String testJSON = "\"books\":[\n" +
                "{\n" +
                "\"name\":\"Clean Code: A Handbook of Agile Software Craftsmanship\",\n" +
                "\"genre\":\"Tech\",\n" +
                "\"format\":\"Ebook\",\n" +
                "\"year\":2008,\n" +
                "\"authors\":[\n" +
                "\"Robert C. Martin\"\n" +
                "],\n" +
                "\"publisher\":\"Prentice Hall\",\n" +
                "\"ISBN\":\"978-0132350884\"\n" +
                "}\n" +
                "],\n";
        String json = ((BookCrawler) bookcrawler).getJSON();
        Assert.assertEquals(testJSON,json);
    }
}