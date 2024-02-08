package com.examination.infrastructure.mapper;

import java.sql.DriverManager;
import java.util.List;

import com.examination.domain.Employee;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@DBRider
@DBUnit
@SpringBootTest
class EmployeeMapperTest {
  private static final String DB_URL = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false";
  private static final String DB_USER = "user";
  private static final String DB_PASSWORD = "password";

  private static final ConnectionHolder connectionHolder =
    () -> DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

  @Autowired
  EmployeeMapper sut;

  @BeforeAll
  static void setUpAll() {
    Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load().migrate();
  }

  @Test
  @DataSet(value = "datasets/employees.yml")
  void 全件取得をする場合() {
    List<Employee> actual = sut.getAllEmployees();

    List<Employee> expected = List.of(
      new Employee("1", "Taro", "Yamada"),
      new Employee("2", "Jiro", "Yamada")
    );

    assertEquals(actual, expected);
  }

  @Test
  @DataSet(value = "datasets/employee.yml")
  void idにより個別に社員情報を取得する場合() {
    Employee actual = sut.getEmployeeById("1");

    // 期待値の作成
    Employee expected = new Employee("1", "Taro", "Tanaka");

    // 検証
    assertEquals(expected, actual);
  }
}
