package com.epam.mrating.dao.impl.jdbc.handler;

import com.epam.mrating.model.entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Result handler factory.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ResultHandlerFactory {

    /**
     * Gets single result handler.
     *
     * @param <T>                 the type parameter
     * @param oneRowResultHandler the one row result handler
     * @return the single result handler
     */
    public static <T> ResultHandler<T> getSingleResultHandler(final ResultHandler<T> oneRowResultHandler) {
        return rs -> {
            if (rs.next()) {
                return oneRowResultHandler.handle(rs);
            } else {
                return null;
            }
        };
    }

    /**
     * Gets list result handler.
     *
     * @param <T>                 the type parameter
     * @param oneRowResultHandler the one row result handler
     * @return the list result handler
     */
    public static <T> ResultHandler<List<T>> getListResultHandler(final ResultHandler<T> oneRowResultHandler) {
        return rs -> {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(oneRowResultHandler.handle(rs));
            }
            return list;
        };
    }

    /**
     * The constant GENRE_RESULT_HANDLER.
     */
    public static final ResultHandler<Genre> GENRE_RESULT_HANDLER = rs-> {
        Genre genre = new Genre();
        genre.setId(rs.getInt("g.id"));
        genre.setName(rs.getString("g.name"));
        genre.setMoviesCount(rs.getInt("g.movies_count"));
        return genre;
    };

    /**
     * The constant COUNTRY_RESULT_HANDLER.
     */
    public static final ResultHandler<Country> COUNTRY_RESULT_HANDLER = rs->{
        Country country = new Country();
        country.setId(rs.getInt("c.id"));
        country.setName(rs.getString("c.name"));
        return country;
    };


    /**
     * The constant CATEGORY_RESULT_HANDLER.
     */
    public static final ResultHandler<Category> CATEGORY_RESULT_HANDLER = rs->{
        Category category = new Category();
        category.setId(rs.getInt("cat.id"));
        category.setName(rs.getString("cat.name"));
        return category;
};

    /**
     * The constant FILMMAKER_RESULT_HANDLER.
     */
    public static final ResultHandler<Filmmaker> FILMMAKER_RESULT_HANDLER = rs->{
        Filmmaker filmmaker = new Filmmaker();
        filmmaker.setId(rs.getInt("f.id"));
        filmmaker.setFirstName(rs.getString("f.first_name"));
        filmmaker.setLastName(rs.getString("f.last_name"));
        return filmmaker;
    };

    /**
     * The constant MOVIE_RESULT_HANDLER.
     */
    public static final ResultHandler<Movie> MOVIE_RESULT_HANDLER = rs-> {
        Movie movie = new Movie();
        movie.setId(rs.getInt("m.id"));
        movie.setUid(rs.getString("m.uid"));
        movie.setImageLink(rs.getString("m.image_link"));
        movie.setName(rs.getString("m.name"));
        movie.setDescription(rs.getString("m.description"));
        movie.setYear(rs.getShort("m.year"));
        movie.setBudget(rs.getLong("m.budget"));
        movie.setFees(rs.getLong("m.fees"));
        movie.setDuration(rs.getString("m.duration"));
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

    /**
     * The constant ROLE_RESULT_HANDLER.
     */
    public static final ResultHandler<Role> ROLE_RESULT_HANDLER = rs->{
        Role role = new Role();
        role.setId(rs.getInt("r.id"));
        role.setName(rs.getString("r.name"));
        return role;
    };

    /**
     * The constant ACCOUNT_RESULT_HANDLER.
     */
    public static final ResultHandler<Account> ACCOUNT_RESULT_HANDLER = rs->{
        Account account = new Account();
        account.setId(rs.getInt("a.id"));
        account.setName(rs.getString("a.name"));
        account.setPassword(rs.getString("a.password"));
        account.setEmail(rs.getString("a.email"));
        Role role = ROLE_RESULT_HANDLER.handle(rs);
        account.setRole(role);
        return account;
    };

    /**
     * The constant USER_RESULT_HANDLER.
     */
    public static final ResultHandler<User> USER_RESULT_HANDLER = rs->{
        User user = new User();
        user.setId(rs.getInt("u.id"));
        user.setUid(rs.getString("u.uid"));
        user.setImageLink(rs.getString("u.image_link"));
        user.setCreated(rs.getTimestamp("u.created"));
        user.setBanned(rs.getBoolean("u.banned"));
        user.setRating(rs.getInt("u.rating"));
        Account account = ACCOUNT_RESULT_HANDLER.handle(rs);
        user.setAccount(account);
        return user;
    };

    /**
     * The constant COMMENT_RESULT_HANDLER.
     */
    public static final ResultHandler<Comment> COMMENT_RESULT_HANDLER = rs->{
        Comment comment = new Comment();
        comment.setId(rs.getLong("c.id"));
        comment.setContent(rs.getString("c.content"));
        comment.setCreated(rs.getTimestamp("c.created"));
        comment.setRating(rs.getDouble("c.rating"));
        User user = new User();
        user.setId(rs.getInt("u.id"));
        user.setRating(rs.getInt("u.rating"));
        user.setUid(rs.getString("u.uid"));
        user.setImageLink(rs.getString("u.image_link"));
        Account account = new Account();
        account.setName(rs.getString("a.name"));
        account.setId(rs.getInt("a.id"));
        user.setAccount(account);
        comment.setUser(user);
        Movie movie = new Movie();
        movie.setId(rs.getInt("m.id"));
        movie.setRating(rs.getDouble("m.rating"));
        movie.setName(rs.getString("m.name"));
        movie.setUid(rs.getString("m.uid"));
        movie.setImageLink(rs.getString("m.image_link"));
        comment.setMovie(movie);
        return comment;
    };

    /**
     * The constant ACCOUNT_AUTH_TOKEN_RESULT_HANDLER.
     */
    public static final ResultHandler<AccountAuthToken> ACCOUNT_AUTH_TOKEN_RESULT_HANDLER = rs->{
        AccountAuthToken accountAuthToken = new AccountAuthToken();
        accountAuthToken.setId(rs.getLong("t.id"));
        accountAuthToken.setSelector(rs.getString("t.selector"));
        accountAuthToken.setValidator(rs.getString("t.validator"));
        accountAuthToken.setAccountId(rs.getInt("t.fk_account_id"));
        return accountAuthToken;
    };

    private ResultHandlerFactory(){

    }
}
