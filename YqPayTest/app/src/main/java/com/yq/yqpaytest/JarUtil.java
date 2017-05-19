package com.yq.yqpaytest;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class JarUtil {

    private Context context;

    public JarUtil(Context context) {
        this.context = context;
    }

    /**
     * 这个方法所加载的jar包通过add external jars添加
     *
     * @param classPath  class在jar包的路径
     * @param methodName 要执行的方法名
     * @param args       要执行的方法所带的参数
     * @return 执行完方法的返回值
     */
    public static Object executeJarClass(String classPath, String methodName, Object... args) throws Exception {
        Object ret = null;
        Class<?> c = null;
        c = Class.forName(classPath);

        Class<?>[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = c.getDeclaredMethod(methodName, argsClass);
        method.setAccessible(true);
        ret = method.invoke(c.newInstance(), args);
        return ret;
    }

    /**
     * 这个方法所加载的jar包通过add external jars添加
     *
     * @param classPath  class在jar包的路径
     * @param methodName 要执行的方法名
     * @param args       要执行的方法所带的参数
     * @param argsClass  要执行的方法所带的参数的类型
     * @return 执行完方法的返回值
     */
    public static Object executeJarClass(String classPath, String methodName, Object[] args, Class<?>[] argsClass)
            throws Exception {
        Object ret = null;
        Class<?> c = null;
        c = Class.forName(classPath);
        Method method = c.getDeclaredMethod(methodName, argsClass);
        method.setAccessible(true);
        ret = method.invoke(c.newInstance(), args);
        return ret;
    }

    /**
     * 这个方法所加载的jar包存放在assets目录
     *
     * @param jarPath    要生成在本地的jar包目录
     * @param classPath  class在jar包的路径
     * @param methodName 要执行的方法名
     * @param args       要执行的方法所带的参数
     * @return 执行完方法的返回值
     */
    public Object executeJarClass(String jarPath, String jarName, String classPath, String methodName, Object... args)
            throws Exception {
        Object ret = null;
        Class<?> c = getClassObject(jarPath, jarName, classPath);
        Class<?>[] argsClass = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = c.getDeclaredMethod(methodName, argsClass);
        method.setAccessible(true);
        ret = method.invoke(c.newInstance(), args);
        return ret;
    }

    /**
     * @param jarPath   要生成在本地的jar包目录
     * @param classPath class在jar包的路径
     * @return 要加载的class对象
     */
    public Class<?> getClassObject(String jarPath, String jarName, String classPath) throws Exception {
        // 从assects中读取文件并放到jarPath目录
        File file = new File(jarPath, jarName);
        if (!file.exists()) {
            file.createNewFile();
        }
        InputStream in = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        try {
            in = context.getAssets().open(jarName);
            bis = new BufferedInputStream(in);
            fos = new FileOutputStream(file);

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = bis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            in.close();
            bis.close();
            fos.close();
        }

        // 用DexClassLoader加载用dx.bat命令再编译过的jar包。java se中对应的是URLClassLoader
        DexClassLoader classLoader = new DexClassLoader(file.getPath(),
                jarPath, null, ClassLoader.getSystemClassLoader().getParent());

        // 可以在加载完的时候将生成在本地的.jar和.dex删除
        file.delete();
        file = new File(jarPath + "/" + jarName.substring(0, jarName.length() - 4) + ".dex");
        file.delete();
        Class<?> c = classLoader.loadClass(classPath);
        return c;
    }


    public static void main(String[] a) {
        Object[] args = new Object[2];
        args[0] = "str_agr1";
        args[1] = 2;
        Class<?>[] argsClass = new Class<?>[2];
        argsClass[0] = String.class;
        argsClass[1] = int.class;
        try {
            CharSequence charSequence = (CharSequence) JarUtil.executeJarClass("com.hidejar.test.InstallFactory", "LoadInstall", args,
                    argsClass);
            CharSequence b = (CharSequence) JarUtil.executeJarClass("com.hidejar.test.InstallFactory", "MyInstall", "str_agr1",
                    2);
//            Toast.makeText(
//                    MainActivity.this,
//                    (CharSequence) JarUtil.executeJarClass("com.hidejar.test.InstallFactory", "LoadInstall", args,
//                            argsClass), Toast.LENGTH_SHORT).show();

//            Toast.makeText(
//                    MainActivity.this,
//                    (CharSequence) JarUtil.executeJarClass("com.hidejar.test.InstallFactory", "MyInstall", "str_agr1",
//                            2), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
//            Toast.makeText(MainActivity.this, "反射出现错误", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
