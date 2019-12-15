package com.yejh.test;/**
 * @author yejh
 * @create 2019-12_14 16:00
 */

import com.yejh.bean.Employee;
import com.yejh.dao.EmployeeDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * MyBatis缓存机制：将查询出的一些数据保存在一个Map中
 * 一级缓存：线程级别的缓存；本地缓存；SqlSession级别的额缓存
 * 每次查询，先看一级缓存中有没有，如果没有就去发新的sql
 * 每个sqlSession拥有自己的一级缓存
 *
 *     一级缓存失效的几种情况：
 *     1、不同的SqlSession使用不同的一级缓存；只有在同一个SqlSession期间查询到的数据会保存在zhegesqlSession的缓存中
 *     2、同一个方法，不同的参数，由于可能之前没查询过，所以还会发新的sql
 *     3、同一个sqlSession期间执行任何一次增删改操作，增删改操作会把缓存清空
 *     4、同一个sqlSession期间手动清空缓存：sqlSession.clearCache()
 *
 *
 * 二级缓存：全局范围的缓存；namespace级别的缓存；除了当前线程；其他SqlSession也可以使用
 * SqlSession关闭或者提交之后，一级缓存的数据会放在二级缓存中
 * 需要手动开启和配置
 *     1、全局配置开启二级缓存        <setting name="cacheEnabled" value="true"/>
 *     2、配置某个dao.xml文件让其使用二级缓存      <cache/>
 *
 * 1、不会出现一级缓存和二级缓存中有用一个数据
 *     二级缓存中：一级缓存关闭了就有了
 *     一级缓存中：二级缓存中没有此数据，就会看一级缓存，一级缓存没有就去查数据库
 *                数据库的查询后的结果放在一级缓存中
 * 2、任何时候都是先看二级缓存，再看一级缓存，如果都没有采取刊讯数据库
 *
 *
 **/



public class CacheTest {

    SqlSessionFactory sqlSessionFactory;

    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void FirstCacheTest() throws IOException {
        initSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee emp = employeeDao.getEmpById(1);
        System.out.println(emp);

        Employee emp1 = employeeDao.getEmpById(1);
        System.out.println(emp1);
    }

    @Test
    public void SecondCacheTest() throws IOException {
        initSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        EmployeeDao employeeDao1 = sqlSession1.getMapper(EmployeeDao.class);

        Employee emp = employeeDao.getEmpById(1);
        System.out.println(emp);
        sqlSession.close();

        Employee emp1 = employeeDao1.getEmpById(1);
        System.out.println(emp1);
        sqlSession1.close();
    }
}