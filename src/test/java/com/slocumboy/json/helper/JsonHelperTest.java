package com.slocumboy.json.helper;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by davidrho on 5/11/17.
 */
public class JsonHelperTest {

    private TestUtils testUtils;

    @Before
    public void setup() throws Exception {
        testUtils = new TestUtils();
    }

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

        JsonObject actual = (JsonObject) helper.replace(jsonObject, "key2", new Pair<String, JsonValue>("key3", newObject.getJsonString("key3")));

        assertEquals(2, actual.size());

        assertEquals("keyValue1", actual.getString("key1"));

        assertEquals("keyValue3", actual.getString("key3"));

    }

//    @Test
//    public void testReplaceNested() throws IOException {
//        JsonHelper helper = new JsonHelper();
//        String jsonString = testUtils.readJsonFile("nestedJson.json");
//
//        JsonObject jsonObject = helper.createFromString(jsonString);
//
//        JsonObject newObject = Json.createObjectBuilder().add("key3", "keyValue3").build();
//
//        JsonObject actual = (JsonObject) helper.replace(jsonObject, "key2Level3",
//                new Pair<String, JsonValue>("key3", newObject.getJsonString("key3Level3")));
//
//        JsonObject object1Level1 = actual.getJsonObject("key1Level1");
//
//        JsonObject object1Level2 = object1Level1.getJsonObject("key1Level2");
//
//        assertEquals(2, object1Level1.size());
//
//        assertEquals("key1Value1", actual.getString("key1Level3"));
//
//        assertEquals("keyValue3", actual.getString("key3Level3"));
//
//    }

}
