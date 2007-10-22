package notification;

import application.InstanceFactory;
import junit.framework.TestCase;

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
        MailNotify notify = InstanceFactory.getMailNotify();
        notify.sendMail(600);
    }

}
