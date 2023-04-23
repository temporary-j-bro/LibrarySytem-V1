package jbro.librarysystem.acceptance.home;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class HomeRequests {

    public static ExtractableResponse<Response> 홈_페이지_이동() {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.TEXT_HTML_VALUE)
                .when()
                .get("/")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract();
    }
}
