package com.examination.presentation.controller;

import com.examination.application.CreateEmployeeUseCase;
import com.examination.application.DeleteEmployeeUseCase;
import com.examination.application.GetAllEmployeesUseCase;
import com.examination.application.GetEmployeeUseCase;
import com.examination.application.UpdateEmployeeUseCase;
import com.examination.application.data.InsertEmployeeData;
import com.examination.domain.Employee;
import com.examination.presentation.request.UpdateEmployeeRequest;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @MockBean
  GetEmployeeUseCase getEmployeeUseCase;

  @MockBean
  CreateEmployeeUseCase createEmployeeUseCase;

  @MockBean
  UpdateEmployeeUseCase updateEmployeeUseCase;

  @MockBean
  DeleteEmployeeUseCase deleteEmployeeUseCase;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.mockMvc(mockMvc);
  }

  @Test
  void ルートURLへアクセスした場合() {
    given()
      .when()
      .get("/")
      .then()
      .statusCode(200);
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
      .log().all()
      .status(HttpStatus.OK)
      .body("employees[0].id", equalTo("1"))
      .body("employees[0].firstName", equalTo("Taro"))
      .body("employees[0].lastName", equalTo("Yamada"))
      .body("employees[1].id", equalTo("2"))
      .body("employees[1].firstName", equalTo("Jiro"))
      .body("employees[1].lastName", equalTo("Yamada"));
  }

  @Test
  void idが1の人を検索する場合() {
    when(getEmployeeUseCase.getEmployeeById("1"))
      .thenReturn(new Employee("1", "Taro", "Yamada"));

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
    when(getEmployeeUseCase.getEmployeeById("2"))
      .thenReturn(new Employee("2", "Jiro", "Yamada"));

    given()
      .when()
      .get("/v1/employees/2")
      .then()
      .status(HttpStatus.OK)
      .body("id", equalTo("2"))
      .body("firstName", equalTo("Jiro"))
      .body("lastName", equalTo("Yamada"));
  }

  @Test
  void 社員の新規登録を行う場合() {
    when(createEmployeeUseCase.createEmployee(new InsertEmployeeData("Hanako", "Shirato")))
      .thenReturn(new Employee("3", "Hanako", "Shirato"));

    given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body("""
        {
        "firstName": "Hanako",
        "lastName": "Shirato"
        }
        """)
      .when()
      .post("/v1/employees")
      .then()
      .statusCode(201)
      .header("Location", equalTo("http://localhost/v1/employees/3"));
  }

  @Test
  void 社員の更新を行う場合() {
    given()
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .body(new UpdateEmployeeRequest("Hanako", "Shirato"))
      .when()
      .patch("/v1/employees/1")
      .then()
      .status(HttpStatus.NO_CONTENT);
  }

  @Test
  void 社員の削除を行う場合() {
    given()
      .when()
      .delete("/v1/employees/1")
      .then()
      .status(HttpStatus.NO_CONTENT);
  }
}
