package com.example.jmsSendXML;

import com.github.javafaker.Faker;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Random;

public class PersonFabric {

    private int counter;

    public PersonFabric() {
        counter = 0;
    }

    Person createPerson() {
        counter++;
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String city = faker.address().cityName();
        int age = new Random().nextInt(100);
        return new Person(counter, firstName, age, city);
    }

    String preparePersonXmlString(Person person) {
        String result = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter strWrt = new StringWriter();
            jaxbMarshaller.marshal(person, strWrt);
            jaxbMarshaller.marshal(person, System.out);
            result = strWrt.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

}
