package com.marcin.stepien.testproject.controler.webapicontrol;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class ApiControler {

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given().
                        headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().
                        get(endpoint).
                then().
                        contentType(ContentType.JSON).extract().response();
    }

    public static int doDeleteRequest(String endpoint) {
       return
               given().
                       headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                when().
                       delete(endpoint).
                       statusCode();
    }

    public static int doPostRequest(String endpoint, String json) {
        return
                given().
                        headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        body(json).
                when().
                        post(endpoint).
                        statusCode();

    }
}
