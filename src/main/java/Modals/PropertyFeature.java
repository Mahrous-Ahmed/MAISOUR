package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyFeature {
    @JsonProperty("id")
    public String id;

    @JsonProperty("nameInEnglish")
    public  String nameInEnglish;

    @JsonProperty("nameInArabic")
    public String nameInArabic;
}
