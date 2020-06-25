package epam.my.project.listener;

import epam.my.project.configuration.Constants;
import epam.my.project.configuration.ResourceConfiguration;
import epam.my.project.exception.PageException;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Filmmaker;
import epam.my.project.model.entity.Genre;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@WebListener
public class MovieRatingApplicationListener implements ServletContextListener {
    private static final Logger logger = getLogger(MovieRatingApplicationListener.class);
    private ServiceFactory serviceFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.serviceFactory = ServiceFactory.SERVICE_FACTORY_INSTANCE;
        try {
            List<Category> categories = serviceFactory.getViewMovieService().listAllCategories();
            List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
            List<Country> countries = serviceFactory.getViewMovieService().listAllCountries();
            List<Filmmaker> filmmakers = serviceFactory.getViewMovieService().listAllFilmmakers();
            int allMoviesCount = serviceFactory.getViewMovieService().countAllMovies();
            sce.getServletContext().setAttribute(Constants.FILMMAKERS, filmmakers);
            sce.getServletContext().setAttribute(Constants.CATEGORIES, categories);
            sce.getServletContext().setAttribute(Constants.GENRES, genres);
            sce.getServletContext().setAttribute(Constants.COUNTRIES, countries);
            sce.getServletContext().setAttribute(Constants.ALL_MOVIES_COUNT, allMoviesCount);
            sce.getServletContext().setAttribute(Constants.GOOGLE_APP_ID, ResourceConfiguration.CONFIGURATION_INSTANCE.getGoogleAppId());
            logger.info("ApplicationListener was initialized");
        } catch (ObjectNotFoundException e) {
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_NOT_FOUND);
        } catch (InternalServerErrorException e) {
            throw new PageException(e.getMessage(), e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceFactory.close();
    }
}
