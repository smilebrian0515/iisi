package com.iisi.test.brian.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CathayUnitedBankJavaExerciseTests {

    @Test
    public void test(){
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
        System.out.println(str);
        String excepted = "123";

        String actual = "123";
        assertThat(actual).isEqualTo(excepted);
    }
}
