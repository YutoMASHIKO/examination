package com.examination.infrastructure.mapper;


import com.examination.infrastructure.entity.EmployeeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
  @Select("SELECT id, first_name, last_name FROM employees")
  List<EmployeeEntity> getAllEmployees();

  @Select("SELECT id, first_name, last_name FROM employees WHERE id = #{id}")
  EmployeeEntity getEmployeeById(String id);

  @Insert("INSERT INTO employees (id, first_name, last_name) VALUES(#{id}, #{firstName}, #{lastName})")
  Integer insert(EmployeeEntity employeeEntity);
}
