package com.iisi.test.brian.demo;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CathayUnitedBankJavaExerciseTests {

    private static Gson gson = new Gson();

    @Test
    public void test() {
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

}
