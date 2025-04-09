package com.example.stocktrade.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UnixTimestampDeserializerTest {
    @Test
    public void testDeserializeUnixTimestamp() throws Exception {
        String json = "{ \"timestamp\": 1712293800000 }";

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new UnixTimestampDeserializer());
        mapper.registerModule(module);

        TestObject result = mapper.readValue(json, TestObject.class);

        assertEquals(LocalDateTime.of(2024, 4, 5, 10, 40), result.getTimestamp());
    }
}
