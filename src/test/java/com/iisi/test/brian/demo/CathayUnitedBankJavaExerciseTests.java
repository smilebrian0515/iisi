package com.iisi.test.brian.demo;

import com.google.gson.Gson;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class CathayUnitedBankJavaExerciseTests {

    private static Gson gson = new Gson();

    @Test
    public void test_Q1() {
        String str = "{\n" +
                "\"menu\": {\n" +
                "\"id\": \"A\",\n" +
                "\"name\": \"A\",\n" +
                "\"menuitem\": [\n" +
                "{\n" +
                "\"id\": \"1\",\n" +
                "\"name\": \"1\"\n" +
                "},\n" +
                "{\n" +
                "\"id\": \"2\",\n" +
                "\"name\": \"2\"\n" +
                "},\n" +
                "{\n" +
                "\"id\": \"3\",\n" +
                "\"name\": \"3\"\n" +
                "}\n" +
                "]\n" +
                "}\n" +
                "}";

//        System.out.println(str);

        Jsonobj jsonobj = gson.fromJson(str, Jsonobj.class);
        Jsonobj.Menu actual = jsonobj.menu;

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.id).isEqualTo("A");
        softAssertions.assertThat(actual.name).isEqualTo("A");
        softAssertions.assertThat(actual.menuitem.get(0).id).isEqualTo("1");
        softAssertions.assertThat(actual.menuitem.get(0).name).isEqualTo("1");
        softAssertions.assertThat(actual.menuitem.get(1).id).isEqualTo("2");
        softAssertions.assertThat(actual.menuitem.get(1).name).isEqualTo("2");
        softAssertions.assertThat(actual.menuitem.get(2).id).isEqualTo("3");
        softAssertions.assertThat(actual.menuitem.get(2).name).isEqualTo("3");
        softAssertions.assertAll();
    }

    @Test
    public void test_Q2() throws JAXBException {
        String str = "<?xml version=\"1.0\" encoding=\"BIG-5\"?>\n" +
                "<note>\n" +
                "<to>YOU</to>\n" +
                "<from>ME</from>\n" +
                "<heading>Hello</heading>\n" +
                "\n" +
                "<body>Hello World</body>\n" +
                "<attachs>\n" +
                "<attach>\n" +
                "<name>1</name>\n" +
                "<content>2</content>\n" +
                "</attach>\n" +
                "<attach></attach>\n" +
                "<attach></attach>\n" +
                "</attachs>\n" +
                "</note>";

        System.out.println(str);

        JAXBContext jaxbContext = JAXBContext.newInstance(Xmlobj.Note.class);
        Unmarshaller jaxbContextUnmarshaller = jaxbContext.createUnmarshaller();
        Xmlobj.Note actual= (Xmlobj.Note)jaxbContextUnmarshaller.unmarshal(new StringReader(str));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.to).isEqualTo("YOU");
        softAssertions.assertThat(actual.from).isEqualTo("ME");
        softAssertions.assertThat(actual.heading).isEqualTo("Hello");
        softAssertions.assertThat(actual.body).isEqualTo("Hello World");
        softAssertions.assertThat(actual.attachs.attaches.get(0).name).isEqualTo("1");
        softAssertions.assertThat(actual.attachs.attaches.get(0).content).isEqualTo("2");

        softAssertions.assertAll();
    }
}
