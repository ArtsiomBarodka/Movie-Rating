package epam.my.project.controller.command.impl.get;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import java.io.IOException;

public class DeleteCommentCommand extends FrontCommand {
    private static final long serialVersionUID = 8213066987208506460L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException {
        long id = Long.parseLong(request.getParameter("id"));
        String returnUrl = request.getParameter("return");
        serviceFactory.getCommentService().deleteComment(id);
        redirect(returnUrl);
    }

}

