package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Конвертация из одного класса в другой используя JSON
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        First first = new First();
        first.name = "firstName";
        first.i = 1;
        Second second = new Second();
        second.name = "secondName";
        second.i = 2;
        Second s = (Second) convertOneToAnother(first, Second.class);
        System.out.println(s.toString());
        First f = (First) convertOneToAnother(new Second(), First.class);
        System.out.println(f.toString());
        StringWriter writer = new StringWriter();
        convertToJSON(writer, second);
        System.out.println(writer.toString());
    }

    public static void convertToJSON(StringWriter writer, Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, object);
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(MapperFeature.USE_ANNOTATIONS);
        String result = mapper.writeValueAsString(one);
        Object object = mapper.readValue(result, resultClassObject);
        return object;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=First.class,  name="first"))
    public static class First {
        public int i;
        public String name;

        @Override
        public String toString() {
            return "FirstClass" + i + name;
        }
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME,  property="className")
    @JsonSubTypes(@JsonSubTypes.Type(value=Second.class, name="second"))
    public static class Second {
        public int i;
        public String name;
        @Override
        public String toString() {
            return "FirstClass" + i + name;
        }
    }
}
