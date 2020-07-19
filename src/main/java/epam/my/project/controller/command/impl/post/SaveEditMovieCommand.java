package epam.my.project.controller.command.impl.post;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.util.ViewUtil;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SaveEditMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -6845550316808967779L;
    private static final int SUBSTRING_INDEX = "/app/movie/edit/save/".length();

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        String uid = request.getRequestURI().substring(SUBSTRING_INDEX);
        int movieId = Integer.parseInt(request.getParameter("id"));
        try {
            MovieForm movieForm = fetchForm(request);
            Movie editMovie = serviceFactory.getEditMovieService().getMovieById(movieId);
            if(!editMovie.getName().equalsIgnoreCase(request.getParameter("name")) && serviceFactory.getEditMovieService().isAlreadyExistMovie(movieForm.getName())){
                WebUtil.setMessage(request, "Movie with this name already exist!");
                ViewUtil.forwardToServlet("/app/movie/edit/" + uid, request,response);
            } else {
                Movie updatedMovie = serviceFactory.getEditMovieService().updateMovie(movieForm, movieId);
                ViewUtil.redirect("/app/movie/" + updatedMovie.getUid(), request,response);
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
            ViewUtil.forwardToServlet("/app/movie/edit/" + uid, request,response);
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
