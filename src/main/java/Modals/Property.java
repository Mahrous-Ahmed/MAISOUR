package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Property {


    @JsonProperty("id")
    public String id;


    @JsonProperty("code")
    public String code;

    @JsonProperty("titleInEnglish")
    public String titleInEnglish;

    @JsonProperty("titleInArabic")
    public String titleInArabic;

    @JsonProperty("cityInEnglish")
    public String cityInEnglish;

    @JsonProperty("cityInArabic")
    public String cityInArabic;

    @JsonProperty("countryInEnglish")
    public String countryInEnglish;

    @JsonProperty("countryInArabic")
    public String countryInArabic;

    @JsonProperty("propertyStatus")
    public String propertyStatus;

    @JsonProperty("fiveYearExpectedReturn")
    public double fiveYearExpectedReturn;


    @JsonProperty("propertyFeatures")
    public List<PropertyFeature> propertyFeatures;

    @JsonProperty("propertyImages")
    public List<PropertyImage> propertyImages;

    @JsonProperty("propertyRentalOtherCosts")
    public List<PropertyRentalOtherCost> propertyRentalOtherCosts;

    @JsonProperty("rentAmount")
    public Double rentAmount;

    @JsonProperty("totalPrice")
    public Double totalPrice;

    @JsonProperty("propertyManagementFee")
    public Double propertyManagementFee;

    @JsonProperty("totalRentalOtherCost")
    public Double totalRentalOtherCost;


    @JsonProperty("expectedAnnualAppreciation")
    public String expectedAnnualAppreciation;

}
