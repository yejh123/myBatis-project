package com.yejh.bean;/**
 * @author yejh
 * @create 2019-12_11 23:29
 */

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @description: TODO
 **/

//通过Alias注解起别名
//@Alias("Employee")
public class Employee {
    private Integer id;
    private String empName;
    private String email;
    private Integer gender;

    public Employee() {
    }

    public Employee(String empName, String email, Integer gender) {
        this.empName = empName;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
