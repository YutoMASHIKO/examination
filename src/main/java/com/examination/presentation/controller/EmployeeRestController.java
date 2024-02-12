package com.examination.presentation.controller;

import com.examination.application.CreateEmployeeUseCase;
import com.examination.application.DeleteEmployeeUseCase;
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
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  private final DeleteEmployeeUseCase deleteEmployeeUseCase;

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
   * @return すべての従業員情報を含むAllEmployeeResponse
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
   * @return 取得した従業員情報を含むEmployeeResponse
   */
  @GetMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.OK)
  public EmployeeResponse getEmployeeById(@PathVariable String id) {
    Employee employee = getEmployeeUseCase.getEmployeeById(id);
    return EmployeeResponse.createResponse(employee);
  }

  /**
   * 従業員を新しく作成します.
   *
   * @param request 新しい従業員の情報を含むリクエストデータ
   * @return 作成された従業員のURIを含むレスポンス
   */
  @PostMapping("v1/employees")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Void> insertEmployee(
      @RequestBody @Validated CreateEmployeeRequest request
  ) {
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

  /**
   * 従業員の情報を部分的に更新します.
   *
   * @param id      更新したい従業員のID
   * @param request 更新する情報を含むリクエストデータ
   */
  @PatchMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEmployee(
      @PathVariable("id") String id,
      @RequestBody UpdateEmployeeRequest request
  ) {
    updateEmployeeUseCase.update(
        new UpdateEmployeeData(id, request.firstName(), request.lastName())
    );
  }

  /**
   * 指定したIDの従業員情報を削除します.
   *
   * @param id 削除したい従業員のID
   */
  @DeleteMapping("v1/employees/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployee(@PathVariable("id") String id) {
    deleteEmployeeUseCase.deleteEmployee(id);
  }
}
