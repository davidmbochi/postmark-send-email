package com.javawhizz.sendEmail.service;

import com.javawhizz.sendEmail.model.CustomMessage;
import com.postmarkapp.postmark.client.ApiClient;

public interface EmailService {
    Boolean sendEmail(ApiClient client, CustomMessage customMessage);
}
