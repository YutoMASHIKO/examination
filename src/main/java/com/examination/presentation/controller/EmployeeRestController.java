package com.examination.presentation.controller;

import com.examination.application.GetAllEmployeesUseCase;
import com.examination.application.GetEmployeeUseCase;
import com.examination.domain.Employee;
import com.examination.presentation.response.EmployeeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報を管理するためのコントローラー.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeeRestController {
  private final GetAllEmployeesUseCase getAllEmployeesUseCase;
  private final GetEmployeeUseCase getEmployeeUseCase;

  /**
   * rootURLにアクセスされた際に、ステータスコード200を返します.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public void accessRoot() {
    // 何もしない
  }

  /**
   * すべての従業員を取得します.
   *
   * @return <{@link EmployeeResponse}>.
   */
  @GetMapping("v1/employees")
  @ResponseStatus(HttpStatus.OK)
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
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponse getEmployeeById(@PathVariable String id) {
    Employee employee = getEmployeeUseCase.getEmployeeById(id);
    return EmployeeResponse.createResponse(employee);
  }
}
