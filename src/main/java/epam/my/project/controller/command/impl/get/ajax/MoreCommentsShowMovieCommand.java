package epam.my.project.controller.command.impl.get.ajax;

import epam.my.project.configuration.SecurityConfiguration;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.controller.command.FrontCommand;
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
 * The type More comments show movie command.
 */
public class MoreCommentsShowMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -3249206833276562574L;
    private static final int SUBSTRING_INDEX = "/app/ajax/html/movie/".length();

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        int page = Integer.parseInt(request.getParameter(RequestParameterNames.PAGE));
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        Movie movie = serviceFactory.getEditMovieService().getMovieByUId(uid);
        int pageable = getPageable();
        request.setAttribute(RequestAttributeNames.PAGEABLE, pageable);
        List<Comment> comments = serviceFactory.getCommentService().listAllCommentsByMovie(movie.getId(), new Page(page, pageable));
        movie.setComments(comments);
        request.setAttribute(RequestAttributeNames.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(RequestAttributeNames.PAGE_COUNT, getPageCount(totalCount, pageable));
        AccountDetails accountDetails = WebUtil.getCurrentAccountDetails(request);
        if(Objects.nonNull(accountDetails) && SecurityConfiguration.ROLE_USER.equalsIgnoreCase(accountDetails.getRole())){
            int currentAccountId = accountDetails.getId();
            User user = serviceFactory.getUserService().getUserByAccountId(currentAccountId);
            request.setAttribute(RequestAttributeNames.USER, user);
            boolean isAlreadyExistComment = serviceFactory.getCommentService().commentAlreadyExist(movie.getId(), user.getId());
            request.setAttribute(RequestAttributeNames.ALREADY_EXIST_COMMENT, isAlreadyExistComment);
        }
        ViewUtil.forwardToFragment("comments-list.jsp",request,response);
    }
}