package presentation.controller;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class EmployeeRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void 全件取得をする場合() {
        RestAssuredMockMvc.given()
                .when()
                .get("/employees")
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
}
