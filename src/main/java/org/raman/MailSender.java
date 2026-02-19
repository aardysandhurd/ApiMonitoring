package org.raman;

import jakarta.mail.MessagingException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.raman.InitializingMailRequirements;

import java.util.List;

public class MailSender {
    private static final Logger logger = LogManager.getLogger(MailSender.class);
    
    public void reportFailedApis(List<String> failedApis) {
        if (failedApis == null || failedApis.isEmpty()) {
            logger.warn("No failed APIs to report");
            return;
        }
        
        logger.info("Sending email notification for {} failed APIs", failedApis.size());
        
        try {
            new InitializingMailRequirements().setMessage(failedApis);
            logger.info("Email notification sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send email notification: {}", e.getMessage(), e);
            throw new RuntimeException("Email sending failed", e);
        }
    }
}
