package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestAssignment{
	
	@Test
	public void Only4ForeignPlayers() {
		Response response = RestAssured.get("https://gist.githubusercontent.com/kumarpani/1e759f27ae302be92ad51ec09955e765/raw/184cef7125e6ef5a774e60de31479bb9b2884cb5/TeamRCB.json");
		String resp = response.getBody().asString();
		JsonPath jPath = new JsonPath(resp); 
		int roleCount = jPath.getInt("player.size()"), count=0;
		
		for(int i=0; i<roleCount; i++) {
			if(!jPath.getString("player["+i+"].country").equals("India")) {
				count++;	
			}
		}

		Assert.assertEquals(count, 4);
	}
}
