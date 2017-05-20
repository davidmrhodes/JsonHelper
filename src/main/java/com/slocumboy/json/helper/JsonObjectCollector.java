package com.slocumboy.json.helper;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by davidrho on 5/18/17.
 */
public class JsonObjectCollector implements
        Collector<Map.Entry<String, JsonValue>, JsonObjectBuilder, JsonObject> {

    @Override
    public Supplier<JsonObjectBuilder> supplier() {
        return () -> Json.createObjectBuilder();
    }

    @Override
    public BiConsumer<JsonObjectBuilder, Map.Entry<String, JsonValue>> accumulator() {
        return (builder, entry) -> builder.add(entry.getKey(), entry.getValue());
    }

    @Override
    public BinaryOperator<JsonObjectBuilder> combiner() {
        return (left, right) -> {
            right.build().entrySet().stream().forEach(
                    entry -> left.add(entry.getKey(), entry.getValue()));
            return left;
        };
    }

    @Override
    public Function<JsonObjectBuilder, JsonObject> finisher() {
        return (builder) -> builder.build();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
