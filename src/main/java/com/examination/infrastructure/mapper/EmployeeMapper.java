package com.examination.infrastructure.mapper;

import com.examination.domain.Employee;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
  @Select("SELECT id, first_name, last_name FROM employees")
  List<Employee> getAllEmployees();
}
