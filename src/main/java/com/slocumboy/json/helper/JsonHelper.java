package com.slocumboy.json.helper;

import javafx.util.Pair;
import jdk.nashorn.internal.ir.ObjectNode;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidrho on 5/11/17.
 */
public class JsonHelper {

    public JsonObject createFromString(String jsonString) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));

        return reader.readObject();
    }

    public String prettyPrint(JsonObject jsonObject) {
        return jsonFormat(jsonObject, JsonGenerator.PRETTY_PRINTING);

    }

    private String jsonFormat(JsonStructure json, String... options) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Boolean> config = buildConfig(options);
        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        JsonWriter jsonWriter = writerFactory.createWriter(stringWriter);

        jsonWriter.write(json);
        jsonWriter.close();

        return stringWriter.toString();
    }

    private Map<String, Boolean> buildConfig(String... options) {
        Map<String, Boolean> config = new HashMap<String, Boolean>();

        if (options != null) {
            for (String option : options) {
                config.put(option, true);
            }
        }

        return config;
    }

    public JsonObject replace(JsonStructure json, String key, Pair<String, JsonValue> replacement) {
        JsonObject jsonObject = (JsonObject)json;
        JsonObject newJsonObject = jsonObject.entrySet().stream()
                .map( entry -> {
                    if (entry.getKey().equals(key)) {
                        return new AbstractMap.SimpleEntry<String, JsonValue>(replacement.getKey(), replacement.getValue());
                    } else {
                        return entry;
                    }
                }).collect(new JsonObjectCollector());
        return newJsonObject;
    }
}
