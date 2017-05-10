package cn.utopay.gblwsdk.log;

import android.util.Log;

public class LogUtil {

	// tag
	private static final String TAG = "Nianhua";
	public static final boolean isDebug = true;
	private static final int VERBOSE = 1;
	private static final int DEBUG = 2;
	private static final int INFO = 3;
	private static final int WARN = 4;
	private static final int ERROR = 5;
	private static final int NOTHING = 6;
	private static final int LEVEL = VERBOSE;
	private static final String SEPARATOR = ",";

	// log.d
	public static void d(String msg) {
		if(isDebug) {
			Log.d(TAG,msg);
		}
	}

	// log.v
	public static void v(String msg) {
		if(isDebug){
			Log.v(TAG, msg);
		}
	}

	// log.e
	public static void e(String msg) {
		if(isDebug){
			Log.e(TAG, msg);
		}
	}

	// log.w
	public static void w(String msg) {
		if(isDebug){
			Log.w(TAG, msg);
		}
	}

	/**
	 * 输出日志所包含的信息
	 */
	public static String getLogInfo() {
		StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
		// 获取线程名
		String threadName = Thread.currentThread().getName();
		// 获取线程ID
		long threadID = Thread.currentThread().getId();
		// 获取文件名.即xxx.java
		String fileName = stackTraceElement.getFileName();
		// 获取类名.即包名+类名
		String className = stackTraceElement.getClassName();
		// 获取方法名称
		String methodName = stackTraceElement.getMethodName();
		// 获取生日输出行数
		int lineNumber = stackTraceElement.getLineNumber();

		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		sb.append("threadID = " + threadID).append(SEPARATOR);
		sb.append("threadName = " + threadName).append(SEPARATOR);
		sb.append("fileName = " + fileName).append(SEPARATOR);
		sb.append("className = " + className).append(SEPARATOR);
		sb.append("methodName = " + methodName).append(SEPARATOR);
		sb.append("lineNumber = " + lineNumber);
		sb.append(" ] ");
		return sb.toString();
	}
}
