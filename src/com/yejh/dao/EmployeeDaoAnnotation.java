package com.yejh.dao;/**
 * @author yejh
 * @create 2019-12_12 23:49
 */

import com.yejh.bean.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @description: TODO
 **/
public interface EmployeeDaoAnnotation {
    @Select("select * from employee where id = #{id}")
    Employee getEmpById(int id);

    @Update("update employee set empName=#{empName}, email=#{email}, gender=#{gender} " +
            "where id=#{id}")
    int updateEmployee(Employee employee);

    @Delete("delete from employee where id=#{id}")
    int deleteEmployee(Integer id);

    @Insert("insert into employee(empName, email, gender)" +
            " values(#{empName}, #{email}, #{gender})")
    int insertEmployee(Employee employee);

}
