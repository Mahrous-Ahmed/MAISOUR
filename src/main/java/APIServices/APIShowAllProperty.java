package APIServices;

import Modals.AllProperties;
import Modals.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class APIShowAllProperty {



    public static AllProperties getAllProperties() throws JsonProcessingException {
        String Token = APILogin.getToken("mahrous2017@mobidevlabs.com", "Mahrous&&99");
        if (Token.equals(""))
            return null;
        else {
            System.out.println(Token);
            System.out.println("-------------------------");
            RestAssured.baseURI = "https://maistest.space/api/v1/allProperties/?page=0&size=6";
            RequestSpecification httpRequest = RestAssured.given();
            httpRequest.header("Authorization", Token);
            Response response = httpRequest.get("");
            if(response.statusCode() != 200)
                return null;
            AllProperties properties= new ObjectMapper().readValue(response.getBody().asString(), AllProperties.class);
            return properties;
        }
    }

}
