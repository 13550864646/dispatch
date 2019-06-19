package com.cloud.strategy;


import com.cloud.util.PropertiesReader;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.net.URLEncoder;

public class ObsDataMsgPublisher implements MessagePublisher {
    private static ObsDataMsgPublisher _instance = null;
    private static JmsTemplate jmsTemplate;

    private ObsDataMsgPublisher() {
    }

    public static synchronized ObsDataMsgPublisher getInstance() {
        if (_instance == null) {
            _instance = new ObsDataMsgPublisher();
            ActiveMQConnectionFactory mqFactory = new ActiveMQConnectionFactory();
            mqFactory.setBrokerURL(PropertiesReader.getProp("jms.url"));
            CachingConnectionFactory cachFactory = new CachingConnectionFactory(mqFactory);
            cachFactory.setSessionCacheSize(Integer.parseInt(PropertiesReader.getProp("jms.cachSessionNum")));
            jmsTemplate = new JmsTemplate(cachFactory);
            jmsTemplate.setPubSubDomain(false); // p2p 方式
            jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);//采用持久化方式
        }
        return _instance;
    }

    @Override
    public synchronized boolean sendByQuene(final String msg, final String queneName) {
        boolean ret = false;
        try {
            log.info(this.getClass().getSimpleName() + "--准备发送 JMS消息 queueName ：" + queneName);
            log.info(this.getClass().getSimpleName() + "-- msg:" + msg);
            jmsTemplate.setDefaultDestinationName(queneName);
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    try {
                        return session.createTextMessage(URLEncoder.encode(msg, "utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
            ret = true;
            log.info(this.getClass().getSimpleName() + "JMS 消息发送成功");
        } catch (JmsException e) {
            e.printStackTrace();
        }
        return ret;
    }

}

