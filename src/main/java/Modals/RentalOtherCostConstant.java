package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalOtherCostConstant {
    @JsonProperty("id")
    public String id;

    @JsonProperty("nameInEnglish")
    public String nameInEnglish;

    @JsonProperty("nameInArabic")
    public String nameInArabic;

    @JsonProperty("value")
    public Double value;

    @JsonProperty("type")
    public String type;


}
