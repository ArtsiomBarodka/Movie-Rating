package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.Command;
import epam.my.project.service.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class EditMovieCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = 6482141577718818609L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory)  {
    }
}
