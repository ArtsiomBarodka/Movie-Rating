package epam.my.project.controller.command.impl.get;
import epam.my.project.controller.command.FrontCommand;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.exception.ObjectNotFoundException;
import epam.my.project.model.validation.ValidatorFactory;

import java.io.IOException;
import java.util.Optional;

public class DeleteCommentCommand extends FrontCommand {
    private static final long serialVersionUID = 8213066987208506460L;

    @Override
    public void execute() throws IOException, InternalServerErrorException, ObjectNotFoundException {
        Optional<String> returnUrl = Optional.ofNullable(request.getParameter("return"));
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        if(returnUrl.isPresent() && id.isPresent() && ValidatorFactory.IS_NUMBER_VALUE.validate(id.get())){
            serviceFactory.getCommentService().deleteComment(Long.parseLong(id.get()));
            redirect(returnUrl.get());
        } else {
            redirect("/app/movies");
        }
    }

}

