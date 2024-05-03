package API_Automation.tests.curd;

import API_Automation.Actions.AssertActions;
import API_Automation.endPoints.API_Constants;
import API_Automation.modules.Payload_Manager;
import com.beust.ah.A;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;


import static io.restassured.RestAssured.requestSpecification;

public class CurdTypes {
    Payload_Manager payloadManager;
    AssertActions assertActions;
    String token;
    int bookingId;
public RequestSpecification requestSpecification;
    public  CurdTypes(){
        requestSpecification = new RequestSpecBuilder().build();

    }
    public void testCreateBooking() throws JsonProcessingException {

        requestSpecification.basePath(API_Constants.CREATE_BOOKING);
        payloadManager = new Payload_Manager();
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload())
                .post().then().log().all();
        response.statusCode(200);
        JsonPath jsonPath = response.extract().jsonPath();
        bookingId = jsonPath.get("bookingid");
        String firstName = jsonPath.get("booking.firstname");
        System.out.println(firstName);
        System.out.println("Booking is created and the bookingId is "+bookingId);
        Assert.assertNotNull(bookingId);
        Assertions.assertThat(bookingId).isNotNull();
        Assertions.assertThat(firstName).isEqualToIgnoringCase("prasad").isNotEqualTo("psd");

    }
    public void CreateToken(){
    requestSpecification.basePath(API_Constants.CREATE_TOKEN);
    payloadManager = new Payload_Manager();
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .body(payloadManager.createToken())
                .post()
                .then().log().all()
                .statusCode(200);
        response.body("token", Matchers.notNullValue());
        response.body("token",Matchers.equalTo(15));
        token = response.extract().path("token");
        System.out.println("the generated token is" + token);
        Assertions.assertThat(token).isNotNull().hasSize(15);

    }

    public void UpdateBooking() throws JsonProcessingException{
        requestSpecification.basePath(API_Constants.UPDATE_BOOKING +bookingId);

        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .body(payloadManager.updatePayload())
                .when().put()
                .then().log().all()
                .assertThat().statusCode(200)
                .body("firstname", Matchers.equalTo("PrasadNe"))
                .body("totalprice", Matchers.equalTo(112))
                .assertThat().body("firstname",Matchers.equalTo("PrasadNe"));

    }

    public void testUpdatedBooking_ByGetID(){
        payloadManager = new Payload_Manager();
        requestSpecification.basePath(API_Constants.UPDATE_BOOKING+bookingId);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .assertThat().statusCode(200);
JsonPath jsonPath = response.extract().jsonPath();
String firstName = jsonPath.getString("firstname");
Assertions.assertThat(firstName).isEqualToIgnoringCase("pRasadNe");

    }


    public void testDeleteBooking(){
        payloadManager = new Payload_Manager();
        requestSpecification.basePath(API_Constants.DELETE_BOOKING+bookingId);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .delete()
                .then().log().all()
                .assertThat().statusCode(200);

     }




}
