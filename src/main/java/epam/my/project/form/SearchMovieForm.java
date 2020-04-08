package epam.my.project.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchMovieForm {
    private String query;
    private List<Integer> categories;
    private List<Integer> genres;
    private List<Integer> countries;

    public SearchMovieForm(String query,
                           String[] categories,
                           String[] genres,
                           String[] countries) {
        this.query = query;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public List<Integer> getCountries() {
        return countries;
    }

    public void setCountries(List<Integer> countries) {
        this.countries = countries;
    }
}
