package org.example.agenticai.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    // 🔔 REMINDER EMAIL (Purple Theme)
    public void sendReminderEmail(String to, String message) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject("🔔 Reminder: " + message);

            String htmlContent =
                    "<html>" +
                    "<head><meta charset='UTF-8'></head>" +
                    "<body bgcolor='#0d1117' style='margin:0; padding:0; background-color:#0d1117 !important; font-family:Arial,sans-serif;'>" +

                    "<table width='100%' cellpadding='0' cellspacing='0' border='0' style='background-color:#0d1117 !important;'>" +
                    "<tr><td align='center'>" +

                    "<table width='600' cellpadding='0' cellspacing='0' border='0' " +
                    "style='margin:20px auto; background:#161b22; border-radius:16px; overflow:hidden; box-shadow:0 4px 20px rgba(0,0,0,0.5);'>" +

                    // Header with purple gradient
                    "<tr>" +
                    "<td style='padding:40px 30px; text-align:center; background:linear-gradient(135deg,#a66dd0,#7d3f7f); color:white;'>" +
                    "<h1 style='margin:0; font-size:32px;'>🔔 REMINDER</h1>" +
                    "<p style='margin:10px 0 0; font-size:14px; opacity:0.9;'>You have a task waiting</p>" +
                    "</td>" +
                    "</tr>" +

                    // Message section
                    "<tr>" +
                    "<td style='padding:30px; color:#c9d1d9;'>" +

                    "<div style='background:#0d1117; border-left:5px solid #a66dd0; padding:20px; border-radius:8px; margin:15px 0;'>" +
                    "<p style='margin:0; color:#8b949e; font-size:12px; text-transform:uppercase; letter-spacing:1px;'>Task</p>" +
                    "<h2 style='margin:8px 0 0; color:#e7c4f5; font-size:18px;'>✨ " + message + "</h2>" +
                    "</div>" +

                    // Button
                    "<div style='margin-top:25px; text-align:center;'>" +
                    "<a href='http://localhost:3000' style='padding:12px 30px; background:#a66dd0; color:white; text-decoration:none; border-radius:6px; font-weight:bold; display:inline-block; margin-bottom:20px;'>OPEN DASHBOARD</a>" +
                    "</div>" +

                    "</td>" +
                    "</tr>" +

                    // Footer
                    "<tr>" +
                    "<td style='text-align:center; padding:20px; background:#0d1117; color:#8b949e; font-size:12px; border-top:1px solid #30363d;'>" +
                    "⏰ AI Reminder System" +
                    "</td>" +
                    "</tr>" +

                    "</table>" +

                    "</td></tr>" +
                    "</table>" +

                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);

            System.out.println("📧 Reminder email sent!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🌤️ WEATHER EMAIL (Cyan Theme)
    public void sendWeatherEmail(String to, String city, double temperature) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("🌤️ Weather Update: " + city);

            String htmlContent =
                    "<html>" +
                    "<head><meta charset='UTF-8'></head>" +
                    "<body bgcolor='#0d1117' style='margin:0; padding:0; background-color:#0d1117 !important; font-family:Arial,sans-serif;'>" +

                    "<table width='100%' cellpadding='0' cellspacing='0' border='0' style='background-color:#0d1117 !important;'>" +
                    "<tr><td align='center'>" +

                    "<table width='600' cellpadding='0' cellspacing='0' border='0' " +
                    "style='margin:20px auto; background:#161b22; border-radius:16px; overflow:hidden; box-shadow:0 4px 20px rgba(0,0,0,0.5);'>" +

                    // Header with cyan gradient
                    "<tr>" +
                    "<td style='padding:40px 30px; text-align:center; background:linear-gradient(135deg,#00d4ff,#0099cc); color:white;'>" +
                    "<h1 style='margin:0; font-size:32px;'>🌤️ WEATHER UPDATE</h1>" +
                    "<p style='margin:10px 0 0; font-size:14px; opacity:0.9;'>Current conditions in your area</p>" +
                    "</td>" +
                    "</tr>" +

                    // Content section
                    "<tr>" +
                    "<td style='padding:30px; color:#c9d1d9; text-align:center;'>" +

                    "<h2 style='margin:0 0 20px; color:#00d4ff; font-size:24px;'>📍 " + city + "</h2>" +

                    // Temperature display with cyan theme
                    "<div style='background:#0d1117; border:3px solid #00d4ff; border-radius:16px; padding:30px; margin:20px 0;'>" +
                    "<h1 style='margin:0; color:#00d4ff; font-size:48px; font-weight:bold;'>" + String.format("%.1f", temperature) + "°</h1>" +
                    "<p style='margin:10px 0 0; color:#8b949e; font-size:14px;'>Celsius</p>" +
                    "</div>" +

                    // Details info
                    "<div style='background:#0d1117; border-left:5px solid #00d4ff; padding:15px; margin:20px 0; text-align:left; border-radius:8px;'>" +
                    "<p style='margin:5px 0; color:#c9d1d9;'><strong style=\"color:#00d4ff;\">Temperature:</strong> " + String.format("%.1f", temperature) + "°C</p>" +
                    "<p style='margin:5px 0; color:#c9d1d9;'><strong style=\"color:#00d4ff;\">Status:</strong> " + (temperature > 30 ? "🔥 Hot" : (temperature > 20 ? "😊 Pleasant" : "❄️ Cold")) + "</p>" +
                    "</div>" +

                    // Button
                    "<div style='margin-top:25px; text-align:center;'>" +
                    "<a href='http://localhost:3000' style='padding:12px 30px; background:#00d4ff; color:#0d1117; text-decoration:none; border-radius:6px; font-weight:bold; display:inline-block;'>VIEW FULL FORECAST</a>" +
                    "</div>" +

                    "</td>" +
                    "</tr>" +

                    // Footer
                    "<tr>" +
                    "<td style='text-align:center; padding:20px; background:#0d1117; color:#8b949e; font-size:12px; border-top:1px solid #30363d;'>" +
                    "🌐 AI Weather System" +
                    "</td>" +
                    "</tr>" +

                    "</table>" +

                    "</td></tr>" +
                    "</table>" +

                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(message);

            System.out.println("📧 Weather email sent!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🚨 ALERT EMAIL (Red/Orange Theme)
    public void sendAlertEmail(String to, String city, double currentTemp, double threshold, double excess) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("🚨 Temperature Alert: " + city);

            String htmlContent =
                    "<html>" +
                    "<head><meta charset='UTF-8'></head>" +
                    "<body bgcolor='#0d1117' style='margin:0; padding:0; background-color:#0d1117 !important; font-family:Arial,sans-serif;'>" +

                    "<table width='100%' cellpadding='0' cellspacing='0' border='0' style='background-color:#0d1117 !important;'>" +
                    "<tr><td align='center'>" +

                    "<table width='600' cellpadding='0' cellspacing='0' border='0' " +
                    "style='margin:20px auto; background:#161b22; border-radius:16px; overflow:hidden; box-shadow:0 4px 20px rgba(0,0,0,0.5);'>" +

                    // Header with red/orange gradient
                    "<tr>" +
                    "<td style='padding:40px 30px; text-align:center; background:linear-gradient(135deg,#ff4757,#ff7b54); color:white;'>" +
                    "<h1 style='margin:0; font-size:32px;'>⚠️ TEMPERATURE ALERT</h1>" +
                    "<p style='margin:10px 0 0; font-size:14px; opacity:0.9;'>THRESHOLD EXCEEDED</p>" +
                    "</td>" +
                    "</tr>" +

                    // Content section
                    "<tr>" +
                    "<td style='padding:30px; color:#c9d1d9;'>" +

                    "<h2 style='color:#ff7b54; text-align:center; margin:0 0 25px;'>📍 " + city + "</h2>" +

                    // Temperature display
                    "<table width='100%' cellpadding='0' cellspacing='0' border='0'>" +
                    "<tr>" +
                    "<td style='background:#0d1117; border:3px solid #ff4757; border-radius:12px; padding:25px; text-align:center;'>" +
                    "<p style='margin:0; color:#8b949e; font-size:12px; text-transform:uppercase;'>Current Temperature</p>" +
                    "<h1 style='margin:10px 0 0; color:#ff4757; font-size:44px; font-weight:bold;'>" + String.format("%.1f", currentTemp) + "°</h1>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +

                    // Alert details
                    "<div style='background:#0d1117; border-left:5px solid #ff7b54; padding:20px; margin:20px 0; border-radius:8px;'>" +
                    "<p style='margin:10px 0; color:#c9d1d9;'>" +
                    "<strong style=\"color:#ff7b54;\">⚠️ Threshold Set:</strong> " + String.format("%.1f", threshold) + "°C" +
                    "</p>" +
                    "<p style='margin:10px 0; color:#c9d1d9;'>" +
                    "<strong style=\"color:#ff4757;\">🔥 Excess Temperature:</strong> <span style='font-size:18px;'>+" + String.format("%.1f", excess) + "°C</span>" +
                    "</p>" +
                    "</div>" +

                    // Status indicator
                    "<div style='background:#ff4757; opacity:0.1; border:2px dashed #ff4757; border-radius:8px; padding:15px; text-align:center; margin:20px 0;'>" +
                    "<p style='margin:0; color:#ff7b54; font-weight:bold;'>🌡️ IMMEDIATE ACTION RECOMMENDED</p>" +
                    "</div>" +

                    // Button
                    "<div style='margin-top:25px; text-align:center;'>" +
                    "<a href='http://localhost:3000' style='padding:12px 30px; background:#ff4757; color:white; text-decoration:none; border-radius:6px; font-weight:bold; display:inline-block;'>VIEW ALERT DETAILS</a>" +
                    "</div>" +

                    "</td>" +
                    "</tr>" +

                    // Footer
                    "<tr>" +
                    "<td style='text-align:center; padding:20px; background:#0d1117; color:#8b949e; font-size:12px; border-top:1px solid #30363d;'>" +
                    "🚨 AI Weather Alert System" +
                    "</td>" +
                    "</tr>" +

                    "</table>" +

                    "</td></tr>" +
                    "</table>" +

                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(message);

            System.out.println("📧 Alert email sent for: " + city);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 📧 GENERIC EMAIL (Green Theme)
    public void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);

            String htmlContent =
                    "<html>" +
                    "<head><meta charset='UTF-8'></head>" +
                    "<body bgcolor='#0d1117' style='margin:0; padding:0; background-color:#0d1117 !important; font-family:Arial,sans-serif;'>" +

                    "<table width='100%' cellpadding='0' cellspacing='0' border='0' style='background-color:#0d1117 !important;'>" +
                    "<tr><td align='center'>" +

                    "<table width='600' cellpadding='0' cellspacing='0' border='0' " +
                    "style='margin:20px auto; background:#161b22; border-radius:16px; overflow:hidden; box-shadow:0 4px 20px rgba(0,0,0,0.5);'>" +

                    // Header with green gradient
                    "<tr>" +
                    "<td style='padding:40px 30px; text-align:center; background:linear-gradient(135deg,#3fb950,#238636); color:white;'>" +
                    "<h1 style='margin:0; font-size:32px;'>" + subject + "</h1>" +
                    "</td>" +
                    "</tr>" +

                    // Content
                    "<tr>" +
                    "<td style='padding:30px; color:#c9d1d9; line-height:1.8;'>" +
                    "<div style='background:#0d1117; border-left:5px solid #3fb950; padding:20px; border-radius:8px;'>" +
                    body.replaceAll("<p>", "<p style='margin:15px 0; color:#c9d1d9;'>").replaceAll("</p>", "</p>") +
                    "</div>" +

                    // Button
                    "<div style='margin-top:25px; text-align:center;'>" +
                    "<a href='http://localhost:3000' style='padding:12px 30px; background:#3fb950; color:white; text-decoration:none; border-radius:6px; font-weight:bold; display:inline-block;'>OPEN DASHBOARD</a>" +
                    "</div>" +

                    "</td>" +
                    "</tr>" +

                    // Footer
                    "<tr>" +
                    "<td style='text-align:center; padding:20px; background:#0d1117; color:#8b949e; font-size:12px; border-top:1px solid #30363d;'>" +
                    "✅ AI Notification System" +
                    "</td>" +
                    "</tr>" +

                    "</table>" +

                    "</td></tr>" +
                    "</table>" +

                    "</body>" +
                    "</html>";

            helper.setText(htmlContent, true);
            mailSender.send(message);

            System.out.println("📧 Email sent: " + subject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}