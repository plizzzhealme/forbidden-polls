package io.github.plizzzhealme.dao.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria {

    private final Map<Column, String> searchParameters;

    public Criteria() {
        searchParameters = new HashMap<>();
    }

    public void addParameter(Column column, String value) {
        searchParameters.put(column, value);
    }

    public Map<Column, String> getSearchParameters() {
        return searchParameters;
    }
}
