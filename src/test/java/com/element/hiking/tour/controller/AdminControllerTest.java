package com.element.hiking.tour.controller;

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
import static org.hamcrest.Matchers.contains;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = "/sql/test-date.sql")
@Sql(executionPhase = AFTER_TEST_METHOD, scripts = "/sql/delete-test-data.sql")
class AdminControllerTest {

    @LocalServerPort
    private Integer localeServicePort;

    @Value("http://localhost/api/admin")
    private String baseUrl;

    @BeforeEach
    void setup() {
        RestAssured.port = localeServicePort;
        RestAssured.baseURI = baseUrl;
    }

    @Test
    void viewBookingTest() {
        final LocalDate date = LocalDate.of(2020, 7, 12);
        final String url = format("%s/hikers/%s", baseUrl, date);
        given()
                .when()
                .get(url)
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("bookingDate", contains(date.toString()))
                .body("hikers[0].name", contains("test01"));
    }
}
