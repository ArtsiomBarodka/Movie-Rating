package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Genre;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

/**
 * The type Delete movie command.
 */
public class DeleteMovieCommand extends FrontCommand {
    private static final long serialVersionUID = 7534284914787908135L;
    private static final int SUBSTRING_INDEX = "/app/movie/delete/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        if(serviceFactory.getEditMovieService().deleteMovie(uid)){
            List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
            request.getServletContext().setAttribute(RequestAttributeNames.GENRES, genres);
            int allMoviesCount = serviceFactory.getViewMovieService().countAllMovies();
            request.getServletContext().setAttribute(RequestAttributeNames.ALL_MOVIES_COUNT, allMoviesCount);
        }
        ViewUtil.redirect("/app/movies",request,response);
    }

}

