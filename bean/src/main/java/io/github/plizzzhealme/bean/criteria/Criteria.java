package io.github.plizzzhealme.bean.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria {

    private final Map<String, String> searchParameters;

    public Criteria() {
        searchParameters = new HashMap<>();
    }

    public void addParameter(String parameter, String value) {
        searchParameters.put(parameter, value);
    }

    public Map<String, String> getSearchParameters() {
        return searchParameters;
    }
}
