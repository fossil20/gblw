package com.fossil.lwlibrary.method;

/**
 * Created by Kam Wong Division on 17/5/11.
 */
public abstract class Method {

    public abstract String classPath();

    public abstract String methodName();

    public Object[] args(Object... objects){
        return objects;
    }

    public Class<?>[] argsClass(Class<?>... argsClass){
        return argsClass;
    }
}
