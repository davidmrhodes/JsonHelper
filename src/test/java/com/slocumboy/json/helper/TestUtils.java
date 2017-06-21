package com.slocumboy.json.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by davidrho on 6/19/17.
 */
public class TestUtils {

    public String readJsonFile(String fileName) throws IOException {
        InputStream input = TestUtils.class.getClassLoader().
                getResourceAsStream(fileName);
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }

}
