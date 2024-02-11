package com.examination.infrastructure.repository;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import com.examination.infrastructure.entity.EmployeeEntity;
import com.examination.infrastructure.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
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
    return employee;
  }

  @Override
  public void updateEmployee(Employee employee) {

  }

  @Override
  public void deleteEmployee(String id) {

  }
}
