package com.examination.presentation.controller;

import com.examination.domain.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.examination.presentation.response.EmployeeResponse;

import java.util.List;

@RestController
public class EmployeeRestController {
  @GetMapping("v1/employees")
  @ResponseBody
  public List<EmployeeResponse> getAllEmployees() {
    return List.of(
      EmployeeResponse.createResponse(new Employee("1", "Taro", "Yamada")),
      EmployeeResponse.createResponse(new Employee("2", "Jiro", "Yamada"))
    );
  }
}
