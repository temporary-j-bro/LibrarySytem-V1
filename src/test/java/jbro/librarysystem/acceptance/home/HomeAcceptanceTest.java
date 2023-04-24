package jbro.librarysystem.acceptance.home;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import jbro.librarysystem.acceptance.AcceptanceTest;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class HomeAcceptanceTest extends AcceptanceTest {

    /**
     * When  홈 페이지를 요청하면
     * Then  topic 은 홈이고,
     *  And  검색창, 책 관리하기 라우터가 존재한다
     */
    @Test
    void home() {
        //When
        ExtractableResponse<Response> response = HomeRequests.홈_페이지_이동();

        //Then
        홈_페이지_요구사항(response);
    }

    private static void 홈_페이지_요구사항(ExtractableResponse<Response> response) {
        Document document = Jsoup.parse(response.asString());

        assertAll(
                //topic 검증: 홈
                () -> assertThat(document.select("h1").select(".section-topic").text()).isEqualTo("홈"),

                //section 검증: 검색창
                () -> assertThat(document.select("section").select(".search-container").select("form#search-box").attr("action")).isEqualTo("/books"),
                () -> assertThat(document.select("section").select(".search-container").select("form#search-box").attr("method")).isEqualTo("GET"),
                () -> assertThat(document.select("section").select(".search-container").select("form#search-box").select("input.search-input").attr("name")).isEqualTo("keyword"),
                () -> assertThat(document.select("section").select(".search-container").select("form#search-box").select("button.search-button").attr("type")).isEqualTo("submit"),

                //section 검증: 책 관리하기 라우터
                () -> assertThat(document.select("section").select(".book-management-container").select("#book-management-router").select("button").attr("onclick")).isEqualTo("location.href='/books/management'")
        );
    }
}
