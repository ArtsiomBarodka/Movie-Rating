package epam.my.project.controller.command.impl.get;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Genre;
import epam.my.project.util.ViewUtil;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeleteMovieCommand extends FrontCommand {
    private static final long serialVersionUID = 7534284914787908135L;
    private static final int SUBSTRING_INDEX = "/app/movie/delete/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        if(serviceFactory.getEditMovieService().deleteMovie(uid)){
            List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
            request.getServletContext().setAttribute(Constants.GENRES, genres);
            int allMoviesCount = serviceFactory.getViewMovieService().countAllMovies();
            request.getServletContext().setAttribute(Constants.ALL_MOVIES_COUNT, allMoviesCount);
        }
        ViewUtil.redirect("/app/movies",request,response);
    }

}

