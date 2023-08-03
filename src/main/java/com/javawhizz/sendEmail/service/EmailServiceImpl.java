package com.javawhizz.sendEmail.service;

import com.javawhizz.sendEmail.model.CustomMessage;
import com.postmarkapp.postmark.client.ApiClient;
import com.postmarkapp.postmark.client.data.model.message.Message;
import com.postmarkapp.postmark.client.exception.PostmarkException;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class EmailServiceImpl implements EmailService{
    private static final String FROM = "info@javawhizz.com";

    @Override
    public Boolean sendEmail(ApiClient client, CustomMessage customMessage) {
        boolean isMessageSent = false;
        Message message = new Message(
                FROM,
                customMessage.getTo(),
                customMessage.getSubject(),
                """
                        <!doctype html>
                        <html lang="en">
                          <head>                  
                            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" 
                            rel="stylesheet" 
                            integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
                             crossorigin="anonymous">
                          </head>
                          <body>
                          <p>%s</P                
                          <div class="card">
                            <div class="card-body">
                              <h5 class="card-title">JavaWhizz</h5>
                              <p class="card-text">
                              In this tutorial you will learn how to send emails in Spring Boot using Postmark 
                              </p>
                              <p class="card-text">
                              <small class="text-body-secondary">Last updated 3 mins ago
                              </small>
                              </p>
                            </div>
                            <img 
                            src="https://res.cloudinary.com/javawhizz/image/upload/v1690791222/Send_emails_in_Spring_Boot_using_Postmark_cytdkl.png" 
                            class="card-img-bottom" alt="...">
                          </div>
                            
                            <script 
                            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" 
                            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" 
                            crossorigin="anonymous"></script>
                          </body>
                        </html>
                                                    
                        """.formatted(customMessage.getMessage())
        );

        message.setMessageStream("announcements");

        try {
            if (client.deliverMessage(message)
                    .getMessageId() != null)
                isMessageSent = true;
        } catch (PostmarkException | IOException e) {
            throw new RuntimeException(e);
        }

        return isMessageSent;
    }
}
