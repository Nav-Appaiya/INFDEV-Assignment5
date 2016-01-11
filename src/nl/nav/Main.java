package nl.nav;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Nav Appaiya on 11/01/16 16:02.
 */
public class Main {

    static ArrayList arrayListMovies;
    static ArrayList arrayListRatings;

    public static void main(String[] args) {


        BufferedReader bufferedReaderMovies = getFileReader("movies.csv");
        arrayListMovies = (ArrayList) getMoviesCollection(bufferedReaderMovies);

        BufferedReader bufferedReaderRatings = getFileReader("movies.csv");
        arrayListRatings = (ArrayList) getRatingsCollection(bufferedReaderRatings);

        countRatingsForMovies();

        // opdr. a
        System.out.println("All films with start letter P: ");
        System.out.println(titleStartsWithLetter("P"));


        System.out.println(System.getProperty("line.separator"));


        // opdr. b
        System.out.println(hasGenre("Drama"));


        System.out.println(System.getProperty("line.separator"));


        // opdr. c
        List<String> searchGenres = new ArrayList();;
        searchGenres.add("Drama");
        searchGenres.add("Romance");
        searchGenres.add("Musical");
        System.out.println(hasGenres(searchGenres));


        // opdr. d
        System.out.println(hasMinimumRating(1040.5f));


        System.out.println(System.getProperty("line.separator"));


        // opdr. e
        // No idea, how to implement :(

    }


    /**
     * titleStartsWithLetter
     * @param start
     * @return
     */
    public static List<Movie> titleStartsWithLetter(String start) {
        List<Movie> moviesList =
                (List) arrayListMovies
                        .stream()
                        .filter(m -> ((Movie)m).getTitle().startsWith(start))
                        .collect(Collectors.toList());

        return moviesList;
    }


    /**
     * hasGenre
     * @param genre
     * @return
     */
    public static List<Movie> hasGenre(String genre) {
        List<Movie> moviesList =
                (List) arrayListMovies
                        .stream()
                        .filter(m -> (Arrays.asList(((Movie)m).getGenres()).contains(genre)))
                        .collect(Collectors.toList());

        return moviesList;
    }


    /**
     * hasGenres
     * @param genres
     * @return
     */
    public static List<Movie> hasGenres(List<String> genres) {
        List<Movie> moviesList =
                (List) arrayListMovies
                        .stream()
                        .filter(m -> (((Movie)m).compareGenres(genres)))
                        .collect(Collectors.toList());

        return moviesList;
    }


    /**
     * hasMinimumRating
     * @param rating
     * @return
     */
    public static List<Movie> hasMinimumRating(float rating) {
        List<Movie> moviesList =
                (List) arrayListMovies
                        .stream()
                        .filter(m -> ((Movie)m).getRating() >= rating)
                        .collect(Collectors.toList());

        return moviesList;
    }


    /**
     * countRatingsForMovies
     * count all ratings and assign it to movies
     */
    public static void countRatingsForMovies()
    {
        Movie movies = new Movie();

        for (Object object : arrayListRatings) {
            Rating rating = (Rating) object;

            Movie currentMovie = movies.getMovieById (arrayListMovies, rating.getMovieId());
            currentMovie.setRating(currentMovie.getRating()+rating.getRating());
        }

    }


    /**
     * getMoviesCollection
     * @param bufferedReader
     * @return
     */
    private static Collection<Movie> getMoviesCollection(BufferedReader bufferedReader) {

        ArrayList<Movie> collection = new ArrayList<Movie>();

        try {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while(line != null) {
                Movie mov = new Movie();
                mov.create(line);
                collection.add(mov);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }


    /**
     * getRatingsCollection
     * @param bufferedReader
     * @return
     */
    private static Collection<Rating> getRatingsCollection(BufferedReader bufferedReader) {

        ArrayList<Rating> collection = new ArrayList<Rating>();

        try {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();

            while(line != null) {
                Rating rat = new Rating();
                rat.create(line);
                collection.add(rat);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return collection;
    }


    /**
     *
     * @param file
     * @return
     */
    private static BufferedReader getFileReader(String file) {
        try {
            return Files.newBufferedReader(Paths.get(file), StandardCharsets.ISO_8859_1);
        } catch (IOException ioe) {
            System.err.println("err: " + ioe);
        }
        return null;
    }
}
