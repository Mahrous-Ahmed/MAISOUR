package APIServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



import java.util.HashMap;
import java.util.Map;

public class APIPropertyDetails {

    public static Map<String , String> getDetails(int position) throws JsonProcessingException {
        RestAssured.baseURI = "https://maistest.space/api/v1/property/"+position;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization",APILogin.getToken("mahrous2017@mobidevlabs.com","Mahrous&&99"));
        Response response = httpRequest.get();
        if(response.getStatusCode() != 200) {
            System.out.println("Error When Call API");
            return null;
        }


        Map<String , String> map = new HashMap<String,String>();

        final ObjectNode node = new  ObjectMapper().readValue(response.getBody().asString(),ObjectNode.class);
        map.put("numberOfInvestors",node.get("numberOfInvestors").asText());
        map.put("propertyPricePerShare",node.get("propertyPricePerShare").asText());
        map.put("propertyAcquisition",node.get("propertyAcquisition").asText());
        map.put("totalPurchaseCost",node.get("totalPurchaseCost").asText());
        map.put("totalTransactionCost",node.get("totalTransactionCost").asText());
        map.put("totalRentalOtherCost",node.get("totalRentalOtherCost").asText());
        map.put("remainingPeriod",node.get("remainingPeriod").asText());
        map.put("minInvestmentAmount",node.get("minInvestmentAmount").asText());
        map.put("dividedFrequency",node.get("dividedFrequency").asText());

        return map;




    }
}
