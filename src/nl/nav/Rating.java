package nl.nav;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nav on 11-01-16.
 */
public class Rating {
    private int userId;
    private int movieId;
    private float rating;
    private int timestamp;


    public Rating create(List<String> data){
        return this.create(
                Integer.parseInt(data.get(0)),
                Integer.parseInt(data.get(1)),
                Float.parseFloat(data.get(2)),
                Integer.parseInt(data.get(3))
        );
    }


    public Rating create(int userId, int movieId, float rating, int timestamp) {

        this.setUserId(userId);
        this.setMovieId(movieId);
        this.setRating(rating);
        this.setTimestamp(timestamp);

        return this;
    }


    public Rating create(String str) {
        List<String> lst = Arrays.asList(str.split(","));
        return create(lst);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ratings{");

        sb.append("userId='").append(userId).append('\'');
        sb.append(", movieId='").append(movieId);
        sb.append(", rating=").append(rating);
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');

        return sb.toString();
    }
}
