import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class MovieCrawler {
    public  String root;

    public Set<String> pagesCrawled = new HashSet<>();
    public List<String> pagesToCrawl = new LinkedList<>();
    private List<Movie> moviesFound = new ArrayList<>();

    MovieCrawler(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new IllegalArgumentException("URL does not exist");
        }
        root = doc.baseUri();
    }

    public List<String> getLinksFromPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("abs:href");
            System.out.println("Parse" + linkHref);
            this.addPageToCrawl(linkHref);
        }
        return pagesToCrawl;
    }

    public void addPageToCrawl(String linkHref) {
        linkHref = linkHref.toLowerCase();
        if (!linkHref.contains(root)) return;
        if (pagesToCrawl.contains(linkHref) || pagesCrawled.contains(linkHref)) return;
        pagesToCrawl.add(linkHref);
        System.out.println("Page added to list: " + linkHref);
    }

    public void addVisitedPage(String linkHref) {
        linkHref = linkHref.toLowerCase();
        if (!linkHref.contains(root)) return;
        pagesCrawled.add(linkHref);
    }

    public Movie searchMovie(String url) throws IOException {
        Movie movie;
        String genre = "";
        String format = "";
        String year = "";
        String director = "";
        List<String> writers = new ArrayList<>();
        List<String> stars = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Element content = doc.getElementById("content");
        if (url.contains("id=")){
            Element details = content.select("table").first();
            Elements rows = details.getElementsByTag("tr");
            for (Element row : rows) {
                Element header = row.getElementsByTag("th").first();
                Element data = row.getElementsByTag("td").first();
                if (header.text().contains("Genre")) genre = data.text();
                if (header.text().contains("Format")) format = data.text();
                if (header.text().contains("Year")) year = data.text();
                if (header.text().contains("Director")) director = data.text();
                if (header.text().contains("Writers")) writers = Arrays.asList(data.text().split(","));
                if (header.text().contains("Stars")) stars = Arrays.asList(data.text().split(","));
            }
        }
        try {
            movie = new Movie(genre,format,Integer.parseInt(year),director,writers,stars);
        } catch (Exception e) {
            movie = null;
        }
        return movie;
    }

    public void parseURL(String url) throws IOException {
        this.addVisitedPage(url);
        this.getLinksFromPage(url);
        Movie movie = this.searchMovie(url);
        if (movie != null) moviesFound.add(movie);
        if (this.pagesToCrawl.isEmpty()) return ;
        this.parseURL(this.pagesToCrawl.remove(0));
    }

    public List<Movie> getAllMovies(String url) throws IOException {
        this.parseURL(url);
        return this.moviesFound;
    }

}
