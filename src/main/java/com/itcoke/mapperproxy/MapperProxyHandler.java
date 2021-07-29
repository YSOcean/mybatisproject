package com.itcoke.mapperproxy;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxyHandler implements InvocationHandler {
    private SqlSession sqlSession;
    private Class<?> targetInterface;

    public MapperProxyHandler(SqlSession sqlSession,Class<?> targetInterface){
        this.sqlSession = sqlSession;
        this.targetInterface = targetInterface;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String className = targetInterface.getName();
        String methodName = method.getName();
        String statement = className + "." + methodName;

        return sqlSession.selectOne(statement,args[0]);
    }
}
