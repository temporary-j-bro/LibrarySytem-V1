package jbro.librarysystem.acceptance.book;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jbro.librarysystem.book.dto.BookRegisterForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

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

    public static ExtractableResponse<Response> 책_등록하기_페이지_이동() {
        return RestAssured
                .given().log().all()
                    .contentType(MediaType.TEXT_HTML_VALUE)
                .when()
                    .get("/books/register-form")
                .then().log().all()
                    .statusCode(HttpStatus.OK.value())
                    .extract();
    }

    public static ExtractableResponse<Response> 책_등록하기(BookRegisterForm form) throws IOException {
        return RestAssured
                .given().log().all()
                    .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                    .formParam("title", form.getTitle())
                    .formParam("author", form.getAuthor())
                    .formParam("isbn", form.getIsbn())
                    .multiPart("image", form.getImage().getOriginalFilename(), form.getImage().getBytes(), form.getImage().getContentType())
                .when()
                    .post("/books/register-form")
                .then().log().all()
                    .statusCode(HttpStatus.FOUND.value())
                    .extract();
    }

    public static ExtractableResponse<Response> 책_등록하기_페이지_리다이렉션(String jsessionId) {
        return RestAssured
                .given().log().all()
                    .contentType(MediaType.TEXT_HTML_VALUE)
                    .cookie("JSESSIONID", jsessionId)
                .when()
                    .get("/books/register-form")
                .then().log().all()
                    .statusCode(HttpStatus.OK.value())
                    .extract();
    }

    public static ExtractableResponse<Response> 책_상세_페이지_이동(Long bookId) {
        return RestAssured
                .given().log().all()
                    .contentType(MediaType.TEXT_HTML_VALUE)
                .when()
                    .get("/books/{bookId}", bookId)
                .then().log().all()
                    .statusCode(HttpStatus.OK.value())
                    .extract();
    }
}
