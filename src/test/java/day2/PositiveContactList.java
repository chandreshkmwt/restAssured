package day2;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class PositiveContactList {
	String id;
  @Test(enabled=false, description="getting all the contacts")
  public void getContactList() {
	  RestAssured.given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  
  @Test(enabled=false, description="getting all the contacts")
  public void getSpecificContact() {
	  RestAssured.given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/615fa73bf2967f0ec893ae9b")
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  
  @Test(enabled=false, description="getting all the contacts")
  public void getSpecificContact2() {
	  Response res = RestAssured.given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/615fa73bf2967f0ec893ae9b");
	  
	  System.out.println(res.getTime());
	  
	  res.then().log().body().statusCode(200);
  }
  
  @Test(enabled=true, description="getting all the contacts")
  public void addingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Mumbai");
	  location.put("country", "India");
	  
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  
	  details.put("firstName", "John");
	  details.put("lastName", "Smith");
	  details.put("email", "john@email.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
	  
	  ExtractableResponse<Response> ex = RestAssured.given()
	  .header("Content-Type", "application/json")
	  .body(details.toJSONString())
	  .when()
	  .post("http://3.13.86.142:3000/contacts")
	  .then()
	  .log()
	  .body()
	  .statusCode(200)
	  .extract();
//	  .path("_id");
	  id = ex.path("_id");
	  
	  System.out.println("Id is"+ex.path("_id"));
	  System.out.println("firstname is"+ex.path("firstName"));
	  System.out.println("lastname is"+ex.path("lastName"));
	  System.out.println("location is"+ex.path("location.city"));
	  
  }
  
  @Test(enabled=false, dependsOnMethods="addingContact",description="updating the contact")
  public void updatingContact() {
	  JSONObject details = new JSONObject();
	  JSONObject location = new JSONObject();
	  JSONObject emp = new JSONObject();
	  
	  location.put("city", "Mumbai");
	  location.put("country", "India");
	  
	  emp.put("jobTitle", "QA");
	  emp.put("company", "LTI");
	  
	  details.put("firstName", "newname");
	  details.put("lastName", "Smith");
	  details.put("email", "john@email.com");
	  details.put("location", location);
	  details.put("employer", emp);
	  
	  
	  RestAssured.given()
	  .header("Content-Type", "application/json")
	  .body(details.toJSONString())
	  .when()
	  .put("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(204);
//	  .path("_id");
	  System.out.println("done");
	  
  }
  
  @Test(enabled=false,dependsOnMethods="updatingContact", description="getting all the contacts")
  public void getSpecificContact3() {
	  RestAssured.given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(200);
  }
  
  @Test(enabled=true, dependsOnMethods="addingContact",description="deleting the contact")
  public void deleteContact() {
	  
	  RestAssured.given()
	  .when()
	  .delete("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(204);
//	  .path("_id");
	  System.out.println("done");
	  
  }
  
  @Test(enabled=true,dependsOnMethods="deleteContact", description="Checking the contact")
  public void checkDeletion() {
	  RestAssured.given()
	  .when()
	  .get("http://3.13.86.142:3000/contacts/"+id)
	  .then()
	  .log()
	  .body()
	  .statusCode(404);
  }
}
