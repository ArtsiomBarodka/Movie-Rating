package epam.my.project.controller.command.impl.get;

import epam.my.project.controller.command.Command;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public class EditUserCommandImpl implements Command, Serializable {
    private static final long serialVersionUID = 897207187978943000L;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) {
    }
}
