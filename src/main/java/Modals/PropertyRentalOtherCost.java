package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PropertyRentalOtherCost {

    @JsonProperty ("id")
    public String id;

    @JsonProperty("actualValue")
    public Double actualValue;

    @JsonProperty("rentalOtherCostConstant")
    public RentalOtherCostConstant rentalOtherCostConstants;

}
