package utils;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;

public class ApiUtils extends ServiceConstants {

    public static String getRequestBody(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/" + filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Integer> getBookingIds(String firstName, String lastName) {
        Response response = given().
                request().
                queryParam("firstname", firstName).
                queryParam("lastname", lastName).
                get(BASE_URI + BOOKING_ENDPOINT);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        return response.path("bookingid");
    }

    public String getAuthToken() throws ParseException {
        JSONObject auth = (JSONObject) new JSONParser().parse(getRequestBody("get_auth_token.json"));
        Response response = given().
                request().
                header("Content-Type", "application/json").
                body(auth.toString()).
                post(BASE_URI + AUTH_ENDPOINT);
        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        return response.path("token").toString();
    }

}
