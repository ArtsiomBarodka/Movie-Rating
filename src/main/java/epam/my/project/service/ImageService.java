package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;
import epam.my.project.service.exception.InternalServerErrorException;
import java.io.InputStream;

/**
 * The interface Image service.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public interface ImageService {
    /**
     * Download image from storage string.
     *
     * @param mediaDirParent the media dir parent
     * @param in             the in
     * @param imageCategory  the image category
     * @return the string
     * @throws InternalServerErrorException the internal server error exception
     */
    String downloadImageFromStorage(String mediaDirParent, InputStream in, ImageCategory imageCategory) throws  InternalServerErrorException;
}
