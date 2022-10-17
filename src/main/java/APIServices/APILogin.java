package APIServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class APILogin {

    private final static String URL = "https://maistest.space/api/v1";
    public APILogin(){}
    public static String getToken(String username , String password) throws JsonProcessingException {
      RestAssured.baseURI = URL;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.multiPart("username" , username);
        httpRequest.multiPart("password" , password);
        Response response = httpRequest.post("/login");
        if(response.getStatusCode() != 200)
            return "";
        //System.out.println(response.body().asString());
        final ObjectNode objectNode = new ObjectMapper().readValue(response.getBody().asString(), ObjectNode.class);
        if(objectNode.has("jwtToken"))
            return "Bearer "+objectNode.get("jwtToken").asText();
        return "";
    }

    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(getToken("mahrous2017@mobidevlabs.com","Mahrous&&99"));
    }


}
