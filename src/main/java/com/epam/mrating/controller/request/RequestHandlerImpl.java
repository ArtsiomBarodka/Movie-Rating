package com.epam.mrating.controller.request;

import com.epam.mrating.configuration.Constants;
import com.epam.mrating.controller.command.CommandProvider;
import com.epam.mrating.controller.command.FrontCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * The type Request handler.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
final class RequestHandlerImpl implements RequestHandler {
    private CommandProvider commandProvider;

    /**
     * Instantiates a new Request handler.
     *
     * @param commandProvider the command provider
     */
    RequestHandlerImpl(CommandProvider commandProvider) {
        this.commandProvider = commandProvider;
    }

    @Override
    public FrontCommand getCommand(HttpServletRequest request) {
        String contextPath = request.getServletContext().getContextPath();
        String commandName = request.getRequestURI().substring(contextPath.length());
        FrontCommand command = commandProvider.getCommand(commandName);

        if(Objects.nonNull(command)){
            return command;
        }

        if (commandName.contains("/") && commandName.length() > 2){
            commandName = commandName.substring(0, commandName.lastIndexOf('/')).concat("/*");
            command = commandProvider.getCommand(commandName);
            if(Objects.nonNull(command)){
                return command;
            }
        }

        return commandProvider.getCommand(Constants.NOT_FOUND_COMMAND);
    }
}
