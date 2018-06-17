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

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJSON() {
        String JS =
                "{\"" +
                        "name\":" + "\"" + getTitle() +
                        "\",\"genre\":\"" + getGenre() +
                        "\",\"format\":\""+ getFormat() +
                        "\",\"year\":" + getYear() +
                        ",\"authors\":\"" + getAuthors() +
                        ",\"publisher\":\"" + getPublisher() +
                        ",\"ISBN\":\"" + getISBN() +
                        "\"}";
        return JS;
    }
}

