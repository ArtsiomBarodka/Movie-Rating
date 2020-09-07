package com.epam.mrating.service.impl;

import com.epam.mrating.configuration.ImageCategory;
import com.epam.mrating.service.exception.InternalServerErrorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.InputStream;

@RunWith(PowerMockRunner.class)
public class ImageServiceImplTest {
    private ImageServiceImpl imageService;

    @Before
    public void setUp() throws Exception {
        imageService = new ImageServiceImpl();
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDownloadImageFromStorage_NULL_PARAMETER_MEDIA_DIR_PARENT() throws InternalServerErrorException {
        String mediaDirParent = null;
        InputStream in = PowerMockito.mock(InputStream.class);
        ImageCategory imageCategory = ImageCategory.USER_PHOTO;

        imageService.downloadImageFromStorage(mediaDirParent, in, imageCategory);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDownloadImageFromStorage_NULL_PARAMETER_INPUT_STREAM() throws InternalServerErrorException {
        String mediaDirParent = "";
        InputStream in = null;
        ImageCategory imageCategory = ImageCategory.USER_PHOTO;

        imageService.downloadImageFromStorage(mediaDirParent, in, imageCategory);
    }

    @Test(expected = InternalServerErrorException.class)
    public void testDownloadImageFromStorage_NULL_PARAMETER_IMAGE_CATEGORY() throws InternalServerErrorException {
        String mediaDirParent = "";
        InputStream in = PowerMockito.mock(InputStream.class);
        ImageCategory imageCategory = null;

        imageService.downloadImageFromStorage(mediaDirParent, in, imageCategory);
    }

}