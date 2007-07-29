package be.eonconsult.clouddetect.web.pages;

import org.apache.tapestry.annotations.Inject;

import persistence.dao.UserDao;
import persistence.model.User;
import be.eonconsult.clouddetect.web.data.RegisterForm;
import be.eonconsult.clouddetect.web.services.PasswordService;

public class Register {
	
	RegisterForm registerForm;
	
	@Inject
	private UserDao userDao;

	public RegisterForm getRegisterForm() {
		return registerForm;
	}

	public void setRegisterForm(RegisterForm registerForm) {
		this.registerForm = registerForm;
	}
	

	public String onSuccess() {
		
		try {
			String hash1 = PasswordService.getInstance().encrypt(registerForm.getPassword());
			if(hash1.equals(PasswordService.getInstance().encrypt(registerForm.getPassword2()))) {
				User user = new User();
				user.setUserName(registerForm.getUsername());
				user.setEmail(registerForm.getEmail());
				user.setFirstName(registerForm.getFirstName());
				user.setLastName(registerForm.getLastName());
				user.setPassword(hash1);
				user.setAdmin(false);
				user.setPremium(false);
				userDao.save(user);
				
				return "Start";
			} else {
				System.out.println("hashes not equal");
				return "Register";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Register";
		}
		
	}

}
