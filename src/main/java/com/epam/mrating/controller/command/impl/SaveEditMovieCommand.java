package com.epam.mrating.controller.command.impl;

import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.exception.ValidationException;
import com.epam.mrating.util.ViewUtil;
import com.epam.mrating.controller.request.RequestParameterNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.model.entity.Movie;
import com.epam.mrating.model.form.MovieForm;
import com.epam.mrating.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Save edit movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class SaveEditMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = -6845550316808967779L;
    private static final int SUBSTRING_INDEX = "/app/movie/edit/save/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        int movieId = Integer.parseInt(request.getParameter(RequestParameterNames.MOVIE_ID));
        try {
            MovieForm movieForm = fetchForm(request);
            Movie editMovie = serviceFactory.getEditMovieService().getMovieById(movieId);
            if(!editMovie.getName().equalsIgnoreCase(request.getParameter(RequestParameterNames.MOVIE_NAME))
                    && serviceFactory.getEditMovieService().isAlreadyExistMovie(movieForm.getName())){

                WebUtil.setRequestMessage(request, "Movie with this name already exist!");
                ViewUtil.forwardToServlet("/app/movie/edit/" + uid, request,response);
            } else {
                Movie updatedMovie = serviceFactory.getEditMovieService().updateMovie(movieForm, movieId);
                ViewUtil.redirect("/app/movie/" + updatedMovie.getUid(), request,response);
            }
        } catch (ValidationException e) {
            WebUtil.setRequestViolations(request,e.getViolations());
            ViewUtil.forwardToServlet("/app/movie/edit/" + uid, request,response);
        }
    }

    private MovieForm fetchForm(HttpServletRequest request) {
        String imageLink = request.getParameter(RequestParameterNames.MOVIE_IMAGE_LINK);
        String name = request.getParameter(RequestParameterNames.MOVIE_NAME);
        String year = request.getParameter(RequestParameterNames.MOVIE_YEAR);
        String fees = request.getParameter(RequestParameterNames.MOVIE_FEES);
        String budget = request.getParameter(RequestParameterNames.MOVIE_BUDGET);
        String duration = request.getParameter(RequestParameterNames.MOVIE_DURATION);
        String genre = request.getParameter(RequestParameterNames.MOVIE_GENRE);
        String category = request.getParameter(RequestParameterNames.MOVIE_CATEGORY);
        String filmmaker = request.getParameter(RequestParameterNames.MOVIE_FILMMAKER);
        String country = request.getParameter(RequestParameterNames.MOVIE_COUNTRY);
        String description = request.getParameter(RequestParameterNames.MOVIE_DESCRIPTION);
        MovieForm movieForm = new MovieForm();
        movieForm.setImageLink(imageLink);
        movieForm.setName(name);
        movieForm.setYear(year);
        movieForm.setFees(fees);
        movieForm.setBudget(budget);
        movieForm.setDuration(duration);
        movieForm.setGenreId(genre);
        movieForm.setCategoryId(category);
        movieForm.setFilmmakerId(filmmaker);
        movieForm.setCountryId(country);
        movieForm.setDescription(description);
        return movieForm;
    }

}
