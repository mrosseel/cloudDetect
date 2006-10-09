package media.web;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Authenticator which stores the user and password.
 * 
 * @author mikers
 *  
 */
class MyAuthenticator extends Authenticator {
    /** user id */
    private String user;
    /** encrypted password */
    private char[] password;

    /**
     * Makes a new Authenticator with the given username and password
     * 
     * @param user		username, can include domain : "domain\\username"
     * @param password	the password (unencrypted)
     */
    public MyAuthenticator(String user, String password) {
        super();
        this.user = user;
        this.password = password.toCharArray();
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.user, this.password);
    }
}

