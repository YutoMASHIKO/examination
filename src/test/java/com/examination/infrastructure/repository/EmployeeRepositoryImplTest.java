package com.examination.infrastructure.repository;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.examination.domain.Employee;
import com.examination.infrastructure.entity.EmployeeEntity;
import com.examination.infrastructure.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class EmployeeRepositoryImplTest {
  @InjectMocks
  EmployeeRepositoryImpl sut;

  @Mock
  EmployeeMapper employeeMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件取得をする場合() {
    when(employeeMapper.getAllEmployees())
      .thenReturn(
        List.of(
          new EmployeeEntity("1", "Taro", "Yamada"),
          new EmployeeEntity("2", "Jiro", "Yamada")
        )
      );

    List<Employee> expected = List.of(
      new Employee("1", "Taro", "Yamada"),
      new Employee("2", "Jiro", "Yamada")
    );

    List<Employee> actual = sut.getAllEmployees();

    assertEquals(expected, actual);
  }

  @Nested
  class id検索 {
    @Test
    void idによる従業員の情報を取得できる場合() {
      when(employeeMapper.getEmployeeById("3"))
        .thenReturn(new EmployeeEntity("3", "Taro", "Tanaka"));

      Optional<Employee> expected = Optional.of(new Employee("3", "Taro", "Tanaka"));

      Optional<Employee> actual = sut.getEmployeeById("3");

      assertEquals(expected, actual);
    }

    @Test
    void idによる従業員の情報を取得できない場合() {
      when(employeeMapper.getEmployeeById("0")).thenReturn(null);

      Optional<Employee> expected = Optional.empty();

      Optional<Employee> actual = sut.getEmployeeById("0");

      assertEquals(expected, actual);
    }
  }

  @Test
  void 次の授業員Idを取得する場合() {
    when(employeeMapper.getNextId()).thenReturn(3L);

    Long actual = sut.getNextId();

    assertEquals(3L, actual);
  }

  @Test
  void 従業員の新規登録をする場合() {
    when(employeeMapper.insert(new EmployeeEntity("3", "Hanako", "Shirato")))
      .thenReturn(1);

    Employee expected = new Employee("3", "Hanako", "Shirato");

    Employee actual = sut.createEmployee(new Employee("3", "Hanako", "Shirato"));

    assertEquals(expected, actual);
  }

  @Test
  void 従業員の更新を行う場合() {
    when(employeeMapper.update(new EmployeeEntity("1", "Taro", "Tanaka")))
      .thenReturn(1);

    Employee employee = new Employee("1", "Taro", "Tanaka");

    assertDoesNotThrow(() -> sut.updateEmployee(employee));
  }

  @Test
  void 従業員の削除を行う場合() {
    when(employeeMapper.delete("1")).thenReturn(1);

    assertDoesNotThrow(() -> sut.deleteEmployee("1"));
  }
}
