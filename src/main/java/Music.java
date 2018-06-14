public class Music {
    private String title;
    private String genre;
    private String format;
    private int year;
    private String artist;

    public Music(String title, String genre, String format, int year, String artist) {
        this.setTitle(title);
        this.setGenre(genre);
        this.setFormat(format);
        this.setYear(year);
        this.setArtist(artist);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getJSON() {
        String JSON_string =
                "{\"" +
                "title\":" + "\"" + getTitle() +
                "\",\"genre\":\"" + getGenre() +
                "\",\"format\":\""+ getFormat() +
                "\",\"year\":" + getYear() +
                ",\"artist\":\"" + getArtist() +
                "\"}";
        return JSON_string;
    }
}
