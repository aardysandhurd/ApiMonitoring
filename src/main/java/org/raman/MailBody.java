package org.raman;

import java.util.List;

public class MailBody {
//Return the message body for the email
    public String messageBody(List<String> failedApis) {
        StringBuilder failedApi = new StringBuilder();
        for(String api: failedApis) {
            failedApi.append("<li>").append(api).append("</li>");
        }
        return "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%); color: #ffffff; margin: 0; padding: 0; }" +
                ".container { max-width: 600px; margin: 40px auto; background: rgba(255,255,255,0.1); backdrop-filter: blur(10px); border-radius: 15px; box-shadow: 0 8px 32px rgba(0,0,0,0.3); overflow: hidden; }" +
                ".header { background: linear-gradient(45deg, #ff6b6b, #ee5a24); padding: 30px; text-align: center; }" +
                ".header h2 { margin: 0; font-size: 32px; font-weight: 300; letter-spacing: 2px; }" +
                ".alert-icon { display: inline-block; width: 40px; height: 40px; background: #ff6b6b; border-radius: 50%; text-align: center; line-height: 40px; font-size: 24px; font-weight: bold; margin-right: 10px; animation: glow 2s ease-in-out infinite alternate; }" +
                "@keyframes glow { from { text-shadow: 0 0 10px #ff6b6b, 0 0 20px #ff6b6b, 0 0 30px #ff6b6b; } to { text-shadow: 0 0 20px #ff6b6b, 0 0 30px #ff6b6b, 0 0 40px #ff6b6b; } }" +
                ".content { padding: 40px 30px; }" +
                ".alert-title { color: #ffd93d; font-size: 20px; margin-bottom: 20px; font-weight: 500; }" +
                ".api-list { background: rgba(255,255,255,0.05); border-radius: 10px; padding: 25px; margin: 20px 0; border-left: 4px solid #ffd93d; }" +
                ".api-list ul { margin: 0; padding-left: 20px; }" +
                ".api-list li { margin: 10px 0; line-height: 1.6; font-size: 16px; }" +
                ".footer { background: rgba(0,0,0,0.2); padding: 25px 30px; border-top: 1px solid rgba(255,255,255,0.1); }" +
                ".system-notice { background: rgba(255,107,107,0.1); border: 1px solid rgba(255,107,107,0.3); border-radius: 8px; padding: 20px; }" +
                ".system-notice p { margin: 0; font-size: 14px; color: #ffcccc; }" +
                ".timestamp { margin-top: 15px; font-size: 12px; color: #999; text-align: center; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='header'>" +
                "<h2><span class='alert-icon'>!</span> API MONITORING ALERT</h2>" +
                "</div>" +
                "<div class='content'>" +
                "<div class='alert-title'>🔍 The following APIs have failed:</div>" +
                "<div class='api-list'>" +
                "<ul>" + failedApi.toString() + "</ul>" +
                "</div>" +
                "</div>" +
                "<div class='footer'>" +
                "<div class='system-notice'>" +
                "<p><strong>⚠ IMPORTANT:</strong> This is an automated system-generated email from API Monitoring Service. Please do not reply to this message.</p>" +
                "<div class='timestamp'>Generated on: " + new java.util.Date() + "</div>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

    }
}
