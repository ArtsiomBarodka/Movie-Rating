package epam.my.project.listener;

import epam.my.project.configuration.Constants;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.entity.Category;
import epam.my.project.model.entity.Country;
import epam.my.project.model.entity.Genre;
import epam.my.project.service.factory.ServiceFactory;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
            sce.getServletContext().setAttribute(Constants.CATEGORIES, categories);
            sce.getServletContext().setAttribute(Constants.GENRES, genres);
            sce.getServletContext().setAttribute(Constants.COUNTRIES, countries);
        } catch (ObjectNotFoundException e) {
            ///
        } catch (InternalServerErrorException e) {
            ///
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceFactory.close();
    }
}
