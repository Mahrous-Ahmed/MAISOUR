package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AllProperties {

    @JsonProperty("pageNumbers")
    public String pageNumbers;

    @JsonProperty("property")
    public List<Property> properties;
}
