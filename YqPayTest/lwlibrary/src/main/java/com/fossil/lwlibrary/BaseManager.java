package com.fossil.lwlibrary;

import android.app.Activity;

import com.fossil.lwlibrary.log.LogManager;
import com.fossil.lwlibrary.method.Method;
import com.fossil.lwlibrary.utils.JarUtil;

/**
 * Created by Kam Wong Division on 17/5/11.
 */
public class BaseManager<T extends Method> {

    public void execute(Activity activity, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            Object[] objects = new Object[1];
            objects[0] = activity;

            Class<?>[] classes = new Class[1];
            classes[0] = Activity.class;
            t.argsClass(classes);

            String classPath = t.classPath();
            String methodName = t.methodName();
            JarUtil.executeJarClass(activity, classPath, methodName, t.args(objects), t.argsClass(classes));
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.writeInlog("execute:" + e.toString());
        }
    }
}
