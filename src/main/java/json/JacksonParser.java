package com.solvd.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.model.UberData;

import java.io.File;

public class JacksonParser {

    private static final String FILE_PATH = "src/main/resources/uber-data.json";

    public UberData read() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            return mapper.readValue(new File(FILE_PATH), UberData.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON with Jackson", e);
        }
    }
}
