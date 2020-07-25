package epam.my.project.model.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Search movie form.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public class SearchMovieForm {
    private String movieName;
    private List<Integer> categories;
    private List<Integer> genres;
    private List<Integer> countries;

    /**
     * Instantiates a new Search movie form.
     *
     * @param movieName  the movie name
     * @param categories the categories
     * @param genres     the genres
     * @param countries  the countries
     */
    public SearchMovieForm(String movieName,
                           String[] categories,
                           String[] genres,
                           String[] countries) {
        this.movieName = movieName;
        this.categories = convert(categories);
        this.genres = convert(genres);
        this.countries = convert(countries);
    }

    private List<Integer> convert(String[] args) {
        if(args == null) {
            return Collections.emptyList();
        } else {
            List<Integer> res = new ArrayList<>(args.length);
            for(String arg : args) {
                res.add(Integer.parseInt(arg));
            }
            return res;
        }
    }

    /**
     * Gets movie name.
     *
     * @return the movie name
     */
    public String getMovieName() {
        return movieName;
    }

    /**
     * Sets movie name.
     *
     * @param movieName the movie name
     */
    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    /**
     * Gets categories.
     *
     * @return the categories
     */
    public List<Integer> getCategories() {
        return categories;
    }

    /**
     * Sets categories.
     *
     * @param categories the categories
     */
    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public List<Integer> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the genres
     */
    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    /**
     * Gets countries.
     *
     * @return the countries
     */
    public List<Integer> getCountries() {
        return countries;
    }

    /**
     * Sets countries.
     *
     * @param countries the countries
     */
    public void setCountries(List<Integer> countries) {
        this.countries = countries;
    }
}
