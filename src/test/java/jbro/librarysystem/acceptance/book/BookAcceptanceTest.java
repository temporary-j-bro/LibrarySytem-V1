package jbro.librarysystem.acceptance.book;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jbro.librarysystem.acceptance.AcceptanceTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

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
}
