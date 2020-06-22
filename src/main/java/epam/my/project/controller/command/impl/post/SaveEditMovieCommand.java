package epam.my.project.controller.command.impl.post;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.entity.Movie;
import epam.my.project.model.form.MovieForm;
import epam.my.project.util.WebUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SaveEditMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -6845550316808967779L;


    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException, ServletException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        try {
            MovieForm movieForm = fetchForm(request);
            Movie editMovie = serviceFactory.getEditMovieService().getMovieById(movieId);
            if(!editMovie.getName().equalsIgnoreCase(request.getParameter("name")) && serviceFactory.getEditMovieService().isAlreadyExistMovie(movieForm.getName())){
                WebUtil.setMessage(request, "Movie with this name already exist!");
                returnToPage(request);
            } else {
                Movie updatedMovie = serviceFactory.getEditMovieService().updateMovie(movieForm, movieId);
                redirect("/app/movie/" + updatedMovie.getUid());
            }
        } catch (ValidationException e) {
            WebUtil.setViolations(request,e.getViolations());
            returnToPage(request);
        }
    }

    private void returnToPage(HttpServletRequest request) throws InternalServerErrorException, ObjectNotFoundException, ServletException, IOException {
        int movieId = Integer.parseInt(request.getParameter("id"));
        Movie movie = serviceFactory.getEditMovieService().getMovieById(movieId);
        request.setAttribute(Constants.MOVIE, movie);
        int totalCount = serviceFactory.getCommentService().countAllCommentsByMovie(movie.getId());
        request.setAttribute(Constants.TOTAL_COMMENTS_COUNT, totalCount);
        forwardToPage("page/edit-movie.jsp");
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
