package epam.my.project.controller.command.impl;

import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.request.RequestParameterNames;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.service.exception.ValidationException;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * The type Save create movie command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
 public final class SaveCreateMovieCommand extends AbstractCommand {
    private static final long serialVersionUID = -3888245404031945050L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException, ObjectNotFoundException {
        MovieForm movieForm = fetchForm(request);
        try {
            if(serviceFactory.getEditMovieService().isAlreadyExistMovie(request.getParameter(RequestParameterNames.MOVIE_NAME))){
                WebUtil.setMessage(request, "Movie with this name already exist!");
                ViewUtil.forwardToPage("page/create-movie.jsp", request, response);
            } else {
                Movie movie = serviceFactory.getEditMovieService().createMovie(movieForm);
                List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
                request.getServletContext().setAttribute(RequestAttributeNames.GENRES, genres);
                ViewUtil.redirect("/app/movie/" + movie.getUid(), request,response);
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
            ViewUtil.forwardToPage("page/create-movie.jsp", request, response);
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