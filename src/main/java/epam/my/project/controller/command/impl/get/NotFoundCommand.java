package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.ObjectNotFoundException;
import java.io.IOException;

public class NotFoundCommand extends FrontCommand {
    private static final long serialVersionUID = 3976717553386963417L;

    @Override
    public void execute() throws IOException, ObjectNotFoundException {
        throw new ObjectNotFoundException("Page not found!");
    }
}