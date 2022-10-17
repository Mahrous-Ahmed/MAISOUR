package APIServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SumOfInvestment {

    public static double getSumOfSuccessfulInvestment() throws JsonProcessingException {
        RestAssured.baseURI = "https://maistest.space/api/v1";
        RequestSpecification httprequest = RestAssured.given();
        httprequest.header("Authorization",APILogin.getToken("mahrous2017@mobidevlabs.com","Mahrous&&99"));
        httprequest.multiPart("status", "Successful");
        Response response = httprequest.post("/getSumOfUserInvestmentByStatus");
        if(response.statusCode() == 200)
            return Double.valueOf(response.getBody().asString());

        else
            return -1;
    }

    public static double getSumOfPendingInvestment() throws JsonProcessingException {
        RestAssured.baseURI = "https://maistest.space/api/v1";
        RequestSpecification httprequest = RestAssured.given();
        httprequest.header("Authorization",APILogin.getToken("mahrous2017@mobidevlabs.com","Mahrous&&99"));
        httprequest.multiPart("status", "Pending");
        Response response = httprequest.post("/getSumOfUserInvestmentByStatus");
        if(response.statusCode() == 200)
            return Double.valueOf(response.getBody().asString());

        else
            return -1;
    }
}
