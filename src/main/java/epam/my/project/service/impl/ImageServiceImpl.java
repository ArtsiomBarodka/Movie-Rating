package epam.my.project.service.impl;

import epam.my.project.configuration.ImageCategory;
import epam.my.project.exception.InternalServerErrorException;
import epam.my.project.service.ImageService;
import epam.my.project.util.DataUtil;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageServiceImpl implements ImageService {
    private final String mediaDirParent = "parent root";

//    ImageServiceImpl(ServiceManager serviceManager) {
//        this.mediaDirParent = normalizeMediaDirPath(serviceManager.applicationContext.getRealPath("/"));
//    }

    private String normalizeMediaDirPath(String path) {
        path = path.replace("\\", "/");
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return path;
    }

    private void resize(String inputImagePath,
                         String outputImagePath,
                         int scaledWidth,
                         int scaledHeight) throws InternalServerErrorException {
        try {
            // reads input image
            File inputFile = new File(inputImagePath);
            BufferedImage inputImage = null;
            inputImage = ImageIO.read(inputFile);

            // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());

            // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            // extracts extension of output file
            String formatName = outputImagePath.substring(outputImagePath
                    .lastIndexOf(".") + 1);

            // writes to output file
            ImageIO.write(outputImage, formatName, new File(outputImagePath));
        }catch (IOException e) {
            throw new InternalServerErrorException("Can`t resize image file: " + e.getMessage(), e);
        }
    }

    private String downloadImage(InputStream in, ImageCategory imageCategory) throws InternalServerErrorException {
        try {
            String uid = DataUtil.generateUniqueImageName();
            String fullImgPath = mediaDirParent + imageCategory.getRoot() + uid;

            File file = new File(fullImgPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Files.copy(in, Paths.get(fullImgPath));

            resize(fullImgPath, fullImgPath, imageCategory.getWidth(), imageCategory.getHeight());

            return imageCategory.getRoot() + uid;
        } catch (IOException e) {
            throw new InternalServerErrorException("Can`t copy image file: " + e.getMessage(), e);
        }
    }

    @Override
    public String downloadImageFromUrl(String url, ImageCategory imageCategory) throws InternalServerErrorException {
        if (url != null) {
            try (InputStream in = new URL(url).openStream()) {
                return downloadImage(in, imageCategory);
            } catch (IOException e) {
                throw new InternalServerErrorException("Can`t download image from url: " + e.getMessage(), e);
            }
        } else {
            return null;
        }
    }

    @Override
    public String downloadImageFromStorage(InputStream in, ImageCategory imageCategory) throws InternalServerErrorException {
        if(in != null){
            return downloadImage(in, imageCategory);
        }
        return null;
    }

    @Override
    public boolean deleteImage(String path) {
        if (path != null) {
            File image = new File(mediaDirParent + path);
            if (image.exists()) {
                if (image.delete()) {
                    return true;
                } else {
//                    LOGGER.error("Can't delete file: " + avatar.getAbsolutePath());
                }
            }
        }
        return false;
    }
}
