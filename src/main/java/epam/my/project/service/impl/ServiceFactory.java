package epam.my.project.service.impl;

import epam.my.project.configuration.Constants;
import epam.my.project.dao.impl.jdbc.DAOFactory;
import epam.my.project.configuration.exception.ConfigException;
import epam.my.project.service.*;

/**
 * The enum Service factory.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public enum ServiceFactory {
    /**
     * Service factory instance service factory.
     */
    SERVICE_FACTORY_INSTANCE;

    private AuthenticateAndAuthorizationService authenticateAndAuthorizationService;
    private CommentService commentService;
    private EditMovieService editMovieService;
    private ImageService imageService;
    private SocialService googleSocialService;
    private SocialService facebookSocialService;
    private UserService userService;
    private ViewMovieService viewMovieService;

    private DAOFactory daoFactory;

    ServiceFactory(){
        init();
    }

    private void init() {
        this.daoFactory = DAOFactory.DAO_JDBC_FACTORY;

        this.authenticateAndAuthorizationService = new AuthenticateAndAuthorizationServiceImpl(daoFactory);
        this.commentService = new CommentServiceImpl(daoFactory);
        this.editMovieService = new EditMovieServiceImpl(daoFactory);
        this.imageService = new ImageServiceImpl();
        this.googleSocialService = new GoogleSocialServiceImpl();
        this.facebookSocialService = new FacebookSocialServiceImpl();
        this.userService = new UserServiceImpl(daoFactory);
        this.viewMovieService = new ViewMovieServiceImpl(daoFactory);
    }

    /**
     * Gets authenticate and authorization service.
     *
     * @return the authenticate and authorization service
     */
    public AuthenticateAndAuthorizationService getAuthenticateAndAuthorizationService() {
        return authenticateAndAuthorizationService;
    }

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    public CommentService getCommentService() {
        return commentService;
    }

    /**
     * Gets edit movie service.
     *
     * @return the edit movie service
     */
    public EditMovieService getEditMovieService() {
        return editMovieService;
    }

    /**
     * Gets image service.
     *
     * @return the image service
     */
    public ImageService getImageService() {
        return imageService;
    }

    /**
     * Gets social service.
     *
     * @param name the name
     * @return the social service
     */
    public SocialService getSocialService(String name) {
        switch (name){
            case Constants.FACEBOOK_SOCIAL:
                return facebookSocialService;

            case Constants.GOOGLE_SOCIAL:
                return googleSocialService;

            default:
                throw new ConfigException("Unsupported social service: " + name);
        }
    }

    /**
     * Gets user service.
     *
     * @return the user service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Gets view movie service.
     *
     * @return the view movie service
     */
    public ViewMovieService getViewMovieService() {
        return viewMovieService;
    }

    /**
     * Close.
     */
    public void close(){
        daoFactory.shutdownPool();
    }
}
