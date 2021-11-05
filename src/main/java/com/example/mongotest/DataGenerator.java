package com.example.mongotest;

import java.util.HashMap;
import java.util.Map;

public class DataGenerator {


    public DataGenerator() {
    }

    public Map<String, Object> dataForUser() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "Edward");
        params.put("job","Postman");
        return params;
    }

    public Map<String, Object> dataForUserCustom(String name, String job) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", name);
        params.put("job",job);
        return params;
    }
}
