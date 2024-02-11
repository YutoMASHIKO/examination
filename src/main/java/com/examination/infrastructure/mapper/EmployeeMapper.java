package com.examination.infrastructure.mapper;

import com.examination.infrastructure.entity.EmployeeEntity;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 従業員情報をデータベースとやり取りするためのマッパーインターフェースです.
 */
@Mapper
public interface EmployeeMapper {

  /**
   * すべての従業員を取得します.
   *
   * @return 従業員のリスト
   */
  @Select("SELECT id, first_name, last_name FROM employees")
  List<EmployeeEntity> getAllEmployees();

  /**
   * 指定されたIDの従業員を取得します.
   *
   * @param id 取得したい従業員のID
   * @return 従業員エンティティ
   */
  @Select("SELECT id, first_name, last_name FROM employees WHERE id = #{id}")
  EmployeeEntity getEmployeeById(String id);

  /**
   * 次の従業員のIDを取得します.
   *
   * @return 次の従業員の ID
   */
  @Select("SELECT nextval('EMPLOYEE_ID_SEQ')")
  @Options(flushCache = Options.FlushCachePolicy.TRUE)
  Long getNextId();

  /**
   * 従業員を新規登録します.
   *
   * @param employeeEntity 新規登録をしたい従業員エンティティ
   * @return 新規登録の件数
   */
  @Insert("INSERT INTO employees (id, first_name, last_name) VALUES(#{id}, #{firstName}, #{lastName})")
  Integer insert(EmployeeEntity employeeEntity);

  /**
   * 指定したIDの従業員情報を更新します.
   *
   * @param employeeEntity 更新する従業員エンティティ
   * @return 更新した件数
   */
  @Update("UPDATE employees SET first_name = #{firstName}, last_name = #{lastName} WHERE id = #{id}")
  Integer update(EmployeeEntity employeeEntity);

  /**
   * 指定されたIDの従業員エンティティを削除します.
   *
   * @param id 削除する従業員のID
   * @return 削除する件数
   */
  @Delete("DELETE FROM employees WHERE id = #{id}")
  Integer delete(String id);
}
