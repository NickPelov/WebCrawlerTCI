import java.util.List;

public class Book {
    private String genre;
    private String format;
    private int year;
    private List<String> authors;
    private String publisher;
    private String ISBN;
    private String title;

    public Book(String title,String genre, String format, int year, List<String> authors, String publisher, String ISBN) {
        if (title.isEmpty()){
            throw new IllegalArgumentException("Title is required!");
        }else if (ISBN.isEmpty()) {
            throw new IllegalArgumentException("ISBN is required!");
        } else if (publisher.isEmpty()){
            throw new IllegalArgumentException("Publisher is required!");
        }

        this.title = title;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public int getYear() {
        return year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getJSON() {
        String s = "\"authors\":[\n";
        int c = 0;
        for (String a: authors) {
            if (c == 0)
            {
                s += "\"" + authors.get(c) + "\"";
            }
            else
            {
                s += ",\n" + "\"" + authors.get(c)+ "\"";
            }
            c++;
        }
        s += "\n],";
        String JS =
                "{" + "\n" +
                        "\"" + "name\":" + "\"" + getTitle() + "\",\n" +
                        "\"" + "genre\":" + "\"" + getGenre() + "\",\n" +
                        "\"" + "format\":" + "\"" + getFormat() + "\",\n" +
                        "\"" + "year\":" + getYear() + ",\n" +
                        s + "\n" +
                        "\"" + "publisher\":" + "\"" + getPublisher() + "\",\n" +
                        "\"" + "ISBN\":" + "\"" + getISBN() + "\"\n" +
                        "}\n";
        return JS;
    }
}

