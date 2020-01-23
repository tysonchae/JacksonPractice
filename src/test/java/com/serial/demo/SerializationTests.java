package com.serial.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public class Birth{
        private Date birthDate;

        public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public Birth(Date birthDate) {
            this.birthDate = birthDate;
        }
    }

    @Test
    public void testDeserializer() throws IOException {

        //SETUP
        String birthDateJson =
                "{ " +
                        "\"birthDate\": \"1934-03-13\" " +
                        "}";

        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        Date birthDate = mapper.readValue(birthDateJson, Date.class);
        System.out.println(birthDate);
        Assert.assertNotEquals(new Date(), birthDate);
    }

    @Test
    public void testSerializer() throws ParseException, JsonProcessingException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Birth birth = new Birth();
        birth.setBirthDate(df.parse("1999-03-01"));

        Event event = new Event("birthDate", birth.getBirthDate());

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(event);
    }
}