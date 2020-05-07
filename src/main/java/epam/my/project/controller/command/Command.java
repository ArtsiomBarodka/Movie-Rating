package epam.my.project.controller.command;
import epam.my.project.exception.ValidationException;
import epam.my.project.service.factory.ServiceFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
     void execute(HttpServletRequest request, HttpServletResponse response, ServiceFactory serviceFactory) throws IOException, ServletException, ValidationException;
}
