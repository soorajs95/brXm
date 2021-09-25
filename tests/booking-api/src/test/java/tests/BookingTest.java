package tests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ApiUtils;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;

@Listeners({ExtentITestListenerClassAdapter.class})
public class BookingTest extends ApiUtils {

    @Test(priority = 1)
    public void createBooking() throws ParseException {
        JSONArray bookings = (JSONArray) new JSONParser().parse(getRequestBody("create_booking.json"));
        for (Object bookingData : bookings) {
            given().
                    request().
                    header("Content-Type", "application/json").
                    header("Accept", "application/json").
                    body(bookingData.toString()).
                    post(BASE_URI + BOOKING_ENDPOINT).
                    then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
        }
    }

    @Test(priority = 2)
    public void partialUpdateBooking() throws ParseException {
        JSONObject partialBookingUpdate = (JSONObject) new JSONParser().parse(getRequestBody("partial_update_booking.json"));
        String bookingId = getBookingIds("Dee", "Jay").get(0).toString();
        String authToken = getAuthToken();
        given().
                request().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                header("Cookie", "token=" + authToken).
                body(partialBookingUpdate.toString()).
                patch(BASE_URI + BOOKING_ENDPOINT + "/" + bookingId).
                then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(priority = 3)
    public void updateBooking() throws ParseException {
        JSONObject bookingUpdate = (JSONObject) new JSONParser().parse(getRequestBody("update_booking.json"));
        String bookingId = getBookingIds("Bloomy", "Reachson").get(0).toString();
        String authToken = getAuthToken();
        given().
                request().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                header("Cookie", "token=" + authToken).
                body(bookingUpdate.toString()).
                put(BASE_URI + BOOKING_ENDPOINT + "/" + bookingId).
                then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test(priority = 4)
    public void deleteBooking() throws ParseException {
        String bookingId = getBookingIds("John", "Macintosh").get(0).toString();
        String authToken = getAuthToken();
        given().
                request().
                header("Content-Type", "application/json").
                header("Cookie", "token=" + authToken).
                delete(BASE_URI + BOOKING_ENDPOINT + "/" + bookingId).
                then().assertThat().statusCode(HttpURLConnection.HTTP_CREATED);
    }
}
