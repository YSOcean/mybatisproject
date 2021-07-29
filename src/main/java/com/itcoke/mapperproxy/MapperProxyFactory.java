package com.itcoke.mapperproxy;

import java.lang.reflect.Proxy;

public class MapperProxyFactory {
    private Class<?> targetInterface;

    public MapperProxyFactory(Class<?> targetInterface){
        this.targetInterface = targetInterface;
    }

    public Object newInstance(MapperProxyHandler handler){
        return Proxy.newProxyInstance(targetInterface.getClassLoader(),
                new Class[]{targetInterface},
                handler);
    }
}
