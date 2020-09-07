package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.configuration.SecurityConfiguration;
import com.epam.mrating.controller.request.RequestAttributeNames;
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
 * The type Show movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class ShowMovieCommand extends AbstractCommand {
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
        AccountDetails accountDetails = WebUtil.getSessionCurrentAccountDetails(request);
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
