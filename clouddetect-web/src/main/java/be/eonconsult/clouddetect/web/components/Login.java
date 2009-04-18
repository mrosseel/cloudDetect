package be.eonconsult.clouddetect.web.components;

import org.apache.tapestry5.annotations.ApplicationState;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.UserData;
import be.eonconsult.clouddetect.web.pages.Start;
import be.eonconsult.clouddetect.web.services.PasswordService;

public class Login {
	@Persist
	private String userName;

	private String password;

	@Component
	private Form _form;

	@Inject
	private UserDao userDao;

	@ApplicationState
	private UserData userData;

	@OnEvent("submit")
	private Object doLogin() {

		User user = userDao.getUserByUserName(userName);
		PasswordService pwdService = PasswordService.getInstance();

		try {
			if (user != null && user.getPassword().equals(pwdService.encrypt(password))) {
				this.userData.setUserId(user.getId());
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

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}