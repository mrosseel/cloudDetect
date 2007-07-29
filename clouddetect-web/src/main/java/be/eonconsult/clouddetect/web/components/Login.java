package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry.annotations.ApplicationState;
import org.apache.tapestry.annotations.Component;
import org.apache.tapestry.annotations.Inject;
import org.apache.tapestry.annotations.Persist;
import org.apache.tapestry.corelib.components.PasswordField;
import org.apache.tapestry.services.Cookies;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.web.data.LoginForm;
import be.eonconsult.clouddetect.web.services.PasswordService;

public class Login
{
	@Persist
	private LoginForm loginForm = new LoginForm();
	
    @Inject
    private Cookies cookies;

    @Inject
    private UserDao userDao;
    
    @ApplicationState
    private User user;

 
    String onSuccess()
    {
    	User user = userDao.getUserByUserName(loginForm.getUsername());
    	PasswordService service = PasswordService.getInstance();
    	try {
			if(user != null && user.getPassword().equals(service.encrypt(loginForm.getPassword()))) {
				this.user = user;
				return "Start";	
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    	
//        if (false) //!_authenticator.isValid(_userName, _password))
//        {
//            _form.recordError(_passwordField, "Invalid user name or password.");
//            return null;
//        }
//
//        return "PostLogin";
    }


	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

}