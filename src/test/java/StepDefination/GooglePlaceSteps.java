package StepDefination;

import io.cucumber.java.en.Given;
import static io.restassured.RestAssured.*;

import org.junit.Assert;

import Resources.Endpoints;
import Resources.PayloadData;
import helper.Base;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GooglePlaceSteps {

	RequestSpecification req;
	Response res;
	Response getRes ;
	static String placeid;
	PayloadData P = new PayloadData();

	@Given("user prepare a reguest with payload {string} {string} {string}")
	public void user_prepare_a_reguest_with_payload(String Name, String Pnumber, String Address) {

		req = given().log().all().spec(Base.setup()).body(P.DataPayload(Name, Pnumber, Address));
	}

	@When("user send the {string} request")
	public void user_send_the_request(String method) {
		if (method.equalsIgnoreCase("POST")) {
			res = req.post(Endpoints.addPlace);
		} else if (method.equalsIgnoreCase("GET")) {
			res = req.get(Endpoints.getPlace);
		} else if (method.equalsIgnoreCase("PUT")) {
			res = req.get(Endpoints.updatePlace);
		} else if (method.equalsIgnoreCase("DELETE")) {
			res = req.get(Endpoints.deletePlace);
		}
	}

	@When("user get status code of {string}")
	public void user_get_status_code_of(String statuscode) {

		int Actualcode = res.getStatusCode();
		Assert.assertEquals(String.valueOf(Actualcode), statuscode);

	}

	@Then("validate {string} value is {string}")
	public void validate_value_is(String Key, String ExpectedValue) {

		String Response = res.asString();
		JsonPath json = new JsonPath(Response);
		String actualvalue = json.getString(Key);
		Assert.assertEquals(actualvalue, ExpectedValue);

	}

	@Then("Validate Place_id Created maps is {string}")
	public void validate_place_id_created_maps_is_using(String ExpectedName) {
		String Response = res.asString();
		JsonPath json = new JsonPath(Response);
	    placeid = json.getString("place_id");
	    getRes = given().log().all().spec(Base.setup()).queryParam("place_id", placeid)
				.get(Endpoints.getPlace);
		String getResponse = getRes.asString();
		System.out.println(getResponse);
		JsonPath json1 = new JsonPath(getResponse);
		String ActualName = json1.get("name");
		Assert.assertEquals(ActualName, ExpectedName);

	}

}
