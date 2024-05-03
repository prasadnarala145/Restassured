package API_Automation.tests.base;

import API_Automation.Actions.AssertActions;
import API_Automation.endPoints.API_Constants;
import API_Automation.modules.Payload_Manager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

public class Base_test {

    public RequestSpecification requestSpecification;

    public AssertActions assertActions;
    public Payload_Manager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    int token;
    @BeforeMethod
    public void BaseTest(){
    payloadManager = new Payload_Manager();
    assertActions = new AssertActions();
    requestSpecification =(RequestSpecification) new RequestSpecBuilder()
            .setBaseUri(API_Constants.BASE_URL)
            .addHeader("Content-Type","application/json")
            .build().log().all();
    }

}
