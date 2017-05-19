package cn.utopay.gblwsdk.utils;

import android.content.Context;

import org.apache.http.NameValuePair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Kam Wong Division on 17/5/19.
 */

public class InvokeUtil {

    private static final String className = "cn.utopay.gblwsdk.pay.Unipay";
    private static final String methodName = "doServerInit";
    private static final String initMethodName = "initInfo";
    private static final String httpClassName = "cn.utopay.gblwsdk.utils.HttpConnect";
    private static final String httpMethodName = "doHttpPost";


    private static Object instanceInvoke(Class<?> clazz, String methodName, Object[] args, Class<?>[] argsClass) throws Exception{
        Method method = invokeMethod(clazz,methodName,argsClass);
        return method.invoke(clazz.newInstance(), args);
    }

    private static Object staticInvoke(Class<?> clazz, String methodName, Object[] args, Class<?>[] argsClass) throws Exception{
        Method method = invokeMethod(clazz,methodName,argsClass);
        return method.invoke(null, args);
    }

    private static Method invokeMethod(Class<?> clazz, String methodName, Class<?>[] argsClass) throws Exception{
        Method method = clazz.getMethod(methodName,argsClass);
        method.setAccessible(true);
        return method;
    }

    public static void invokeServerInit(final Context context, final int appId, final String channel){
        try {
            Class<?> clazz = Class.forName(className);
            instanceInvoke(clazz,methodName,new Object[]{context,appId,channel},new Class<?>[]{Context.class,int.class,String.class});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invokeInitInfo(final Map<String, String> maps, final Context context){
        try {
            MyHashMap<String,String> hashMap = (MyHashMap<String, String>) maps;
            Class<?> clazz = Class.forName(className);
            instanceInvoke(clazz,initMethodName,new Object[]{hashMap,context},new Class<?>[]{MyHashMap.class,Context.class});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String invokeHttp(String httpUrl, ArrayList<NameValuePair> params, int time, boolean gzip) {
        try {
            Class<?> clazz = Class.forName(httpClassName);
            Object object = staticInvoke(clazz, httpMethodName, new Object[]{httpUrl, params, time, gzip}, new Class<?>[]{String.class, ArrayList.class, int.class, boolean.class});
            return String.valueOf(object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
