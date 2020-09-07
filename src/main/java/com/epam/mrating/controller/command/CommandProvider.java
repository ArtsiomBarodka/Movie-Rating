package com.epam.mrating.controller.command;

import com.epam.mrating.controller.command.impl.*;
import com.epam.mrating.configuration.Constants;
import java.util.HashMap;
import java.util.Map;

import static com.epam.mrating.controller.command.CommandNames.*;


/**
 * The type Command provider.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class CommandProvider {
    private final Map<String, FrontCommand> repository = new HashMap<>();

    /**
     * Instantiates a new Command provider.
     */
    public CommandProvider() {
        repository.put(ALL_MOVIES, new AllMoviesCommand());
        repository.put(TOP_LIST_MOVIES, new TopListMoviesCommand());
        repository.put(SHOW_SIGN_IN, new ShowSignInCommand());
        repository.put(SIGN_IN_WITH_FACEBOOK, new SignInWithFacebookCommand());
        repository.put(FROM_FACEBOOK_SIGN_IN, new FromFacebookSignInCommand());
        repository.put(FROM_GOOGLE_SIGN_IN, new FromGoogleSignInCommand());
        repository.put(SIGN_IN, new SignInCommand());
        repository.put(SHOW_SIGN_UP, new ShowSignUpCommand());
        repository.put(SIGN_UP, new SignUpCommand());
        repository.put(LOGOUT, new LogoutCommand());
        repository.put(SIGN_UP_WITH_SOCIAL, new SignUpWithSocialCommand());
        repository.put(ALL_MOVIES_BY_GENRE, new MoviesByGenreCommand());
        repository.put(ALL_MOVIES_BY_SEARCH, new SearchMoviesCommand());
        repository.put(SHOW_MOVIE, new ShowMovieCommand());
        repository.put(SHOW_EDIT_MOVIE, new ShowEditMovieCommand());
        repository.put(SAVE_EDIT_MOVIE, new SaveEditMovieCommand());
        repository.put(DELETE_MOVIE, new DeleteMovieCommand());
        repository.put(CREATE_MOVIE, new ShowCreateMovieCommand());
        repository.put(SAVE_CREATE_MOVIE, new SaveCreateMovieCommand());
        repository.put(SHOW_USER, new ShowUserCommand());
        repository.put(SHOW_EDIT_USER, new ShowEditUserCommand());
        repository.put(SAVE_EDIT_USER, new SaveEditUserCommand());
        repository.put(DELETE_USER, new DeleteUserCommand());
        repository.put(MORE_MOVIES, new MoreMoviesCommand());
        repository.put(MORE_MOVIES_BY_GENRE, new MoreMoviesByGenreCommand());
        repository.put(MORE_MOVIES_BY_SEARCH, new MoreSearchMoviesCommand());
        repository.put(MORE_MOVIE_COMMENTS, new MoreCommentsShowMovieCommand());
        repository.put(MORE_USER_COMMENTS, new MoreCommentsShowUserCommand());
        repository.put(ADD_COMMENT, new AddCommentCommand());
        repository.put(DELETE_COMMENT, new DeleteCommentCommand());
        repository.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        repository.put(Constants.NOT_FOUND_COMMAND, new NotFoundCommand());
    }

    /**
     * Get command front command.
     *
     * @param commandName the command name
     * @return the front command
     */
    public FrontCommand getCommand(String commandName){
        return repository.get(commandName);
    }
}
