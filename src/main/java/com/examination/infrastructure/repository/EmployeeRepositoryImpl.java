package com.examination.infrastructure.repository;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import com.examination.infrastructure.entity.EmployeeEntity;
import com.examination.infrastructure.exception.SqlExecutionException;
import com.examination.infrastructure.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EmployeeMapper employeeMapper;
  @Override
  public List<Employee> getAllEmployees() {
    return employeeMapper.getAllEmployees().stream().map(EmployeeEntity::convert).toList();
  }

  @Override
  public Optional<Employee> getEmployeeById(String id) {
    if (nonNull(employeeMapper.getEmployeeById(id))) {
      return Optional.of(employeeMapper.getEmployeeById(id).convert());
    }
    return Optional.empty();
  }

  @Override
  public Long getNextId() {
    return employeeMapper.getNextId();
  }

  @Override
  public Employee createEmployee(Employee employee) {
    Integer num = employeeMapper.insert(new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName()));
    if (isFailedSql(num)) {
      handleSqlExecutionFailure();
    }
    return employee;
  }

  @Override
  public void updateEmployee(Employee employee) {
    Integer num = employeeMapper.update(new EmployeeEntity(employee.id(), employee.firstName(), employee.lastName()));
    if (isFailedSql(num)) {
      handleSqlExecutionFailure();
    }
  }

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
