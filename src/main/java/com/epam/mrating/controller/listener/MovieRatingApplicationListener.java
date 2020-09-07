package com.epam.mrating.controller.listener;

import com.epam.mrating.configuration.ResourceConfiguration;
import com.epam.mrating.controller.exception.PageException;
import com.epam.mrating.controller.request.RequestAttributeNames;
import com.epam.mrating.service.exception.InternalServerErrorException;
import com.epam.mrating.service.exception.ObjectNotFoundException;
import com.epam.mrating.service.factory.ServiceManager;
import com.epam.mrating.service.factory.ServiceFactory;
import com.epam.mrating.service.factory.ServiceType;
import com.epam.mrating.model.entity.Category;
import com.epam.mrating.model.entity.Country;
import com.epam.mrating.model.entity.Filmmaker;
import com.epam.mrating.model.entity.Genre;
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
        this.serviceFactory = ServiceManager.getServiceFactory(ServiceType.FINAL_SERVICE);
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
