package day2;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class Github {
  @BeforeTest
  public void beforeTest() {
	  baseURI = "https://api.github.com/user/repos";
	  authentication = oauth2("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");
  }
	
  @Test(enabled=false)
  public void gettingAllRepos() {
	  given()
	  .auth()
	  .oauth2("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")
	  .when()
	  .get()
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  
  @Test
  public void createNewRepo() {
	  JSONObject details = new JSONObject();
	  details.put("name", "seleniumtest4");
	  details.put("description", "Created by restassured tool");
	  details.put("homepage", "https://github.com/EightBitCoder404");
	  
	  given()
		  .header("Content-Type", "application/json")
		  .body(details.toJSONString())
	  .when()
	  	  .post()
	  .then()
		  .log()
		  .body()
		  .statusCode(201)
		  .time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
  }
}
