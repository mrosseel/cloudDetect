package notification;

import media.image.CloudImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import util.DateUtil;

public class MailNotify implements Notifier {

    private static Log log = LogFactory.getLog(MailNotify.class);

    private MailSender mailSender;

    private SimpleMailMessage mailMessage;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMailMessage(SimpleMailMessage message) {
        this.mailMessage = message;
    }
    
	/* (non-Javadoc)
     * @see notification.Notifier#notify(media.image.CloudImage)
     */
    public void notify(CloudImage image) {
        log.info("Sending mail at " + image.getMetaData().getDate());
        sendMail();
    }
    
    public void sendMail() {
        // Create a thread safe "sandbox" of the message
        SimpleMailMessage msg = new SimpleMailMessage(this.mailMessage);
        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            log.error(ex.getMessage());
        }
    }
}