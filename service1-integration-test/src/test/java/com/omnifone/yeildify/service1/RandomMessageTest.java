/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omnifone.yeildify.service1;

import static com.jayway.restassured.RestAssured.*;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author sandygordon
 */
public class RandomMessageTest {

    @Test
    public void testRandMsg_Simple() {
        String message = "Hello There";
        JsonPath json = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"message\":\"" + message + "\"}")
                .log().all()
                .expect()
                .statusCode(200)
                .log().all()
                .when().post("http://localhost:8080/api")
                .body().jsonPath();

        Assert.assertNotNull("JsonPath Not Null", json);
        Assert.assertEquals("Message", message, json.getString("message"));
        Double randomValue = json.getDouble("rand");
        Assert.assertNotNull("Random Not Null", randomValue);
        Assert.assertTrue("Random >= 0 && < 1", randomValue >= 0 && randomValue < 1);
    }

    @Test
    public void testRandMsg_Repeated() {
        String message = "Hello There";
        Set<Double> seenRands = new HashSet();
        for (int i = 0; i < 3; i++) {
            JsonPath json = given().contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body("{\"message\":\"" + message + "\"}")
                    .log().all()
                    .expect()
                    .statusCode(200)
                    .log().all()
                    .when().post("http://localhost:8080/api")
                    .body().jsonPath();

            Assert.assertNotNull("JsonPath Not Null", json);
            Assert.assertEquals("Message", message, json.getString("message"));
            Double randomValue = json.getDouble("rand");
            Assert.assertNotNull("Random Not Null", randomValue);
            Assert.assertTrue("Random >= 0 && < 1", randomValue >= 0 && randomValue < 1);
            Assert.assertFalse(seenRands.contains(randomValue));
            seenRands.add(randomValue);
        }
    }

    @Test
    public void testRandMsg_PUT() {
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"message\":\"Hello There\"}")
                .log().all()
                .expect()
                .statusCode(405)
                .log().all()
                .when().put("http://localhost:8080/api");
    }

    @Test
    public void testRandMsg_GET() {
        given()
                .log().all()
                .expect()
                .statusCode(405)
                .log().all()
                .when().get("http://localhost:8080/api");
    }
}
