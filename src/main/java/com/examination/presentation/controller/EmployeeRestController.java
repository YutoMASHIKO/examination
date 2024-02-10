package com.examination.presentation.controller;

import com.examination.application.GetAllEmployeesUseCase;
import com.examination.domain.Employee;
import com.examination.presentation.response.EmployeeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報を管理するためのコントローラー.
 */
@RestController
@RequiredArgsConstructor
public class EmployeeRestController {
  private final GetAllEmployeesUseCase getAllEmployeesUseCase;

  /**
   * すべての従業員を取得します.
   *
   * @return <{@link EmployeeResponse}>.
   */
  @GetMapping("v1/employees")
  @ResponseBody
  public List<EmployeeResponse> getAllEmployees() {
    return getAllEmployeesUseCase.findAllEmployee()
      .stream()
      .map(EmployeeResponse::createResponse)
      .toList();
  }

  /**
   * 指定したIDを持つ従業員の情報を取得します.
   *
   * @param id 取得したい従業員のID
   * @return <{@link EmployeeResponse}>
   */
  @GetMapping("v1/employees/{id}")
  public EmployeeResponse getEmployeeById(@PathVariable String id) {
    List<EmployeeResponse> employeeResponseList = List.of(
        EmployeeResponse.createResponse(new Employee("1", "Taro", "Yamada")),
        EmployeeResponse.createResponse(new Employee("2", "Jiro", "Yamada"))
    );

    return employeeResponseList.stream()
      .filter(response -> id.equals(response.id()))
      .findFirst()
      .orElse(null);
  }
}
