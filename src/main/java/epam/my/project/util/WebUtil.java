package epam.my.project.util;

import epam.my.project.configuration.Constants;
import epam.my.project.controller.request.RequestAttributeNames;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.component.validator.model.Violations;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * The type Web util.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class WebUtil {
    /**
     * Set session redirect url after authenticate.
     *
     * @param request the request
     * @param url     the url
     */
    public static void setSessionRedirectUrlAfterAuthenticate(HttpServletRequest request, String url){
        request.getSession().setAttribute(RequestAttributeNames.REDIRECT_AFTER_AUTHENTICATE, url);
    }

    /**
     * Is session redirect url after authenticate created boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isSessionRedirectUrlAfterAuthenticateCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(RequestAttributeNames.REDIRECT_AFTER_AUTHENTICATE));
    }

    /**
     * Get session redirect url after authenticate string.
     *
     * @param request the request
     * @return the string
     */
    public static String getSessionRedirectUrlAfterAuthenticate(HttpServletRequest request){
        return (String) request.getSession().getAttribute(RequestAttributeNames.REDIRECT_AFTER_AUTHENTICATE);
    }


    /**
     * Set message.
     *
     * @param request the request
     * @param message the message
     */
    public static void setRequestMessage(HttpServletRequest request, String message){
        request.setAttribute(RequestAttributeNames.MESSAGE, message);
    }

    /**
     * Set violations.
     *
     * @param request the request
     * @param v       the v
     */
    public static void setRequestViolations(HttpServletRequest request, Violations v){
        request.setAttribute(RequestAttributeNames.VIOLATIONS, v);
    }

    /**
     * Set locale.
     *
     * @param request the request
     * @param locale  the locale
     */
    public static void setSessionLocale(HttpServletRequest request, String locale){
        request.getSession().setAttribute(RequestAttributeNames.LOCALE, locale);
    }

    /**
     * Is locale created boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isSessionLocaleCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(RequestAttributeNames.LOCALE));
    }

    /**
     * Is current account details created boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isSessionCurrentAccountDetailsCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(RequestAttributeNames.CURRENT_ACCOUNT_DETAILS));
    }

    /**
     * Get current account details account details.
     *
     * @param request the request
     * @return the account details
     */
    public static AccountDetails getSessionCurrentAccountDetails(HttpServletRequest request){
        return (AccountDetails) request.getSession().getAttribute(RequestAttributeNames.CURRENT_ACCOUNT_DETAILS);
    }

    /**
     * Set current account details.
     *
     * @param request        the request
     * @param accountDetails the account details
     */
    public static void setSessionCurrentAccountDetails(HttpServletRequest request, AccountDetails accountDetails){
       request.getSession().setAttribute(RequestAttributeNames.CURRENT_ACCOUNT_DETAILS, accountDetails);
    }

    /**
     * Is current social account created boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean isSessionCurrentSocialAccountCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(RequestAttributeNames.SOCIAL_ACCOUNT));
    }

    /**
     * Get current social account social account.
     *
     * @param request the request
     * @return the social account
     */
    public static SocialAccount getSessionCurrentSocialAccount(HttpServletRequest request){
        return (SocialAccount) request.getSession().getAttribute(RequestAttributeNames.SOCIAL_ACCOUNT);
    }

    /**
     * Set current social account.
     *
     * @param request       the request
     * @param socialAccount the social account
     */
    public static void setSessionCurrentSocialAccount(HttpServletRequest request, SocialAccount socialAccount){
        request.getSession().setAttribute(RequestAttributeNames.SOCIAL_ACCOUNT, socialAccount);
    }

    /**
     * Clear current account details.
     *
     * @param request  the request
     * @param response the response
     */
    public static void clearCurrentAccountDetails(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(RequestAttributeNames.CURRENT_ACCOUNT_DETAILS);
        request.getSession().removeAttribute(RequestAttributeNames.SOCIAL_ACCOUNT);
        request.getSession().removeAttribute(RequestAttributeNames.REDIRECT_AFTER_AUTHENTICATE);
        request.getSession().removeAttribute(RequestAttributeNames.LOCALE);
        setCookie(RequestAttributeNames.VALIDATOR, null, 0, response);
        setCookie(RequestAttributeNames.SELECTOR, null, 0, response);
    }

    /**
     * Has selector cookie boolean.
     *
     * @param request the request
     * @return the boolean
     */
    public static boolean hasSelectorCookie(HttpServletRequest request){
        return Objects.nonNull(getCookie(request, RequestAttributeNames.SELECTOR));
    }

    /**
     * Set selector cookie.
     *
     * @param response the response
     * @param selector the selector
     */
    public static void setSelectorCookie(HttpServletResponse response, String selector){
        setCookie(RequestAttributeNames.SELECTOR, selector, Constants.MAX_COOKIE_AGE, response);
    }

    /**
     * Set validator cookie.
     *
     * @param response  the response
     * @param validator the validator
     */
    public static void setValidatorCookie(HttpServletResponse response, String validator){
        setCookie(RequestAttributeNames.VALIDATOR, validator, Constants.MAX_COOKIE_AGE, response);
    }

    /**
     * Get selector cookie cookie.
     *
     * @param request the request
     * @return the cookie
     */
    public static Cookie getSelectorCookie(HttpServletRequest request){
        return getCookie(request, RequestAttributeNames.SELECTOR);
    }

    /**
     * Get validator cookie cookie.
     *
     * @param request the request
     * @return the cookie
     */
    public static Cookie getValidatorCookie(HttpServletRequest request){
        return getCookie(request, RequestAttributeNames.VALIDATOR);
    }

    private static void setCookie(String name, String value, int age, HttpServletResponse response) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(age);
        c.setPath("/");
        c.setHttpOnly(true);
        response.addCookie(c);
    }

    private static Cookie getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    if (c.getValue() != null && !"".equals(c.getValue())) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    private WebUtil(){}

}
