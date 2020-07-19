package epam.my.project.controller.command;

 class CommandNames {

     private CommandNames(){}

     static final String ALL_MOVIES = "/app/movies";
     static final String TOP_LIST_MOVIES = "/app/movies/top";
     static final String SHOW_SIGN_IN = "/app/show/sign-in";
     static final String SIGN_IN_WITH_FACEBOOK = "/app/sign-in/facebook";
     static final String FROM_FACEBOOK_SIGN_IN = "/app/from-social/fb";
     static final String FROM_GOOGLE_SIGN_IN = "/app/from-social/google";
     static final String SIGN_IN = "/app/sign-in";
     static final String SHOW_SIGN_UP = "/app/show/sign-up";
     static final String SIGN_UP = "/app/sign-up";
     static final String LOGOUT = "/app/logout";
     static final String SIGN_UP_WITH_SOCIAL = "/app/sign-up/complete";
     static final String ALL_MOVIES_BY_GENRE = "/app/movies/genres/*";
     static final String ALL_MOVIES_BY_SEARCH = "/app/movies/search";
     static final String SHOW_MOVIE = "/app/movie/*";
     static final String SHOW_EDIT_MOVIE = "/app/movie/edit/*";
     static final String SAVE_EDIT_MOVIE = "/app/movie/edit/save/*";
     static final String DELETE_MOVIE = "/app/movie/delete/*";
     static final String CREATE_MOVIE = "/app/movie/new/create";
     static final String SAVE_CREATE_MOVIE = "/app/movie/create/save";
     static final String SHOW_USER = "/app/user/*";
     static final String SHOW_EDIT_USER = "/app/user/edit/*";
     static final String SAVE_EDIT_USER = "/app/user/edit/save/*";
     static final String DELETE_USER = "/app/user/delete";
     static final String MORE_MOVIES = "/app/ajax/html/movies";
     static final String MORE_MOVIES_BY_GENRE = "/app/ajax/html/movies/genres/*";
     static final String MORE_MOVIES_BY_SEARCH = "/app/ajax/html/movies/search";
     static final String MORE_MOVIE_COMMENTS = "/app/ajax/html/movie/*";
     static final String MORE_USER_COMMENTS = "/app/ajax/html/user/*";
     static final String ADD_COMMENT = "/app/comment/add/movie/*";
     static final String DELETE_COMMENT = "/app/comment/delete";
     static final String CHANGE_LOCALE = "/app/locale/change";
}
