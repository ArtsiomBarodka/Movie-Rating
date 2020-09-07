package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SecurityConfiguration;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.domain.AccountDetails;
import com.epam.mrating.model.domain.Page;
import com.epam.mrating.model.entity.Comment;
import com.epam.mrating.model.entity.Movie;
import com.epam.mrating.model.entity.User;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * The type More comments show movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class MoreCommentsShowMovieCommand extends AbstractCommand {
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
        AccountDetails accountDetails = WebUtil.getSessionCurrentAccountDetails(request);
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