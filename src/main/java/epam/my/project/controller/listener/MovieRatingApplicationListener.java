package epam.my.project.controller.listener;

import epam.my.project.configuration.ResourceConfiguration;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.controller.exception.PageException;
import epam.my.project.service.exception.InternalServerErrorException;
import epam.my.project.service.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Filmmaker;
import epam.my.project.model.entity.Genre;
import epam.my.project.service.factory.ServiceConfiguration;
import epam.my.project.service.factory.ServiceFactory;
import epam.my.project.service.factory.ServiceType;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The type Movie rating application listener.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@WebListener
public class MovieRatingApplicationListener implements ServletContextListener {
    private static final Logger logger = getLogger(MovieRatingApplicationListener.class);
    private ServiceFactory serviceFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.serviceFactory = ServiceConfiguration.getServiceFactory(ServiceType.FINAL_SERVICE);
        try {
            List<Category> categories = serviceFactory.getViewMovieService().listAllCategories();
            List<Genre> genres = serviceFactory.getViewMovieService().listAllGenres();
            List<Country> countries = serviceFactory.getViewMovieService().listAllCountries();
            List<Filmmaker> filmmakers = serviceFactory.getViewMovieService().listAllFilmmakers();
            int allMoviesCount = serviceFactory.getViewMovieService().countAllMovies();
            sce.getServletContext().setAttribute(RequestAttributeNames.FILMMAKERS, filmmakers);
            sce.getServletContext().setAttribute(RequestAttributeNames.CATEGORIES, categories);
            sce.getServletContext().setAttribute(RequestAttributeNames.GENRES, genres);
            sce.getServletContext().setAttribute(RequestAttributeNames.COUNTRIES, countries);
            sce.getServletContext().setAttribute(RequestAttributeNames.ALL_MOVIES_COUNT, allMoviesCount);
            sce.getServletContext().setAttribute(RequestAttributeNames.GOOGLE_APP_ID, ResourceConfiguration.CONFIGURATION_INSTANCE.getGoogleAppId());
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
