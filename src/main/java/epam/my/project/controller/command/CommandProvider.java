package epam.my.project.controller.command;


import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.get.*;
import epam.my.project.controller.command.impl.get.ajax.*;
import epam.my.project.controller.command.impl.post.*;
import epam.my.project.controller.command.impl.post.AddCommentCommand;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<String, FrontCommand> repository = new HashMap<>();

    public CommandProvider() {
        repository.put("/app/movies", new AllMoviesCommand());
        repository.put("/app/movies/top", new TopListMoviesCommand());
        repository.put("/app/show/sign-in", new ShowSignInCommand());
        repository.put("/app/sign-in/facebook", new SignInWithFacebookCommand());
        repository.put("/app/from-social/fb", new FromFacebookSignInCommand());
        repository.put("/app/from-social/google", new FromGoogleSignInCommand());
        repository.put("/app/sign-in", new SignInCommand());
        repository.put("/app/show/sign-up", new ShowSignUpCommand());
        repository.put("/app/sign-up", new SignUpCommand());
        repository.put("/app/logout", new LogoutCommand());
        repository.put("/app/sign-up/complete", new SignUpWithSocialCommand());
        repository.put("/app/from/google", new FromGoogleSignInCommand());
        repository.put("/app/from/facebook", new FromFacebookSignInCommand());
        repository.put("/app/movies/genres/*", new MoviesByGenreCommand());
        repository.put("/app/movies/search", new SearchMoviesCommand());
        repository.put("/app/movie/*", new ShowMovieCommand());
        repository.put("/app/movie/edit/*", new ShowEditMovieCommand());
        repository.put("/app/movie/edit/save/*", new SaveEditMovieCommand());
        repository.put("/app/movie/delete/*", new DeleteMovieCommand());
        repository.put("/app/movie/new/create", new ShowCreateMovieCommand());
        repository.put("/app/movie/create/save", new SaveCreateMovieCommand());
        repository.put("/app/user/*", new ShowUserCommand());
        repository.put("/app/user/edit/*", new ShowEditUserCommand());
        repository.put("/app/user/edit/save/*", new SaveEditUserCommand());
        repository.put("/app/user/delete", new DeleteUserCommand());
        repository.put("/app/ajax/html/movies", new MoreMoviesCommand());
        repository.put("/app/ajax/html/movies/genres/*", new MoreMoviesByGenreCommand());
        repository.put("/app/ajax/html/movies/search", new MoreSearchMoviesCommand());
        repository.put("/app/ajax/html/movie/*", new MoreCommentsShowMovieCommand());
        repository.put("/app/ajax/html/user/*", new MoreCommentsShowUserCommand());
        repository.put("/app/comment/add/movie/*", new AddCommentCommand());
        repository.put("/app/comment/delete", new DeleteCommentCommand());
        repository.put("/app/locale/change", new ChangeLocaleCommand());
        repository.put(Constants.NOT_FOUND_COMMAND, new NotFoundCommand());
    }

    public FrontCommand getCommand(String commandName){
        return repository.get(commandName);
    }
}
