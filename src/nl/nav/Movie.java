package nl.nav;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Nav Appaiya on 11/01/16 16:32.
 */
public class Movie {

    private int movieId;
    private String title;
    private String genres;
    private float rating;


    public Movie create(List<String> data){
        return this.create(
                Integer.parseInt(data.get(0)),
                data.get(1),
                data.get(2)
        );
    }


    public Movie create(int movieId, String title, String genres) {

        this.setMovieId(movieId);
        this.setTitle(title);
        this.setGenres(genres);

        return this;
    }


    public Movie create(String str) {
        List<String> lst = Arrays.asList(str.split(","));
        return create(lst);
    }


    public Movie getMovieById (ArrayList arrayListMovies, int movieId)
    {
        for(Object object : arrayListMovies) {
            Movie movie = (Movie) object;
            if (movie.getMovieId() == movieId) {
                return movie;
            }
        }

        return null;
    }


    public int getMovieId() {
        return movieId;
    }


    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String[] getGenres() {
        String[] parts = genres.split(Pattern.quote("|"));
        return parts;
    }


    public boolean compareGenres(List<String> genres) {

        int countGenres = genres.size();
        int fountGenres = 0;

        for (String genre : genres) {
            if (Arrays.asList(this.getGenres()).contains(genre)) {
                ++fountGenres;
            }
        }

        if (countGenres == fountGenres) {
            return true;
        }

        return false;
    }


    public void setGenres(String genres) {
        this.genres = genres;
    }


    public float getRating() {
        return rating;
    }


    public void setRating(float rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Movies{");

        sb.append("movieId='").append(movieId).append('\'');
        sb.append(", title=").append(title);
        sb.append(", genres=").append(genres);
        sb.append(", rating=").append(rating);
        sb.append('}');

        return sb.toString();
    }

}
