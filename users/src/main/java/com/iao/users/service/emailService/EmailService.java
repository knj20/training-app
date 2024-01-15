package com.iao.users.service.emailService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iao.users.event.EmailEvent;
import com.iao.users.event.EventData;
import com.iao.users.event.EventType;
import com.iao.users.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JmsTemplate jmsTenplate;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public EmailService(JmsTemplate jmsTemplate)
    {
        this.jmsTenplate = jmsTemplate;
    }

    public void sendEmail(String trainingName, String email, EventType eventType)
    {
        EmailEvent event = new EmailEvent();
        event.setUserEventType(eventType);
        event.setData(new EventData(email,trainingName));
        try {
            jmsTenplate.convertAndSend("test", objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
