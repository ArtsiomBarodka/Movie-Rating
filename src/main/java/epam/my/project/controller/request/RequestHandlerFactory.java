package epam.my.project.controller.request;

import epam.my.project.controller.command.impl.CommandProvider;

/**
 * The enum Request handler factory.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public enum RequestHandlerFactory {
    /**
     * Instance request handler factory.
     */
    INSTANCE;

    private RequestHandler requestHandler;

    RequestHandlerFactory(){
        init();
    }

    private void init() {
        this.requestHandler = new RequestHandlerImpl(new CommandProvider());
    }

    /**
     * Gets request handler.
     *
     * @return the request handler
     */
    public RequestHandler getRequestHandler() {
        return requestHandler;
    }
}
