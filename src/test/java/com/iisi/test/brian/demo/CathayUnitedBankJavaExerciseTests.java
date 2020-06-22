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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

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
        Type type = new TypeToken<List<JsonobjQ3>>() {
        }.getType();
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

    @Test
    public void test_Q4_1() throws UnsupportedEncodingException {
        String str = "威";
        boolean excepted = true;

        boolean actual = new ValidTools().isBig5Encoding(str.getBytes());
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void test_Q4_12() throws UnsupportedEncodingException {
        String str = "二";
        boolean excepted = true;

        boolean actual = new ValidTools().isBig5Encoding(str.getBytes());
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void test_Q4_2() throws UnsupportedEncodingException {
        String str = "燰";
        boolean excepted = false;

        boolean actual = new ValidTools().isBig5Encoding(str.getBytes());
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void test_Q4_22() throws UnsupportedEncodingException {
        String str = "訲";
        boolean excepted = true;

        boolean actual = new ValidTools().isBig5Encoding(str.getBytes());
        assertThat(actual).isEqualTo(excepted);
    }

    /* Q5
    create table TableName (
    country nvarchar(2),
    latitude float,
    longitude float,
    name nvarchar,
    );

    create index indexName on TableName (latitude,longitude);
    * */

    /* Q6
    * build.gradle 加入套件 implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    * application.properties 加入連線資料
    * 新增跟資料表對應的 @Enity 程式
    * 新增操作用的 @Repository 程式
    * */

    /* Q7
    * 一個相對比較新，比較方便的框架
    * 可以利用各種 annotation 去節省一些事情，例如以前在 struts2 需要寫 struts-config.xml
    * 但是在 Spring Boot 可以在 Controller 用 @PostMapping("path") 就可以輕鬆設定好對應
    * 速度有快一些，預設是 Singleton (也可以用 @Scope 去更改)
    * 要寫 RESTful API 可以用 @RestController
    * 要寫會回頁面的，可以用 @Controller 搭配 @ModelAttribute 去做
    * 利用 AOP 可以將一些耦合切開，或是統一整理特定動作 (不太熟)
    * @Transational 因為AOP，要不同類別呼叫才有效果，同類別的方法呼叫會無效
    * 可以利用 DataSourceTransactionManager 去做 Transation，可以比較直觀的看到控管範圍
    * 使用 Gradle Project 在套件的管理上比較簡單和方便
    * 有區分 main 和 test，寫測試也比較好整理
    * 直接把 tomcat 包在 jar 裡面，所以 build 出來的 jar 可以直接拿來啟動
    * */
}
