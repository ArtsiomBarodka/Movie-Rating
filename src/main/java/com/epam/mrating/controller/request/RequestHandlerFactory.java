package com.epam.mrating.controller.request;

import com.epam.mrating.controller.command.CommandProvider;

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

    /**
     * Gets request handler.
     *
     * @param commandProvider the command provider
     * @return the request handler
     */
    public RequestHandler getRequestHandler(CommandProvider commandProvider) {
        return new RequestHandlerImpl(commandProvider);
    }
}
