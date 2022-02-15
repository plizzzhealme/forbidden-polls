package io.github.plizzzhealme.bean.criteria;

import java.io.Serial;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;

public class SearchCriteria implements Serializable {

    @Serial
    private static final long serialVersionUID = -4864180525089525928L;

    private final Map<Parameter, Object> searchParameters = new EnumMap<>(Parameter.class);

    public void addParameter(Parameter parameter, Object value) {
        searchParameters.put(parameter, value);
    }

    public Map<Parameter, Object> getSearchParameters() {
        return searchParameters;
    }
}
