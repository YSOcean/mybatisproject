package com.itcoke;

import com.itcoke.bean.Person;
import com.itcoke.mapper.PersonMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisprojectApplicationTests {
    // 定义SqlSessionFactory
    SqlSessionFactory sessionFactory = null;

    @Before
    public void init() {
        //定义mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //加载 mybatis 全局配置文件
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    //根据id查询person表数据
    @Test
    public void testSelectPersonById() {
        /*这个字符串由 PersonMapper.xml 文件中 两个部分构成
            <mapper namespace="com.itcoke.mapper.PersonMapper"> 的 namespace 的值
            <select id="selectPersonById" > id 值
        */
        String namespace = "com.itcoke.mapper.PersonMapper";
        String method = "selectPersonById";
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession(ExecutorType.SIMPLE);
        Person person = sqlSession.selectOne(namespace + "." + method, 1L);
        System.out.println(person);
        sqlSession.close();
    }


    //根据id查询person表数据
    //通过接口代理的方式
    @Test
    public void testInterfaceSelectPersonById() {
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person = mapper.selectPersonById(1L);
        System.out.println(person);
        sqlSession.close();
    }

    //根据id更新person表数据
    @Test
    public void testUpdatePersonById() {
        String statement = "com.itcoke.mapper.PersonMapper.updatePersonById";
        Person p = new Person();
        p.setPid(2L);
        p.setPage(18);
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.update(statement, p);
        sqlSession.commit();
        sqlSession.close();
    }


    //向 person 表插入数据
    @Test
    public void testInsertPerson() {
        String statement = "com.itcoke.mapper.PersonMapper.insertPerson";
        Person p = new Person();
        p.setPname("可乐");
        p.setPage(18);
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.insert(statement, p);
        sqlSession.commit();
        sqlSession.close();
    }

    //根据id更新person表数据
    @Test
    public void testDeletePersonById() {
        String statement = "com.itcoke.mapper.PersonMapper.deletePersonById";
        Person p = new Person();
        p.setPid(4L);
        //根据 sqlSessionFactory 产生 session
        SqlSession sqlSession = sessionFactory.openSession();
        sqlSession.delete(statement, p);
        sqlSession.commit();
        sqlSession.close();
    }
}
