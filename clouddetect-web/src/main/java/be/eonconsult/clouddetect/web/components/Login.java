package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.web.pages.Start;
import be.eonconsult.clouddetect.web.services.PasswordService;

/*
 @Persist
 private LoginForm loginForm = new LoginForm();

 @Inject
 private Cookies cookies;


 String onSuccess() {
 User user = userDao.getUserByUserName(loginForm.getUsername());
 PasswordService service = PasswordService.getInstance();
 try {
 if (user != null && user.getPassword().equals(service.encrypt(loginForm.getPassword()))) {
 this.user = user;
 return "Start";
 }
 } catch (Exception e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
 }
 */
public class Login {
	@Persist
	private String userId;

	private String password;

	@Component
	private Form _form;

	@Inject
	private UserDao userDao;

	@ApplicationState
	private User user;

	@OnEvent("submit")
	private Object doLogin() {

		User user = userDao.getUserByUserName(userId);
		PasswordService pwdService = PasswordService.getInstance();

		try {
			if (user != null && user.getPassword().equals(pwdService.encrypt(password))) {
				this.user = user;
				return Start.class;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Stay on this page:
		_form.recordError("Invalid user name or password.");

		return null;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}