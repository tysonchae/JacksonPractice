package com.serial.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

@RunWith(SpringRunner.class)
public class
SerializationTests {

    @Test
    public void testDeserializer() throws IOException {

        //“2016-09-09T14:11:08Z”
        //SETUP
        String birthDateJson =
                "{ " +
                        "\"birthDate\": \"1934-03-13\" " +
                        "}";

        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        System.out.println(mapper.getDateFormat().getTimeZone());
        Birth birthDate = mapper.reader().forType(Birth.class).readValue(birthDateJson);
        System.out.println(birthDate.getBirthDate());
        Assert.assertNotEquals(new Date(), birthDate.getBirthDate());
    }

    @Test
    public void testSerializer() throws ParseException, JsonProcessingException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(df.getTimeZone());
        Birth birth = new Birth();
        birth.setBirthDate(df.parse("1999-03-01"));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS , false);
        String result = mapper.writeValueAsString(birth);
        System.out.println(mapper.getDateFormat().getTimeZone());
        System.out.println(result);
    }
}