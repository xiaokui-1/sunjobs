package com.test;

import com.pojo.Dep;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test extends TestCase {
    public void _testFindall() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        List<Dep> list = sqlSession.selectList("com.pojo.Dep.findall");

        for (Dep dep : list) {
            System.out.println(dep.getDepid()+"\t"+dep.getDepname());
        }
    }
    public void testUpdate() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Dep dep =new Dep();
        dep.setDepid(2);
        dep.setDepname("信息部");

        int n = sqlSession.update("com.pojo.Dep.findall",dep);
    }
}
