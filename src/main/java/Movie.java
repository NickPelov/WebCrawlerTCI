import java.util.List;

public class Movie {

    private String genre;
    private String format;
    private int year;
    private String director;
    private List<String> writers;
    private List<String> stars;

    @Override
    public String toString() {
        return "Movie{" +
                "genre='" + genre + '\'' +
                ", format='" + format + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", writers=" + writers +
                ", stars=" + stars +
                '}';
    }

    public Movie(String genre, String format, int year, String director, List<String> writers, List<String> stars) {
        if (genre == null || format == null || director == null || writers == null || stars == null) {
            throw new IllegalArgumentException("Null is not an accepted value!");
        }
        if (director.isEmpty()){
            throw new IllegalArgumentException("Director is required when creating a movie");
        }else if ( writers.isEmpty()) {
            throw new IllegalArgumentException("At least 1 writer should be specified!");
        } else if (year < 1874){
            throw new IllegalArgumentException(year + " is not a valid year. The first movie was created in 1874");
        }

        this.genre = genre;
        this.format = format;
        this.year = year;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
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

    public String getDirector() {
        return director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getStars() {
        return stars;
    }

}

