package com.epam.mrating.service.impl;

import com.epam.mrating.configuration.Constants;
import com.epam.mrating.service.exception.RetrieveSocialAccountFailedException;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

import static org.junit.Assert.*;

public class FacebookSocialServiceImplTest {
    private FacebookSocialServiceImpl facebookSocialService;

    @Before
    public void setUp() throws Exception {
        String appId;
        String appSecret;
        String redirectUri;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = loader.getResourceAsStream("application.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            redirectUri = properties.getProperty("app.host") + Constants.REDIRECT_FROM_FB;
            appId = properties.getProperty("social.facebook.app-id");
            appSecret = properties.getProperty("social.facebook.secret");
        }
        facebookSocialService = new FacebookSocialServiceImpl(appId, appSecret, redirectUri);
    }

    @Test
    public void testGetAuthorizeUrl_SHOULD_RETURN_STRING() {
        String result = facebookSocialService.getAuthorizeUrl();

        assertNotNull(result);
    }

    @Test(expected = RetrieveSocialAccountFailedException.class)
    public void testGetSocialAccount_SHOULD_THROW_EXCEPTION() throws RetrieveSocialAccountFailedException {
        facebookSocialService.getSocialAccount("");
    }

}