package epam.my.project.controller.request;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.impl.CommandProvider;
import epam.my.project.controller.command.FrontCommand;
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
        String commandName = request.getRequestURI();
        FrontCommand command = commandProvider.getCommand(commandName);
        if(Objects.nonNull(command)){
            return command;
        }

        if (commandName.contains("/") && commandName.length() > 2){
            commandName = commandName.substring(0, commandName.lastIndexOf("/")).concat("/*");
            command = commandProvider.getCommand(commandName);
            if(Objects.nonNull(command)){
                return command;
            }
        }

        return commandProvider.getCommand(Constants.NOT_FOUND_COMMAND);
    }
}
