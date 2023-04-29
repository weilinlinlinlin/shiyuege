package com.itheima.reggie.common;

// 基于ThreadLocald封装工具类 用于保存和获取当前登录的用户id 以线程为作用域
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
