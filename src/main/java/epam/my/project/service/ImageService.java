package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;
import epam.my.project.exception.InternalServerErrorException;
import java.io.InputStream;

public interface ImageService {
    String downloadImageFromStorage(String mediaDirParent, InputStream in, ImageCategory imageCategory) throws  InternalServerErrorException;
}
