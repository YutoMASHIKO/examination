package com.examination.infrastructure.repository;

import static java.util.Objects.nonNull;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import com.examination.infrastructure.entity.EmployeeEntity;
import com.examination.infrastructure.exception.SqlExecutionException;
import com.examination.infrastructure.mapper.EmployeeMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/**
 * 従業員リポジトリの実装クラスです.
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EmployeeMapper employeeMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Employee> getAllEmployees() {
    return employeeMapper.getAllEmployees().stream().map(EmployeeEntity::convert).toList();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Employee> getEmployeeById(String id) {
    if (nonNull(employeeMapper.getEmployeeById(id))) {
      return Optional.of(employeeMapper.getEmployeeById(id).convert());
    }
    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long getNextId() {
    return employeeMapper.getNextId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Employee createEmployee(Employee employee) {
    Integer num = employeeMapper.insert(new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName()));
    if (isFailedSql(num)) {
      handleSqlExecutionFailure();
    }
    return employee;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateEmployee(Employee employee) {
    Integer num = employeeMapper.update(new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName()));
    if (isFailedSql(num)) {
      handleSqlExecutionFailure();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEmployee(String id) {
    Integer num = employeeMapper.delete(id);
    if (isFailedSql(num)) {
      handleSqlExecutionFailure();
    }
  }

  private boolean isFailedSql(Integer number) {
    return number != 1;
  }

  private void handleSqlExecutionFailure() {
    log.error("SQLの実行に失敗しました");
    throw new SqlExecutionException("SQLの実行に失敗しました");
  }
}
