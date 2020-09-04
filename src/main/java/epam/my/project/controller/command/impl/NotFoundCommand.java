package epam.my.project.controller.command.impl;

import epam.my.project.service.exception.ObjectNotFoundException;
import java.io.IOException;

/**
 * The type Not found command.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class NotFoundCommand extends AbstractCommand {
    private static final long serialVersionUID = 3976717553386963417L;

    @Override
    public void execute() throws IOException, ObjectNotFoundException {
        throw new ObjectNotFoundException("Page not found!");
    }
}