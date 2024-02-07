package com.examination.application;

import com.examination.domain.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllEmployeeUseCase {
  public List<Employee> findAllEmployee() {
    return List.of(
      new Employee("1", "Taro", "Yamada"),
      new Employee("2", "Jiro", "Yamada")
    );
  }
}
