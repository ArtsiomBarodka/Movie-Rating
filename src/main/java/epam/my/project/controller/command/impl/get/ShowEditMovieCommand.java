package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Movie;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * The type Show edit movie command.
 */
public class ShowEditMovieCommand extends FrontCommand {
    private static final long serialVersionUID = 6482141577718818609L;
    private static final int SUBSTRING_INDEX = "/app/movie/edit/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        request.setAttribute(RequestAttributeNames.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(RequestAttributeNames.TOTAL_COMMENTS_COUNT, totalCount);
        ViewUtil.forwardToPage("page/edit-movie.jsp",request,response);
    }
}
