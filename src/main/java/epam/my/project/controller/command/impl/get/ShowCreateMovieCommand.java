package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.util.ViewUtil;
import javax.servlet.ServletException;
import java.io.IOException;

public class ShowCreateMovieCommand extends FrontCommand {
    private static final long serialVersionUID = -2455271623581103434L;

    @Override
    public void execute() throws IOException, ServletException, InternalServerErrorException, ObjectNotFoundException {
        ViewUtil.forwardToPage("page/create-movie.jsp",request,response);
    }
}
