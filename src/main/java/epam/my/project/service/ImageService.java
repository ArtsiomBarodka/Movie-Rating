package epam.my.project.service;

import epam.my.project.configuration.ImageCategory;
import epam.my.project.exception.InternalServerErrorException;
import java.io.InputStream;

public interface ImageService {
    String downloadImageFromUrl(String mediaDirParent, String url, ImageCategory imageCategory) throws InternalServerErrorException;

    String downloadImageFromStorage(String mediaDirParent, InputStream in, ImageCategory imageCategory) throws  InternalServerErrorException;

    boolean deleteImage(String mediaDirParent, String path);
}
