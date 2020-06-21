package com.iisi.test.brian.demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        Xmlobj.Note actual = (Xmlobj.Note) jaxbContextUnmarshaller.unmarshal(new StringReader(str));

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.to).isEqualTo("YOU");
        softAssertions.assertThat(actual.from).isEqualTo("ME");
        softAssertions.assertThat(actual.heading).isEqualTo("Hello");
        softAssertions.assertThat(actual.body).isEqualTo("Hello World");
        softAssertions.assertThat(actual.attachs.attaches.get(0).name).isEqualTo("1");
        softAssertions.assertThat(actual.attachs.attaches.get(0).content).isEqualTo("2");

        softAssertions.assertAll();
    }

    @Test
    public void test_Q3() {
        String str = "[{\"userId\" : 2003,\"name\" : \"張三\",\"loginTime\" : \"2018-11-13T20:20:39+00:00\"},{\"userId\" :\n" +
                "2004,\"name\" : \"李四\",\"loginTime\" : \"2018-11-13T20:20:01+00:00\"},{\"userId\" :\n" +
                "2005,\"name\" : \"王五\",\"loginTime\" : \"2018-11-13T20:20:01+00:00\"}]";

//        System.out.println(str);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ:ZZ").create();
        Type type = new TypeToken<List<JsonobjQ3>>() {}.getType();
        List<JsonobjQ3> jsonobj = gson.fromJson(str, type);

        List<JsonobjQ3> actual = jsonobj.stream().sorted(
                Comparator.comparing(JsonobjQ3::getLoginTime)
                        .thenComparing(JsonobjQ3::getUserId).reversed()
        ).collect(Collectors.toList());


        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.get(0).userId).isEqualTo(2003);
        softAssertions.assertThat(actual.get(1).userId).isEqualTo(2005);
        softAssertions.assertThat(actual.get(2).userId).isEqualTo(2004);
        softAssertions.assertAll();
    }
}
