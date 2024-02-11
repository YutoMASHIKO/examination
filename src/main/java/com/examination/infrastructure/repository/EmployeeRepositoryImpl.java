package com.examination.infrastructure.repository;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import com.examination.infrastructure.entity.EmployeeEntity;
import com.examination.infrastructure.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
  private final EmployeeMapper employeeMapper;
  @Override
  public List<Employee> getAllEmployees() {
    return employeeMapper.getAllEmployees().stream().map(EmployeeEntity::convert).toList();
  }

  @Override
  public Employee getEmployeeById(String id) {
    return employeeMapper.getEmployeeById(id).convert();
  }
}
