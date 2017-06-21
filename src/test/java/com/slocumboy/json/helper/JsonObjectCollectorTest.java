package com.slocumboy.json.helper;

import org.junit.Test;

import javax.json.JsonObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by davidrho on 5/18/17.
 */
public class JsonObjectCollectorTest {

    @Test
    public void testCreatesNewObjectWithSameKeyValues() {

        JsonHelper helper = new JsonHelper();

        String jsonString = "{\"key1\" : \"keyValue1\", \"key2\" : \"keyValue2\"}";

        JsonObject jsonObject = helper.createFromString(jsonString);

        JsonObject actual = jsonObject.entrySet().stream()
                .collect(new JsonObjectCollector());

        assertFalse(jsonObject == actual);

        assertEquals("keyValue1", actual.getString("key1"));
        assertEquals("keyValue2", actual.getString("key2"));

    }

}
