package org.raman;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.raman.abstractRes.GetProperty;

import java.util.List;
import java.util.Properties;

public class InitializingMailRequirements {
    private static final Logger logger = LogManager.getLogger(InitializingMailRequirements.class);
    private static final GetProperty getProperty = new GetProperty();
    
    public Properties seTProperties(){
        Properties prop = new Properties(); //Setting smtp properties
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", getProperty.getPropertyValue("host"));
        prop.put("mail.smtp.port", getProperty.getPropertyValue("port"));
        return prop;
    }

public Session sessionInit() {
    return Session.getInstance(seTProperties(), new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(getProperty.getPropertyValue("userName"), getProperty.getPropertyValue("password"));
        }
    });
}

public void setMessage(List<String> failedApis) throws MessagingException {
    logger.info("Preparing email notification for {} failed APIs", failedApis.size());
    
    Message message = new MimeMessage(sessionInit());
    message.setFrom(new InternetAddress(getProperty.getPropertyValue("recipients"))); //senders address for the mail
    message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(getProperty.getPropertyValue("from"))); //added internet address
    message.setSubject("Alert Apis Failed");
    message.setContent(new MailBody().messageBody(failedApis), "text/html");
    Transport.send(message);
    logger.info("Email sent successfully");
}
}
