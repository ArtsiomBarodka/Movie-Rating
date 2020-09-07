package com.epam.mrating.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * The type Data util.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class DataUtil {
    private static final String SECURITY_ALGORITHM = "SHA-256";
    private static final String SALT = "MOVIE_RATING_SALT";
    private static final String IMAGE_POSTFIX = ".jpg";

    /**
     * Generate secured password string.
     *
     * @param stringToHash the string to hash
     * @return the string
     */
    public static String generateSecuredPassword(String stringToHash){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance(SECURITY_ALGORITHM);
            //Add password bytes to digest
            md.update(getSalt());
            //Get the hash's bytes
            byte[] bytes = md.digest(stringToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            ///
        }
        return generatedPassword;
    }

    private static byte[] getSalt() {
        return SALT.getBytes();
    }

    /**
     * Generate u id string.
     *
     * @param name the name
     * @return the string
     */
    public static String generateUId(String name){
        return name.trim().replace(" ", "-");
    }

    /**
     * Generate random password string.
     *
     * @return the string
     */
    public static String generateRandomPassword(){
        return generateSecuredPassword(UUID.randomUUID().toString());
    }

    /**
     * Generate random string string.
     *
     * @return the string
     */
    public static String generateRandomString(){
        return UUID.randomUUID().toString();
    }

    /**
     * Generate unique image name string.
     *
     * @return the string
     */
    public static String generateUniqueImageName(){
        return UUID.randomUUID().toString() + IMAGE_POSTFIX;
    }

    private DataUtil(){}
}
