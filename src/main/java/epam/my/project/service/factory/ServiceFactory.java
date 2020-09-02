package epam.my.project.service.factory;

import epam.my.project.service.*;

public interface ServiceFactory {
    AuthenticateAndAuthorizationService getAuthenticateAndAuthorizationService();
    CommentService getCommentService();
    EditMovieService getEditMovieService();
    ImageService getImageService();
    SocialService getSocialService(String name);
    UserService getUserService();
    ViewMovieService getViewMovieService();
    void close();
}
