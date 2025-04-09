package com.example.stocktrade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UnixTimestampSerializerTest {
    @Test

    public void testSerializeLocalDateTimeToUnixTimestamp() throws JsonProcessingException {
        LocalDateTime dateTime = LocalDateTime.of(2024, 4, 5, 12, 30); // 2024-04-05T12:30:00
        long expectedEpoch = dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();

        TestObject testObject = new TestObject(dateTime);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new UnixTimestampSerializer());
        mapper.registerModule(module);

        String json = mapper.writeValueAsString(testObject);

        assertTrue(json.contains("\"timestamp\":" + expectedEpoch));
    }
}
