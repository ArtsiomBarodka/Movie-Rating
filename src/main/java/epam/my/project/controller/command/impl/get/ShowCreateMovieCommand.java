package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import javax.servlet.ServletException;
import java.io.IOException;

public class ShowCreateMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -2455271623581103434L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        viewFactory.getForwardToPage().init(request,response).render("page/create-movie.jsp");
    }
}
