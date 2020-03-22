package epam.my.project.db.handler.select;

import epam.my.project.entity.*;
import java.util.ArrayList;
import java.util.List;

public final class ResultHandlerFactory {

    public static <T> ResultHandler<T> getSingleResultHandler(final ResultHandler<T> oneRowResultHandler) {
        return rs -> {
            if (rs.next()) {
                return oneRowResultHandler.handle(rs);
            } else {
                return null;
            }
        };
    }

    public static <T> ResultHandler<List<T>> getListResultHandler(final ResultHandler<T> oneRowResultHandler) {
        return rs -> {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(oneRowResultHandler.handle(rs));
            }
            return list;
        };
    }

    public static final ResultHandler<Genre> GENRE_RESULT_HANDLER = (rs)-> {
        Genre genre = new Genre();
        genre.setId(rs.getInt("g.id"));
        genre.setName(rs.getString("g.name"));
        return genre;
    };

    public static final ResultHandler<Country> COUNTRY_RESULT_HANDLER = (rs)->{
        Country country = new Country();
        country.setId(rs.getInt("c.id"));
        country.setName(rs.getString("c.name"));
        return country;
    };


    public static final ResultHandler<Category> CATEGORY_RESULT_HANDLER = (rs)->{
        Category category = new Category();
        category.setId(rs.getInt("cat.id"));
        category.setName(rs.getString("cat.name"));
        return category;
    };

    public static final ResultHandler<Filmmaker> FILMMAKER_RESULT_HANDLER = (rs)->{
        Filmmaker filmmaker = new Filmmaker();
        filmmaker.setId(rs.getInt("f.id"));
        filmmaker.setFirstName(rs.getString("f.first_name"));
        filmmaker.setLastName(rs.getString("f.last_name"));
        return filmmaker;
    };

    public static final ResultHandler<Movie> MOVIE_RESULT_HANDLER = (rs)-> {
        Movie movie = new Movie();
        movie.setId(rs.getInt("m.id"));
        movie.setImageLink(rs.getString("m.image_link"));
        movie.setName(rs.getString("m.name"));
        movie.setDescription(rs.getString("m.description"));
        movie.setYear(rs.getDate("m.year"));
        movie.setBudget(rs.getLong("m.budget"));
        movie.setFees(rs.getLong("m.fees"));
        movie.setDuration(rs.getTime("m.duration"));
        movie.setRating(rs.getDouble("m.rating"));
        movie.setAdded(rs.getTimestamp("m.added"));
        Filmmaker filmmaker = FILMMAKER_RESULT_HANDLER.handle(rs);
        movie.setFilmmaker(filmmaker);
        Genre genre = GENRE_RESULT_HANDLER.handle(rs);
        movie.setGenre(genre);
        Category category = CATEGORY_RESULT_HANDLER.handle(rs);
        movie.setCategory(category);
        Country country = COUNTRY_RESULT_HANDLER.handle(rs);
        movie.setCountry(country);
        return movie;
    };

    public static final ResultHandler<Role> ROLE_RESULT_HANDLER = (rs)->{
        Role role = new Role();
        role.setId(rs.getInt("r.id"));
        role.setName(rs.getString("r.name"));
        return role;
    };

    public static final ResultHandler<Account> ACCOUNT_RESULT_HUNDLER = (rs)->{
        Account account = new Account();
        account.setId(rs.getInt("a.id"));
        account.setName(rs.getString("a.name"));
        account.setPassword(rs.getString("a.password"));
        account.setEmail(rs.getString("a.email"));
        Role role = ROLE_RESULT_HANDLER.handle(rs);
        account.setRole(role);
        return account;
    };

    private ResultHandlerFactory(){

    }
}
