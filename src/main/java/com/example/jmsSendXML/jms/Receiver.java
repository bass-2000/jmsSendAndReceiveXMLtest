package com.example.jmsSendXML.jms;

import com.example.jmsSendXML.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.concurrent.CountDownLatch;


public class Receiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "my.frt.q")
    public void receive(String message) {
        LOGGER.info("received message='{}'", message);
        parsePerson(message);
    }

    Person parsePerson(String input) {
        Person person = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(input);
            person = (Person) jaxbUnmarshaller.unmarshal(reader);
            LOGGER.info("parsed person:'{}'", person);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return person;
    }
}
