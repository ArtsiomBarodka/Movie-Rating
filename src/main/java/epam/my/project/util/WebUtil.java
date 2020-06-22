package epam.my.project.util;

import epam.my.project.configuration.Constants;
import epam.my.project.exception.ValidationException;
import epam.my.project.model.domain.AccountDetails;
import epam.my.project.model.domain.SocialAccount;
import epam.my.project.model.validation.Violations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Objects;

public class WebUtil {
    public static void setMessage(HttpServletRequest request, String message){
        request.setAttribute(Constants.MESSAGE, message);
    }

    public static void setViolations(HttpServletRequest request, Violations v){
        request.setAttribute(Constants.VIOLATIONS, v);
    }

    public static void setLocale(HttpServletRequest request, String locale){
        request.getSession().setAttribute(Constants.LOCALE, locale);
    }

    public static boolean isLocaleCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(Constants.LOCALE));
    }

    public static boolean isCurrentAccountDetailsCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(Constants.CURRENT_ACCOUNT_DETAILS));
    }

    public static AccountDetails getCurrentAccountDetails(HttpServletRequest request){
        return (AccountDetails) request.getSession().getAttribute(Constants.CURRENT_ACCOUNT_DETAILS);
    }

    public static void setCurrentAccountDetails(HttpServletRequest request, AccountDetails accountDetails){
       request.getSession().setAttribute(Constants.CURRENT_ACCOUNT_DETAILS, accountDetails);
    }

    public static boolean isCurrentSocialAccountCreated(HttpServletRequest request){
        return Objects.nonNull(request.getSession().getAttribute(Constants.SOCIAL_ACCOUNT));
    }

    public static SocialAccount getCurrentSocialAccount(HttpServletRequest request){
        return (SocialAccount) request.getSession().getAttribute(Constants.SOCIAL_ACCOUNT);
    }

    public static void setCurrentSocialAccount(HttpServletRequest request, SocialAccount socialAccount){
        request.getSession().setAttribute(Constants.SOCIAL_ACCOUNT, socialAccount);
    }

    public static void clearCurrentAccountDetails(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Constants.CURRENT_ACCOUNT_DETAILS);
        request.getSession().removeAttribute(Constants.SOCIAL_ACCOUNT);
        setCookie(Constants.VALIDATOR, null, 0, response);
        setCookie(Constants.SELECTOR, null, 0, response);
    }

    public static boolean hasSelectorCookie(HttpServletRequest request){
        return Objects.nonNull(getCookie(request, Constants.SELECTOR));
    }

    public static void setSelectorCookie(HttpServletResponse response, String selector){
        setCookie(Constants.SELECTOR, selector, Constants.MAX_COOKIE_AGE, response);
    }

    public static void setValidatorCookie(HttpServletResponse response, String validator){
        setCookie(Constants.VALIDATOR, validator, Constants.MAX_COOKIE_AGE, response);
    }

    public static Cookie getSelectorCookie(HttpServletRequest request){
        return getCookie(request, Constants.SELECTOR);
    }

    public static Cookie getValidatorCookie(HttpServletRequest request){
        return getCookie(request, Constants.VALIDATOR);
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



}
