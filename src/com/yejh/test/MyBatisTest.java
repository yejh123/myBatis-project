package com.yejh.test;/**
 * @author yejh
 * @create 2019-12_12 14:10
 */

import com.yejh.bean.Employee;
import com.yejh.bean.Key;
import com.yejh.bean.Lock;
import com.yejh.dao.EmployeeDao;
import com.yejh.dao.EmployeeDaoAnnotation;
import com.yejh.dao.KeyDao;
import com.yejh.dao.LockDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description: TODO
 **/
public class MyBatisTest {

    SqlSessionFactory sqlSessionFactory;

    public void initSqlSessionFactory() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() throws IOException {
        //1、根据全局配置文件创建SqlSessionFactory
        //SqlSession：sql会话（代表和数据库的一次会话
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取和数据库的一次会话
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、使用SqlSession操作数据库，获取到dao接口的实现
        //获得的是接口的代理对象
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee = employeeDao.getEmpById(1);

        System.out.println(employee);
    }

    @Test
    public void insertTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee("张胜男", "zhangsn@qq.com", 1);
            int i = employeeDao.insertEmployee(employee);
            System.out.println("插入条数：" + i);
            System.out.println("新插入对象的id：" + employee.getId());
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void insert2Test() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee("张胜男", "zhangsn@qq.com", 1);
            int i = employeeDao.insertEmployee2(employee);
            System.out.println("插入条数：" + i);
            System.out.println("新插入对象的id：" + employee.getId());
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void EmployeeAnnotationTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDaoAnnotation employeeDaoAnnotation = sqlSession.getMapper(EmployeeDaoAnnotation.class);
            int i = employeeDaoAnnotation.insertEmployee(new Employee("张胜男", "zhangsn@qq.com", 1));
            System.out.println("插入条数：" + i);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }

    }


    @Test
    public void getAllEmployeesTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> allEmployees = employeeDao.getAllEmployees();
            System.out.println(allEmployees);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getEmpByIdReturnMapTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Map<String, Object> empByIdReturnMap = employeeDao.getEmpByIdReturnMap(1);
            System.out.println(empByIdReturnMap);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getAllEmpsReturnMapTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Map<Integer, Employee> empByIdReturnMap = employeeDao.getAllEmpsReturnMap();
            System.out.println(empByIdReturnMap);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getKeyByIdTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            KeyDao keyDao = sqlSession.getMapper(KeyDao.class);
            Key key = keyDao.getKeyById(1);
            System.out.println(key);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getLockByIdTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            LockDao lockDao = sqlSession.getMapper(LockDao.class);
            Lock lock = lockDao.getLockById(3);
            System.out.println(lock);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    /**
     * 分步查询，延迟加载
     * DEBUG 12-13 20:55:33,661 ==>  Preparing: select * from t_lock where id=?   (BaseJdbcLogger.java:143)
     * DEBUG 12-13 20:55:33,690 ==> Parameters: 3(Integer)  (BaseJdbcLogger.java:143)
     * DEBUG 12-13 20:55:33,744 <==      Total: 1  (BaseJdbcLogger.java:143)
     * 三号锁
     * DEBUG 12-13 20:55:33,745 ==>  Preparing: select * from t_key where lockId=?   (BaseJdbcLogger.java:143)
     * DEBUG 12-13 20:55:33,745 ==> Parameters: 3(Integer)  (BaseJdbcLogger.java:143)
     * DEBUG 12-13 20:55:33,747 <==      Total: 2  (BaseJdbcLogger.java:143)
     * [Key{id=3, keyName='301门钥匙1', lock=null}, Key{id=4, keyName='301门钥匙2', lock=null}]
     */
    @Test
    public void getLockByIdByStepTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            LockDao lockDao = sqlSession.getMapper(LockDao.class);
            Lock lock = lockDao.getLockByIdByStep(3);
            System.out.println(lock.getLockName());
            System.out.println(lock.getKeys());
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    //动态sql
    @Test
    public void getEmployeesByConditionTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee();
            employee.setId(1);
            employee.setEmpName("%男%");

            List<Employee> employees = employeeDao.getEmployeesByCondition(employee);
            System.out.println(employees);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getEmployeesByIdInTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            List<Employee> employees = employeeDao.getEmployeesByIdIn(Arrays.asList(1, 2, 3, 4, 5, 6));
            System.out.println(employees);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void getEmployeesByChooseTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee();
            //employee.setEmpName("%男%");
            List<Employee> employees = employeeDao.getEmployeesByChoose(employee);
            System.out.println(employees);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void updateEmployeeTest() throws IOException {
        initSqlSessionFactory();
        //参数为true代表自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = new Employee();
            employee.setId(4);
            employee.setEmail("123@qq.com");
            int i = employeeDao.updateEmployee(employee);
            System.out.println(i);
        } finally {
            //手动提交
            //sqlSession.commit();
            sqlSession.close();
        }
    }
}
