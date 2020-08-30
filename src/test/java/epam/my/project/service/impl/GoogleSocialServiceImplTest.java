package epam.my.project.service.impl;

import epam.my.project.service.exception.RetrieveSocialAccountFailedException;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

public class GoogleSocialServiceImplTest {
    private GoogleSocialServiceImpl googleSocialService;

    @Before
    public void setUp() throws Exception {
        String appId;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = loader.getResourceAsStream("application.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            appId = properties.getProperty("social.google.app-id");
        }
        googleSocialService = new GoogleSocialServiceImpl(appId);
    }

    @Test(expected = RetrieveSocialAccountFailedException.class)
    public void getSocialAccount_SHOULD_THROW_EXCEPTION() throws RetrieveSocialAccountFailedException {
        googleSocialService.getSocialAccount("");
    }
}