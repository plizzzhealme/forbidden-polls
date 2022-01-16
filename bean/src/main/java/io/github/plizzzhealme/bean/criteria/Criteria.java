package io.github.plizzzhealme.bean.criteria;

import java.util.EnumMap;
import java.util.Map;

public class Criteria {

    private final EnumMap<Column, String> searchParameters;

    public Criteria() {
        searchParameters = new EnumMap<>(Column.class);
    }

    public void addParameter(Column column, String value) {
        searchParameters.put(column, value);
    }

    public Map<Column, String> getSearchParameters() {
        return searchParameters;
    }
}
