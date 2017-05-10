package cn.utopay.gblwsdk.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;

public final class OpUtil {
    private static final int OP_WRITE_SMS = 15;

    public static boolean isWriteEnabled(Context context) {
        int uid = getUid(context);
        Object opRes = checkOp(context, OP_WRITE_SMS, uid);
        if (opRes instanceof Integer) {
            return (Integer) opRes == AppOpsManager.MODE_ALLOWED;
        }
        return false;
    }

    public static boolean setWriteEnabled(Context context, boolean enabled) {
        int uid = getUid(context);
        int mode = enabled ?
            AppOpsManager.MODE_ALLOWED : AppOpsManager.MODE_IGNORED;
        for(int i = 0; i <= 20; i++) {
        	setMode(context, i, uid, mode);
        }
        return true;
    }

    private static Object checkOp(Context context, int code, int uid) {
      
        try {
        	  AppOpsManager appOpsManager =
        	            (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        	        Class appOpsManagerClass = appOpsManager.getClass();
            Class[] types = new Class[3];
            types[0] = Integer.TYPE;
            types[1] = Integer.TYPE;
            types[2] = String.class;
            Method checkOpMethod =
                appOpsManagerClass.getMethod("checkOp", types);

            Object[] args = new Object[3];
            args[0] = Integer.valueOf(code);
            args[1] = Integer.valueOf(uid);
            args[2] = context.getPackageName();
            Object result = checkOpMethod.invoke(appOpsManager, args);

            return result;
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean setMode(Context context, int code,
                                   int uid, int mode) {
  
        try {
            AppOpsManager appOpsManager = 
                    (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            Class appOpsManagerClass = appOpsManager.getClass();
            Class[] types = new Class[4];
            types[0] = Integer.TYPE;
            types[1] = Integer.TYPE;
            types[2] = String.class;
            types[3] = Integer.TYPE;
            Method setModeMethod =
                appOpsManagerClass.getMethod("setMode", types);

            Object[] args = new Object[4];
            args[0] = Integer.valueOf(code);
            args[1] = Integer.valueOf(uid);
            args[2] = context.getPackageName();
            args[3] = Integer.valueOf(mode);
            Object result = setModeMethod.invoke(appOpsManager, args);
            if(result == null || result != null)
            	return true;
        }
        catch (Exception e) {
        }
        return false;
    }

    private static int getUid(Context context) {
        try {
            int uid = context.getPackageManager()
                .getApplicationInfo(context.getPackageName(),
                                    PackageManager.GET_ACTIVITIES).uid;

            return uid;
        }
        catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }
}