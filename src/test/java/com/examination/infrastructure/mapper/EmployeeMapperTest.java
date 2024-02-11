package com.examination.infrastructure.mapper;

import java.sql.DriverManager;
import java.util.List;

import com.examination.infrastructure.entity.EmployeeEntity;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
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
    List<EmployeeEntity> expected = List.of(
      new EmployeeEntity("1", "Taro", "Yamada"),
      new EmployeeEntity("2", "Jiro", "Yamada")
    );

    List<EmployeeEntity> actual = sut.getAllEmployees();

    assertEquals(actual, expected);
  }

  @Test
  @DataSet(value = "datasets/employee.yml")
  void idにより個別に社員情報を取得する場合() {
    EmployeeEntity expected = new EmployeeEntity("1", "Taro", "Tanaka");

    EmployeeEntity actual = sut.getEmployeeById("1");

    assertEquals(expected, actual);
  }

  @Test
  @DataSet(value = "datasets/employees.yml")
  void 次の従業員IDを取得する場合() {
    Long actual = sut.getNextId();

    assertEquals(3L, actual);
  }

  @Test
  @DataSet(value = "datasets/employees.yml")
  @ExpectedDataSet(value = "datasets/insertEmployee.yml")
  void 従業員の新規登録をする場合() {
    Integer actual = sut.insert(new EmployeeEntity("3", "Hanako", "Shirato"));

    assertEquals(1, actual);
  }
}
