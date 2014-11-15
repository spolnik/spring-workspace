package com.apress.isf.spring.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component("jmsProducer")
public class JMSProducer {

    @Value("classpath:META-INF/data/jms.txt")
    private Resource jmsTxt;

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send() {
        jmsTemplate.send(session -> session.createTextMessage(getMessage()));
    }

    private String getMessage() {
        final StringBuilder message = new StringBuilder();

        try {
            InputStream stream = jmsTxt.getInputStream();
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()) {
                message.append(scanner.nextLine());
            }
            scanner.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message.toString();
    }
}
