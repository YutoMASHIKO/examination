package com.examination.infrastructure.mapper;


import com.examination.infrastructure.entity.EmployeeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface EmployeeMapper {
  @Select("SELECT id, first_name, last_name FROM employees")
  List<EmployeeEntity> getAllEmployees();

  @Select("SELECT id, first_name, last_name FROM employees WHERE id = #{id}")
  EmployeeEntity getEmployeeById(String id);

  @Select("SELECT nextval('EMPLOYEE_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextId();

  @Insert("INSERT INTO employees (id, first_name, last_name) VALUES(#{id}, #{firstName}, #{lastName})")
  Integer insert(EmployeeEntity employeeEntity);

  @Update("UPDATE employees SET first_name = #{firstName}, last_name = #{lastName} WHERE id = #{id}")
  Integer update(EmployeeEntity employeeEntity);

  @Delete("DELETE FROM employees WHERE id = #{id}")
  Integer delete(String id);
}
