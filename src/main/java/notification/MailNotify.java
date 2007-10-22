package notification;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailNotify {

    private static Log log = LogFactory.getLog(MailNotify.class);

    private MailSender mailSender;

    private SimpleMailMessage message;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }

    public void sendMail(int nrMinutes) {
        // Create a thread safe "sandbox" of the message
        SimpleMailMessage msg = new SimpleMailMessage(this.message);
        msg.setTo("Mike.Rosseel@gmail.com");
        msg
                .setText("Op " + new Date() + " is het " + nrMinutes + " minuten helder.\n\nMike");
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            log.error(ex.getMessage());
        }
    }
}