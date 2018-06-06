import java.util.List;

public class Crawler {
    private List<Movie> movie_list;
    private List<Book> book_list;
    private List<Music> music_list;
    private String file_path;

    public Crawler(List<Movie> movie_list, List<Book> book_list, List<Music> music_list, String file_path) {
        this.movie_list = movie_list;
        this.book_list = book_list;
        this.music_list = music_list;
        this.file_path = file_path;
    }

    public List<Movie> getMovie_list() {
        return movie_list;
    }

    public void setMovie_list(List<Movie> movie_list) {
        this.movie_list = movie_list;
    }

    public List<Book> getBook_list() {
        return book_list;
    }

    public void setBook_list(List<Book> book_list) {
        this.book_list = book_list;
    }

    public List<Music> getMusic_list() {
        return music_list;
    }

    public void setMusic_list(List<Music> music_list) {
        this.music_list = music_list;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void crawlHTMl(){

    }

    public int getNumberOfPagesCrawled(){
        return 42;
    }

    public float getTimeElapsed(){
        return (float) 42;
    }
    public String getSearchDepth(){
        return "42";
    }
}
