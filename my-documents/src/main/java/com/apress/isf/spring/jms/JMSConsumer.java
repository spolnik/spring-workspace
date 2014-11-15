package com.apress.isf.spring.jms;

import com.apress.isf.java.model.Document;
import com.apress.isf.java.utils.XmlUtils;
import com.apress.isf.spring.data.DocumentDAO;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class JMSConsumer implements MessageListener {

    @Inject
    private DocumentDAO documentDAO;

    @Override
    public void onMessage(Message message) {
        try {
            final TextMessage textMessage = (TextMessage) message;
            Document document =
                    XmlUtils.fromXML(textMessage.getText(), Document.class);
            documentDAO.save(document);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
