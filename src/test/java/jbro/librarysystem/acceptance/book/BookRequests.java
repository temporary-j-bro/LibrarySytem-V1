package jbro.librarysystem.acceptance.book;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class BookRequests {

    public static ExtractableResponse<Response> 책_관리하기_페이지_이동() {
        return RestAssured
                .given().log().all()
                    .contentType(MediaType.TEXT_HTML_VALUE)
                .when()
                    .get("/books/management")
                .then().log().all()
                    .statusCode(HttpStatus.OK.value())
                    .extract();
    }
}
