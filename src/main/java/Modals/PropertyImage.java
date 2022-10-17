package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyImage {
    @JsonProperty("id")
    public String id;

    @JsonProperty("imageUrl")
    public String imageUrl;
}
