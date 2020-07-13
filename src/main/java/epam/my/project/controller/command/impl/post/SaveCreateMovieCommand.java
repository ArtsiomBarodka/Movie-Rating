package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Genre;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class SaveCreateMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -3888245404031945050L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ServletException, ObjectNotFoundException {
        MovieForm movieForm = fetchForm(request);
        try {
            if(serviceFactory.getEditMovieService().isAlreadyExistMovie(request.getParameter("name"))){
                WebUtil.setMessage(request, "Movie with this name already exist!");
                viewFactory.getForwardToPage().init(request,response).render("page/create-movie.jsp");
            } else {
                Movie movie = serviceFactory.getEditMovieService().createMovie(movieForm);
                List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
                request.getServletContext().setAttribute(Constants.GENRES, genres);
                viewFactory.getRedirect().init(request,response).render("/app/movie/" + movie.getUid());
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
            viewFactory.getForwardToPage().init(request,response).render("page/create-movie.jsp");
        }
    }

    private MovieForm fetchForm(HttpServletRequest request) {
        String imageLink = request.getParameter("imageLink");
        String name = request.getParameter("name");
        String year = request.getParameter("year");
        String fees = request.getParameter("fees");
        String budget = request.getParameter("budget");
        String duration = request.getParameter("duration");
        String genre = request.getParameter("genre");
        String category = request.getParameter("category");
        String filmmaker = request.getParameter("genre");
        String country = request.getParameter("category");
        String description = request.getParameter("description");
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