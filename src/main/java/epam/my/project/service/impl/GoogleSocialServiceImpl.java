package epam.my.project.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import epam.my.project.configuration.ResourceConfiguration;
import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.service.SocialService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleSocialServiceImpl implements SocialService {
    private String appId;

    public GoogleSocialServiceImpl() {
        this.appId = ResourceConfiguration.CONFIGURATION_INSTANCE.getGoogleAppId();
    }

    @Override
    public String getAuthorizeUrl() {
        return null;
    }

    @Override
    public SocialAccount getSocialAccount(String verificationCode) throws RetrieveSocialAccountFailedException {
        try {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(appId))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

            GoogleIdToken token = verifier.verify(verificationCode);
            return createSocialAccount(token.getPayload());
        } catch (GeneralSecurityException | IOException | RuntimeException e) {
            throw new RetrieveSocialAccountFailedException("Can`t get social account from google: " + e.getMessage(), e);
        }
    }

    private SocialAccount createSocialAccount(GoogleIdToken.Payload payload){
        SocialAccount socialAccount = new SocialAccount();
        socialAccount.setEmail(payload.getEmail());
        socialAccount.setName((String) payload.get("name"));
        return socialAccount;
    }
}
