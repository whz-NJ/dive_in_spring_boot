package com.imooc.diveinspringboot;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class PermgenOOM {
    public static void main(String[] args) throws InterruptedException {
        int i=0;
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Product.class);
            enhancer.setUseCache(false);// 关闭CGLib缓存，否则总是生成同一个类，Spring里通过什么方式创建的代理？？
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args,
                        MethodProxy methodproxy) throws Throwable {
                    // TODO Auto-generated method stub
                    return methodproxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
            // Thread.sleep(100);
        }
    }
}