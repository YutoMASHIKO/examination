package com.examination.presentation.controller;

import com.examination.application.CreateEmployeeUseCase;
import com.examination.application.GetAllEmployeesUseCase;
import com.examination.application.GetEmployeeUseCase;
import com.examination.application.UpdateEmployeeUseCase;
import com.examination.application.data.InsertEmployeeData;
import com.examination.application.data.UpdateEmployeeData;
import com.examination.domain.Employee;
import com.examination.presentation.request.CreateEmployeeRequest;
import com.examination.presentation.request.UpdateEmployeeRequest;
import com.examination.presentation.response.AllEmployeeResponse;
import com.examination.presentation.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * 従業員情報を管理するためのコントローラー.
 */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmployeeRestController {
  private final GetAllEmployeesUseCase getAllEmployeesUseCase;
  private final GetEmployeeUseCase getEmployeeUseCase;
  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;

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
  public AllEmployeeResponse getAllEmployees() {
    return AllEmployeeResponse.createResponse(getAllEmployeesUseCase.findAllEmployee());
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

  @PostMapping("v1/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> insertEmployee(@RequestBody @Validated CreateEmployeeRequest request) {
    Employee employee = createEmployeeUseCase.createEmployee(
      new InsertEmployeeData(request.firstName(), request.lastName())
    );

    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .pathSegment(employee.id())
        .build()
        .encode()
        .toUri();

    return ResponseEntity.created(location).build();
  }

  @PatchMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEmployee(@PathVariable("id") String id, @RequestBody UpdateEmployeeRequest request) {
    updateEmployeeUseCase.update(new UpdateEmployeeData(id, request.firstName(), request.lastName()));
  }
}
