package com.element.hiking.tour.controller;

import com.element.hiking.tour.TestData;
import com.element.hiking.tour.dto.HikersDetailDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/delete-test-data.sql")
class HikerControllerTest {

    @LocalServerPort
    private Integer localeServicePort;

    @Value("http://localhost/api/hikers")
    private String baseUrl;

    @BeforeEach
    void setup() {
        RestAssured.port = localeServicePort;
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void createBookingTest() {
        final HikersDetailDTO hikersDetailDTO = TestData.getHikersDetailDTO();
        final String url = format("%s/", baseUrl);
        given()
                .header("Content-Type", "application/json")
                .when()
                .body(hikersDetailDTO)
                .post(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("paymentAmount", equalTo(59.80F))
                .body("bookingDate", equalTo(LocalDate.now().toString()));
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/test-date.sql")
    void viewBookingTest() {
        final String url = format("%s/1001", baseUrl);
        given()
                .when()
                .get(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("bookingDate", equalTo(LocalDate.of(2020, 7, 12).toString()))
                .body("hikers[0].name", equalTo("test01"));
    }

    @Test
    void viewBookingNotFoundTest() {
        final String url = format("%s/1", baseUrl);
        given()
                .when()
                .get(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    @Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/test-date.sql")
    void cancelBookingTest() {
        final String url = format("%s/1001", baseUrl);
        given()
                .when()
                .delete(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.OK.value());

        given()
                .when()
                .get(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
