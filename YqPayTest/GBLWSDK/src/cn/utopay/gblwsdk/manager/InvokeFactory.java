package cn.utopay.gblwsdk.manager;

import java.lang.reflect.Method;

import cn.utopay.gblwsdk.manager.method.Function;

/**
 * Created by Kam Wong Division on 17/5/19.
 */
public class InvokeFactory<T extends Function> {

    private static class SingletonHolder {
        private static final InvokeFactory invokeFactory = new InvokeFactory();
    }

    public static InvokeFactory getInstance() {
        return SingletonHolder.invokeFactory;
    }

     public Object instanceExecute(Object[] args,Class<?>[] argsClass,Class<T> cla){
         try {
             T t = cla.newInstance();
             Class<?> clazz = Class.forName(t.classPath());
             return instanceInvoke(clazz,t.functionName(),args,argsClass);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
     }

    public Object staticExecute(Object[] args,Class<?>[] argsClass,Class<T> cla){
        try {
            T t = cla.newInstance();
            Class<?> clazz = Class.forName(t.classPath());
            return staticInvoke(clazz,t.functionName(),args,argsClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object instanceInvoke(Class<?> clazz, String methodName, Object[] args, Class<?>[] argsClass) throws Exception{
        Method method = invokeFunction(clazz,methodName,argsClass);
        return method.invoke(clazz.newInstance(), args);
    }

    private Object staticInvoke(Class<?> clazz, String methodName, Object[] args, Class<?>[] argsClass) throws Exception{
        Method method = invokeFunction(clazz,methodName,argsClass);
        return method.invoke(null, args);
    }

    private Method invokeFunction(Class<?> clazz, String methodName, Class<?>[] argsClass) throws Exception{
        Method method = clazz.getMethod(methodName,argsClass);
        method.setAccessible(true);
        return method;
    }

}
