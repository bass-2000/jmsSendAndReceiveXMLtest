package com.example.jmsSendXML;

import com.example.jmsSendXML.jms.Sender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class JmsSendXmlApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JmsSendXmlApplication.class, args);
        Sender sender = context.getBean(Sender.class);
        PersonFabric fabric = new PersonFabric();
        for (int i = 0; i < 10; i++) {
            sender.send(fabric.preparePersonXmlString(fabric.createPerson()));
        }
    }
}
