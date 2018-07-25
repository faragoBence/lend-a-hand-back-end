package com.codecool.web;

import com.codecool.web.model.EmployeeRating;
import com.codecool.web.model.EmployerRating;
import com.codecool.web.model.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.Properties;

public class Utility {

    public static BigDecimal evaluateEmployeeRating(User user) {
        int numOfRatings = user.getEmployeeRatings().size();
        System.out.println(numOfRatings);
        if (numOfRatings != 0) {
            double totalRatingValue = 0;
            for (EmployeeRating rating : user.getEmployeeRatings()) {
                totalRatingValue += rating.getRating();
            }
            return new BigDecimal(totalRatingValue / numOfRatings);
        } else {
            return new BigDecimal(0);
        }
    }


    public static BigDecimal evaluateEmployerRating(User user) {
        int numOfRatings = user.getEmployerRatings().size();
        System.out.println(numOfRatings);
        if (numOfRatings != 0) {
            int totalRatingValue = 0;
            for (EmployerRating rating : user.getEmployerRatings()) {
                totalRatingValue += rating.getRating();
            }
            return new BigDecimal(totalRatingValue / numOfRatings);
        } else {
            return new BigDecimal(0);
        }
    }

    public static void sendEmail(User user) {

        final String username = "lendahand.contact@gmail.com";
        final String password = "Jancsika13";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("reminder.myschedule@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));
            message.setSubject("Account verification");
            message.setText(String.format("Dear %s,\n\nYou are successfully registered to the Lend a hand website.\n\nYou must verificate your account using the code below.\n\nVerification code: %s ", user.getUserName(), user.getVerificationCode()));
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}