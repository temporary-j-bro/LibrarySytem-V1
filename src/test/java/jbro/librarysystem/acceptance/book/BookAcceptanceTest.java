package jbro.librarysystem.acceptance.book;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jbro.librarysystem.acceptance.AcceptanceTest;
import jbro.librarysystem.book.BookRegisterForm;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BookAcceptanceTest extends AcceptanceTest {

    /**
     * When  책 관리하기 페이지를 요청하면
     * Then  topic 은 책 관리하기이고,
     *  And  등록하기, 수정하기, 삭제하기 버튼으로 해당 폼을 작성할 페이지로 이동할 수 있다
     */
    @Test
    void router() {
        ExtractableResponse<Response> response = BookRequests.책_관리하기_페이지_이동();

        책_관리하기_페이지_요구사항(response);
    }

    private static void 책_관리하기_페이지_요구사항(ExtractableResponse<Response> response) {
        Document document = Jsoup.parse(response.asString());

        assertAll(
                //topic 검증: 홈
                () -> assertThat(document.select("h1").select(".section-topic").text()).isEqualTo("책 관리하기"),

                //section 검증: 등록하기, 수정하기, 삭제하기 버튼
                () -> assertThat(document.select("section").select("#register-form").select("button").attr("onclick")).isEqualTo("location.href='/books/register-form'"),
                () -> assertThat(document.select("section").select("#edit-form").select("button").attr("onclick")).isEqualTo("location.href='/books/edit-form'"),
                () -> assertThat(document.select("section").select("#delete-form").select("button").attr("onclick")).isEqualTo("location.href='/books/delete-form'")
        );
    }

    /**
     * When  책 등록하기 페이지을 요청하면
     * Then  topic 은 책 등록하기이고,
     *  And  폼에는 제목, 글쓴이, ISBN, 대표 이미지를 입력 할 수 있다.
     */
    @Test
    void registerForm() {
        ExtractableResponse<Response> response = BookRequests.책_등록하기_페이지_이동();

        책_등록하기_페이지_요구사항(response);
    }

    private void 책_등록하기_페이지_요구사항(ExtractableResponse<Response> response) {
        Document document = Jsoup.parse(response.asString());

        assertAll(
                //topic 검증: 책 등록하기
                () -> assertThat(document.select("h1").select(".section-topic").text()).isEqualTo("책 등록하기"),

                //section 검증: 폼에는 제목, 글쓴이, ISBN, 대표 이미지를 입력하고, 제출한다. 대표 이미지는 파일로 입력한다
                () -> assertThat(document.select("section").select("input#title").attr("type")).isEqualTo("text"),
                () -> assertThat(document.select("section").select("input#author").attr("type")).isEqualTo("text"),
                () -> assertThat(document.select("section").select("input#isbn").attr("type")).isEqualTo("text"),
                () -> assertThat(document.select("section").select("input#image").attr("type")).isEqualTo("file"),

                () -> assertThat(document.select("section").select("button").attr("type")).isEqualTo("submit")
        );
    }

    /**
     * Given registerForm 형식에 맞게
     * When  책 등록을 요청하면
     * Then  책이 등록되었다는 메세지를 포함하여, 등록하기 페이지를 응답한다
     */
    @Test
    void register() throws IOException {
        //Given
        BookRegisterForm form = new BookRegisterForm("Title 1", "Author 1", "ISBN 1", new MockMultipartFile("Image 1.jpg", "Mock Image 1".getBytes()));

        //When
        String jsessionId = BookRequests.책_등록하기(form).response().getCookie("JSESSIONID");

        //Then
        ExtractableResponse<Response> response = BookRequests.책_등록하기_페이지_리다이렉션(jsessionId);
        책_등록_성공시_책_등록하기_페이지_요구사항(response);
    }

    private void 책_등록_성공시_책_등록하기_페이지_요구사항(ExtractableResponse<Response> response) {
        Document document = Jsoup.parse(response.asString());

        assertThat(document.select("#alert-success").text()).isEqualTo("저장되었습니다");
        책_등록하기_페이지_요구사항(response);
    }
}
