package com.yq.yqpaytest;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;

public class CrashHandler implements UncaughtExceptionHandler {

	private static CrashHandler instance;
	private Context mContext;// 
	private UncaughtExceptionHandler mDefaultHandler;
															// UncaughtException

	private CrashHandler() {
	}

	
	public static CrashHandler getInstance() {
		if (instance == null) {
			instance = new CrashHandler();
		}
		return instance;
	}

	public void init(Context context) {
		mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex)) {
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(2000);
				ex.printStackTrace();
				//ActivityManager.getInstance().exit();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return false;
		}
		ex.printStackTrace();
//		new Handler().post(new Runnable() {
//			@Override
//			public void run() {
//				ex.printStackTrace();
//			}
//		});
//		new Thread() {
//			@Override
//			public void run() {
//				Looper.prepare();
//				ex.printStackTrace();
//				//LogManager.writeInlog(ex.getMessage());
//				//AppBaseActivity.showToast(R.string.application_crash);
//				Looper.loop();
//			}
//		}.start();
		return true;
	}

}
