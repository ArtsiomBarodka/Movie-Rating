package epam.my.project.service.impl;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import epam.my.project.configuration.ResourceConfiguration;
import epam.my.project.exception.RetrieveSocialAccountFailedException;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.service.SocialService;

public class FacebookSocialServiceImpl implements SocialService {
    private String appId;
    private String appSecret;
    private String redirectUri;

    public FacebookSocialServiceImpl() {
        this.appId = ResourceConfiguration.CONFIGURATION_INSTANCE.getFacebookAppId();
        this.appSecret = ResourceConfiguration.CONFIGURATION_INSTANCE.getFacebookSecret();
    }

    @Override
    public String getAuthorizeUrl() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder.addPermission(FacebookPermissions.EMAIL);
        FacebookClient client = new DefaultFacebookClient(Version.LATEST);
        return client.getLoginDialogUrl(appId, redirectUri, scopeBuilder);
    }

    @Override
    public SocialAccount getSocialAccount(String verificationCode) throws RetrieveSocialAccountFailedException {
        try {
            FacebookClient facebookClient = new DefaultFacebookClient(getAccessToken(verificationCode), Version.LATEST);
            User user = facebookClient.fetchObject("me", User.class,
                    Parameter.with("fields", "name,email"));
            return createSocialAccount(user);
        } catch (RuntimeException e){
            throw new RetrieveSocialAccountFailedException("Can`t get social account from facebook: " + e.getMessage(), e);
        }
    }

    private String getAccessToken(String verificationCode){
        FacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
        FacebookClient.AccessToken accessToken = facebookClient.obtainUserAccessToken(appId, appSecret, redirectUri, verificationCode);
        return accessToken.getAccessToken();
    }

    private SocialAccount createSocialAccount(User user){
        SocialAccount socialAccount = new SocialAccount();
        socialAccount.setEmail(user.getEmail());
        socialAccount.setName(user.getName());
        return socialAccount;
    }
}
