package notification;

import static org.easymock.EasyMock.*;
import junit.framework.TestCase;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import application.InstanceFactory;

public class MailNotifyTest extends TestCase {

	public MailNotifyTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MailNotifyTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public void testMailing() {
		try {
			MailSender mock = createMock(org.springframework.mail.MailSender.class);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setText("test");
			mock.send(message);
			replay(mock);
			MailNotify notify = InstanceFactory.getMailNotify();
			notify.setMailSender(mock);
			notify.setMailMessage(message);
			notify.sendMail();
			verify(mock); // make sure the method is called
		} catch (Exception e) {
			fail();
		}
	}

}
