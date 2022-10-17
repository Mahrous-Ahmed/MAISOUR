package Modals;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public class AllTransactionDetails {

    @JsonProperty("deposits")
    public double deposits ;

    @JsonProperty("dividends")
    public double dividends;

    @JsonProperty("promotionRewards")
    public double promotionRewards;

    @JsonProperty("rewardsUsed")
    public double rewardsUsed;

    @JsonProperty("saleProceeds")
    public double saleProceeds;

    @JsonProperty("otherAdjustedAmounts")
    public double otherAdjustedAmounts;

    @JsonProperty("withDrawals")
    public double withDrawals;

    @JsonProperty("walletBalance")
    public double walletBalance;










}
