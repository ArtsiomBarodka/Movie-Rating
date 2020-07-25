package epam.my.project.controller.command.impl;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.Page;
import epam.my.project.model.entity.Comment;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.entity.User;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type Show movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 final class ShowMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = 3137112415525853722L;
    private static final int SUBSTRING_INDEX = "/app/movie/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByMovie(movie.getId(), new Page(pageable));
        movie.setComments(comments);
        request.setAttribute(RequestAttributeNames.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(RequestAttributeNames.TOTAL_COMMENTS_COUNT, totalCount);
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        AccountDetails accountDetails = WebUtil.getCurrentAccountDetails(request);
        if(Objects.nonNull(accountDetails) && SecurityConfiguration.ROLE_USER.equalsIgnoreCase(accountDetails.getRole())){
            int currentAccountId = accountDetails.getId();
            User user = serviceFactory.getUserService().getUserByAccountId(currentAccountId);
            request.setAttribute(RequestAttributeNames.USER, user);
            boolean isAlreadyExistComment = serviceFactory.getCommentService().commentAlreadyExist(movie.getId(), user.getId());
            request.setAttribute(RequestAttributeNames.ALREADY_EXIST_COMMENT, isAlreadyExistComment);
        }
        ViewUtil.forwardToPage("page/movie.jsp",request,response);
    }
}
