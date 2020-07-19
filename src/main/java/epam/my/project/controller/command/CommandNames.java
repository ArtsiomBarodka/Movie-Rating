package epam.my.project.controller.command;

/**
 * The type Command names.
 */
class CommandNames {

     private CommandNames(){}

    /**
     * The All movies.
     */
    static final String ALL_MOVIES = "/app/movies";
    /**
     * The Top list movies.
     */
    static final String TOP_LIST_MOVIES = "/app/movies/top";
    /**
     * The Show sign in.
     */
    static final String SHOW_SIGN_IN = "/app/show/sign-in";
    /**
     * The Sign in with facebook.
     */
    static final String SIGN_IN_WITH_FACEBOOK = "/app/sign-in/facebook";
    /**
     * The From facebook sign in.
     */
    static final String FROM_FACEBOOK_SIGN_IN = "/app/from-social/fb";
    /**
     * The From google sign in.
     */
    static final String FROM_GOOGLE_SIGN_IN = "/app/from-social/google";
    /**
     * The Sign in.
     */
    static final String SIGN_IN = "/app/sign-in";
    /**
     * The Show sign up.
     */
    static final String SHOW_SIGN_UP = "/app/show/sign-up";
    /**
     * The Sign up.
     */
    static final String SIGN_UP = "/app/sign-up";
    /**
     * The Logout.
     */
    static final String LOGOUT = "/app/logout";
    /**
     * The Sign up with social.
     */
    static final String SIGN_UP_WITH_SOCIAL = "/app/sign-up/complete";
    /**
     * The All movies by genre.
     */
    static final String ALL_MOVIES_BY_GENRE = "/app/movies/genres/*";
    /**
     * The All movies by search.
     */
    static final String ALL_MOVIES_BY_SEARCH = "/app/movies/search";
    /**
     * The Show movie.
     */
    static final String SHOW_MOVIE = "/app/movie/*";
    /**
     * The Show edit movie.
     */
    static final String SHOW_EDIT_MOVIE = "/app/movie/edit/*";
    /**
     * The Save edit movie.
     */
    static final String SAVE_EDIT_MOVIE = "/app/movie/edit/save/*";
    /**
     * The Delete movie.
     */
    static final String DELETE_MOVIE = "/app/movie/delete/*";
    /**
     * The Create movie.
     */
    static final String CREATE_MOVIE = "/app/movie/new/create";
    /**
     * The Save create movie.
     */
    static final String SAVE_CREATE_MOVIE = "/app/movie/create/save";
    /**
     * The Show user.
     */
    static final String SHOW_USER = "/app/user/*";
    /**
     * The Show edit user.
     */
    static final String SHOW_EDIT_USER = "/app/user/edit/*";
    /**
     * The Save edit user.
     */
    static final String SAVE_EDIT_USER = "/app/user/edit/save/*";
    /**
     * The Delete user.
     */
    static final String DELETE_USER = "/app/user/delete";
    /**
     * The More movies.
     */
    static final String MORE_MOVIES = "/app/ajax/html/movies";
    /**
     * The More movies by genre.
     */
    static final String MORE_MOVIES_BY_GENRE = "/app/ajax/html/movies/genres/*";
    /**
     * The More movies by search.
     */
    static final String MORE_MOVIES_BY_SEARCH = "/app/ajax/html/movies/search";
    /**
     * The More movie comments.
     */
    static final String MORE_MOVIE_COMMENTS = "/app/ajax/html/movie/*";
    /**
     * The More user comments.
     */
    static final String MORE_USER_COMMENTS = "/app/ajax/html/user/*";
    /**
     * The Add comment.
     */
    static final String ADD_COMMENT = "/app/comment/add/movie/*";
    /**
     * The Delete comment.
     */
    static final String DELETE_COMMENT = "/app/comment/delete";
    /**
     * The Change locale.
     */
    static final String CHANGE_LOCALE = "/app/locale/change";
}
