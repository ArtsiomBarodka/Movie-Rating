package epam.my.project.controller.servlet;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.command.CommandProvider;
import epam.my.project.controller.command.FrontCommand;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/app/*")
public class FrontServlet extends AbstractServlet {
    private CommandProvider commandProvider;

    @Override
    public void init() throws ServletException {
        super.init();
        commandProvider = new CommandProvider();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if(!isMediaRequest(req) || !isStaticRequest(req)){
                FrontCommand command = getCommand(req);
                command.init(req, resp, serviceFactory, viewFactory);
                command.execute();
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    private FrontCommand getCommand(HttpServletRequest req) {
        String commandName = req.getRequestURI();
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

    private boolean isMediaRequest(HttpServletRequest request){
        String uri = request.getRequestURI();
        return uri.contains("media");
    }

    private boolean isStaticRequest(HttpServletRequest request){
        String uri = request.getRequestURI();
        return uri.contains("static");
    }

}
