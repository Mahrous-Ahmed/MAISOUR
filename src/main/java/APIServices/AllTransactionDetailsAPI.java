package APIServices;

import Modals.AllTransactionDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AllTransactionDetailsAPI {

    public static AllTransactionDetails getAllTransactionDetails() throws JsonProcessingException {
        RestAssured.baseURI = "https://maistest.space/api/v1/getAllTransactionDetails";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Authorization", APILogin.getToken("mahrous2017@mobidevlabs.com", "Mahrous&&99"));
        httpRequest.multiPart("Success_Payment_Status", "Success");
        Response response = httpRequest.post();
        if (response.statusCode() == 200) {
             return new ObjectMapper().readValue(response.getBody().asString(), AllTransactionDetails.class);
        }
        return null;
    }
}
