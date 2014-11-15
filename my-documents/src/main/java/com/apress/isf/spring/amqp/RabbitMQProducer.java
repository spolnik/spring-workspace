package com.apress.isf.spring.amqp;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.utils.XmlUtils;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("rabbitmqProducer")
public class RabbitMQProducer {

    @Inject
    private RabbitTemplate rabbitTemplate;

    public void send(Document document) {
        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("text/xml");
        byte[] body = XmlUtils.toXML(document).getBytes();
        Message message = new Message(body, messageProperties);
        rabbitTemplate.send(message);
    }
}
