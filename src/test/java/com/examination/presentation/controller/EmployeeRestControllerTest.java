package com.examination.presentation.controller;

import com.examination.application.GetAllEmployeesUseCase;
import com.examination.domain.Employee;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;

@WebMvcTest
class EmployeeRestControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  GetAllEmployeesUseCase getAllEmployeesUseCase;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void 全件取得をする場合() {
    when(getAllEmployeesUseCase.findAllEmployee())
      .thenReturn(
        List.of(
          new Employee("1", "Taro", "Yamada"),
          new Employee("2", "Jiro", "Yamada")
        )
      );

    given()
      .when()
      .get("/v1/employees")
      .then()
      .status(HttpStatus.OK)
      .body("$.size()", equalTo(2))
      .body("[0].id", equalTo("1"))
      .body("[0].firstName", equalTo("Taro"))
      .body("[0].lastName", equalTo("Yamada"))
      .body("[1].id", equalTo("2"))
      .body("[1].firstName", equalTo("Jiro"))
      .body("[1].lastName", equalTo("Yamada"));
  }

  @Test
  void idが1の人を検索する場合() {
    given()
      .when()
      .get("/v1/employees/1")
      .then()
      .status(HttpStatus.OK)
      .body("id", equalTo("1"))
      .body("firstName", equalTo("Taro"))
      .body("lastName", equalTo("Yamada"));
  }

  @Test
  void idが2の人を検索する場合() {
    given()
      .when()
      .get("/v1/employees/2")
      .then()
      .status(HttpStatus.OK)
      .body("id", equalTo("2"))
      .body("firstName", equalTo("Jiro"))
      .body("lastName", equalTo("Yamada"));
  }
}
