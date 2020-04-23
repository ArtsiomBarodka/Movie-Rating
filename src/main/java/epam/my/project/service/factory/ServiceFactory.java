package epam.my.project.service.factory;

import epam.my.project.configuration.Constants;
import epam.my.project.dao.factory.DAOFactory;
import epam.my.project.exception.ConfigException;
import epam.my.project.service.*;
import epam.my.project.service.impl.*;

public enum ServiceFactory {
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

    public AuthenticateAndAuthorizationService getAuthenticateAndAuthorizationService() {
        return authenticateAndAuthorizationService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public EditMovieService getEditMovieService() {
        return editMovieService;
    }

    public ImageService getImageService() {
        return imageService;
    }

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

    public UserService getUserService() {
        return userService;
    }

    public ViewMovieService getViewMovieService() {
        return viewMovieService;
    }

    public void close(){
        daoFactory.shutdownPool();
    }
}
