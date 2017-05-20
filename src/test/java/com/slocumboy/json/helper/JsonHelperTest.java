package com.slocumboy.json.helper;

import javafx.util.Pair;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by davidrho on 5/11/17.
 */
public class JsonHelperTest {

    @Test
    public void testCreateJsonFromString() {

        JsonHelper helper = new JsonHelper();

        String jsonString = "{\"key1\" : \"keyValue1\", \"key2\" : \"keyValue2\"}";

        JsonObject jsonObject = helper.createFromString(jsonString);

        assertEquals("keyValue1", jsonObject.getString("key1"));
        assertEquals("keyValue2", jsonObject.getString("key2"));

    }

    @Test
    public void testPrettyPrint() {
        JsonHelper helper = new JsonHelper();
        String jsonString = "{\"key1\" : \"keyValue1\", \"key2\" : \"keyValue2\"}";

        String expectedPrettyPrint = "\n{\n" +
                "    \"key1\":\"keyValue1\",\n" +
                "    \"key2\":\"keyValue2\"\n" +
                "}";

        JsonObject jsonObject = helper.createFromString(jsonString);

        String actualPrettyPrint = helper.prettyPrint(jsonObject);

        assertEquals(expectedPrettyPrint, actualPrettyPrint);

    }

    @Test
    public void testReplaceNotNested() {
        JsonHelper helper = new JsonHelper();
        String jsonString = "{\"key1\" : \"keyValue1\", \"key2\" : \"keyValue2\"}";

        JsonObject jsonObject = helper.createFromString(jsonString);

        JsonObject newObject = Json.createObjectBuilder().add("key3", "keyValue3").build();

        JsonObject actual = helper.replace(jsonObject, "key2", new Pair<String, JsonValue>("key3", newObject.getJsonString("key3")));

        assertEquals(2, actual.size());

        assertEquals("keyValue1", actual.getString("key1"));

        assertEquals("keyValue3", actual.getString("key3"));

    }

}
