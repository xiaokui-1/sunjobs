package com.test;

import com.pojo.Dep;
import com.pojo.Emp;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Test extends TestCase {

    public void _testAdd() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession(ExecutorType.BATCH);

        Dep dep =new Dep();
        dep.setDepname("信息部");
        Dep dep2 =new Dep();
        dep2.setDepname("生物部");
        Dep dep3 =new Dep();
        dep3.setDepname("科研部");

        sqlSession.insert("com.pojo.Dep.insert",dep);
        sqlSession.insert("com.pojo.Dep.insert",dep2);
        sqlSession.insert("com.pojo.Dep.insert",dep3);
        sqlSession.commit();
        sqlSession.close();
    }

    public void _testUpdate() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();

        Emp emp=new Emp();
        emp.setEmpid("3");
        emp.setDepid(2);
        emp.setEmpname("肖锋");

        sqlSession.update("com.pojo.Emp.update",emp);
        sqlSession.commit();
        sqlSession.close();


    }
    public void _testFindAll() throws IOException {

        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Emp emp = new Emp();
        emp.setEmpid("1");
        emp.setEmpname("葵");

        List<Emp> list = sqlSession.selectList("com.pojo.Emp.findby",emp);
        for (Emp e : list) {
            System.out.println(e.getEmpid()+"\t"+e.getEmpname()+"\t"+e.getDepid());
        }

    }
    public void testBatch() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Emp emp = new Emp();
        emp.setEmpid("00112");
        emp.setEmpname("夏剑");


        Emp emp2 = new Emp();
        emp2.setEmpid("00212");
        emp2.setEmpname("史珍香");


        Emp emp3 = new Emp();
        emp3.setEmpid("00312");
        emp3.setEmpname("秦寿");


        List<Emp> list = new ArrayList<Emp>();
        list.add(emp);
        list.add(emp2);
        list.add(emp3);

        int n = sqlSession.insert("com.pojo.Emp.batchinsert" , list);
        sqlSession.commit();
        sqlSession.close();



    }
}
