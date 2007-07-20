package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry.annotations.Component;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.corelib.components.Checkbox;
import org.apache.tapestry.corelib.components.Form;
import org.apache.tapestry.corelib.components.PasswordField;
import org.apache.tapestry.services.Cookies;

public class Login
{
    @Persist
    private String _userName;

    private String _password;
    
    @Inject
    private Cookies cookies;

//    @Inject
//    private UserAuthenticator _authenticator;

    @Component(id = "password")
    private PasswordField _passwordField;
    
    @Persist
//    @Component(id = "rememberme")
    private boolean rememberMe;

    @Component
    private Form _form;

    String onSuccess()
    {
        if (false) //!_authenticator.isValid(_userName, _password))
        {
            _form.recordError(_passwordField, "Invalid user name or password.");
            return null;
        }

        return "PostLogin";
    }

    public String getPassword()
    {
        return _password;
    }

    public void setPassword(String password)
    {
        _password = password;
    }

    public String getUserName()
    {
        return _userName;
    }

    public void setUserName(String userName)
    {
        _userName = userName;
    }

    public boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    
    
}