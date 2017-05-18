package com.slocumboy.json.helper;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import java.io.StringReader;
import java.io.StringWriter;
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
}
