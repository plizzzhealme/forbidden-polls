package io.github.plizzzhealme.bean;

import java.util.HashMap;
import java.util.Map;

public class SearchCriteria {

    private final Map<String, String> parameters;

    public SearchCriteria() {
        parameters = new HashMap<>();
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
