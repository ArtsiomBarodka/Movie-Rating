package epam.my.project.service.factory;

import epam.my.project.service.*;

/**
 * The interface Service factory.
 */
public interface ServiceFactory {
    /**
     * Gets authenticate and authorization service.
     *
     * @return the authenticate and authorization service
     */
    AuthenticateAndAuthorizationService getAuthenticateAndAuthorizationService();

    /**
     * Gets comment service.
     *
     * @return the comment service
     */
    CommentService getCommentService();

    /**
     * Gets edit movie service.
     *
     * @return the edit movie service
     */
    EditMovieService getEditMovieService();

    /**
     * Gets image service.
     *
     * @return the image service
     */
    ImageService getImageService();

    /**
     * Gets social service.
     *
     * @param name the name
     * @return the social service
     */
    SocialService getSocialService(String name);

    /**
     * Gets user service.
     *
     * @return the user service
     */
    UserService getUserService();

    /**
     * Gets view movie service.
     *
     * @return the view movie service
     */
    ViewMovieService getViewMovieService();

    /**
     * Close.
     */
    void close();
}
