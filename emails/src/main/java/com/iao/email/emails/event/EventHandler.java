package com.iao.email.emails.event;

import com.iao.email.emails.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventHandler {

    @Autowired
    public EmailService emailService;
    public void HandleEvent(EmailEvent event)
    {
        String to = event.getData().email;
        String subject = "";
        String body = "";
        switch (event.getUserEventType()) {
            case UserAddedToTraining:
                subject = "Update regarding your training";
                body = "you are added to the training " + event.getData().trainingName;
                break;
            case TrainingIsDeleted:
                subject = "Your training is deleted";
                body = "The taraining " + event.getData().trainingName + " is deleted";
                break;
            case UserRemovedFromTraining:
                subject = "Update regarding your training";
                body = "You are removed from the training " + event.getData().trainingName ;
                break;
            case TrainingIsUpdated:
                subject = "Update regarding your training";
                body = "Your training is changed to" + event.getData().trainingName ;
                break;
            default:
                System.out.println("no email for the event");
        }

        if(subject != "" && body != "")
        {
            emailService.sendEmail(to, subject, body);
        }

    }
}
