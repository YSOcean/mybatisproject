package com.itcoke.mapperproxy;

import com.itcoke.bean.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperProxyTest {

    public static void main(String[] args) {
        // 1、获取目标接口对象
        Class<?> targetInterface = PersonMapper.class;
        // 2、获取 SqlSession 对象
        SqlSession sqlSession = getSqlSession();
        MapperProxyHandler proxyHandler = new MapperProxyHandler(sqlSession,targetInterface);
        MapperProxyFactory mapperProxyFactory = new MapperProxyFactory(PersonMapper.class);
        PersonMapper personMapper = (PersonMapper)mapperProxyFactory.newInstance(proxyHandler);
        Person person = personMapper.selectPersonById(1L);
        System.out.println(person);
    }



    public static SqlSession getSqlSession() {
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
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return  sessionFactory.openSession();
    }
}
