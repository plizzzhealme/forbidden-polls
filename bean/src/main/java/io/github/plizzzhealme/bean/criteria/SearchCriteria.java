package io.github.plizzzhealme.bean.criteria;

import java.util.EnumMap;
import java.util.Map;

public class SearchCriteria {

    private final EnumMap<Parameter, Object> searchParameters;

    public SearchCriteria() {
        searchParameters = new EnumMap<>(Parameter.class);
    }

    public void addParameter(Parameter parameter, Object value) {
        searchParameters.put(parameter, value);
    }

    public Map<Parameter, Object> getSearchParameters() {
        return searchParameters;
    }
}
