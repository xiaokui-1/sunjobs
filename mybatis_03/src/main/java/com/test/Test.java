package com.test;

import com.pojo.Dep;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Test extends TestCase {

    public void _testAdd() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Dep dep=new Dep();
        dep.setDepname("消防部");

        int n = sqlSession.insert("com.pojo.Dep.insert",dep);
        sqlSession.commit();
        sqlSession.close();
        System.out.println(n);
    }

    public void testAddEmp() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

    }
}
