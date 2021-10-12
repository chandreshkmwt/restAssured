package day1;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WeatherApi {
	
	@Test(enabled=false,description="getting weather info of specific city")
	public void getWeather() {
		RestAssured.given() //some precondition like authentication 
					.when() //perform some steps
					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=acdaa274603b6c7a622c9b5ff9b1aacc")
					.then() //some post condition like verification
					.log() //print data in console
					.body()
					.statusCode(200);
	}
	
	@Test(description="getting weather info of specific city")
	public void getWeather2() {
		Response res = RestAssured.given() //some precondition like authentication 
					.when() //perform some steps
					.get("http://api.openweathermap.org/data/2.5/weather?q=Mumbai&appid=acdaa274603b6c7a622c9b5ff9b1aacc");
		
		System.out.println(res.prettyPrint());
		System.out.println(res.getTime());
		System.out.println(res.getStatusCode());
		System.out.println(res.getContentType());
		
		Assert.assertEquals(res.getStatusCode(), 200);  //checking with help of testng assertion
	}
	
	@Test(description="getting weather info of specific city")
	public void getWeather3() {
		RestAssured.given() //some precondition like authentication 
					.queryParam("q", "Mumbai")
					.queryParam("appid", "acdaa274603b6c7a622c9b5ff9b1aacc")
					.when() //perform some steps
					.get("http://api.openweathermap.org/data/2.5/weather")
					.then() //some post condition like verification
					.log() //print data in console
					.body()
					.statusCode(200);
	}
	
	@Test(description="getting weather info of specific city")
	public void getWeather4() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("q", "Mumbai");
		param.put("appid", "acdaa274603b6c7a622c9b5ff9b1aacc");
		RestAssured.given() //some precondition like authentication 
					.queryParams(param)
					.when() //perform some steps
					.get("http://api.openweathermap.org/data/2.5/weather")
					.then() //some post condition like verification
					.log() //print data in console
					.body()
					.statusCode(200);
	}
}
