package com.yejh.dao;/**
 * @author yejh
 * @create 2019-12_12 14:15
 */

import com.yejh.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 **/
public interface EmployeeDao {
    List<Employee> getAllEmployees();

    Map<String, Object> getEmpByIdReturnMap(int id);

    @MapKey("id")
    Map<Integer, Employee> getAllEmpsReturnMap();

    Employee getEmpById(int id);

    int updateEmployee(Employee employee);

    int deleteEmployee(Integer id);

    int insertEmployee(Employee employee);

    int insertEmployee2(Employee employee);

    List<Employee> getEmployeesByCondition(Employee employee);

    List<Employee> getEmployeesByIdIn(@Param("ids") List<Integer> ids);

    List<Employee> getEmployeesByChoose(Employee employee);
}
