/*
 * Created on 27-aug-2004
 *
 */
package media.web;

import java.net.Authenticator;
import java.util.Properties;

/**
 * Encapsulates all proxy settings.
 * 
 * @author mikers
 *  
 */
public class ProxyConfigurator {
    /** default port for proxies */
    private static String DEFAULT_PORT = "8080";
   
    /**
     * Configure proxy using a default port and no user/password.
     * 
     * @param host the url of the proxy host (e.g. "http://proxy.pandora.be")
     */
    public static void configure(String host) {
        configure(host, DEFAULT_PORT);
    }

    /**
     * Configure proxy without user/password.
     * @param host
     * @param port
     */
    public static void configure(String host, String port) {
        configure(host, port, null, null);
    }

    /**
     * Configure proxy with user/password.
     * @param host
     * @param port
     */
    public static void configure(String host, String port, String user, String password) {
        Authenticator.setDefault(new MyAuthenticator(user, password));
        Properties systemSettings = System.getProperties();
        systemSettings.put("proxySet", "true");
        systemSettings.put("proxyHost", host);
        systemSettings.put("proxyPort", port);
        System.setProperties(systemSettings);
    }

}