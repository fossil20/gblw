package cn.utopay.gblwsdk.model;

import android.graphics.drawable.Drawable;

public class LocalAppInfo {
	private Drawable iconDrawable;
	private String appName;
	private String appPackageName;
	private String appFilePath;
	
	public Drawable getIconDrawable() {
		return iconDrawable;
	}
	public void setIconDrawable(Drawable iconDrawable) {
		this.iconDrawable = iconDrawable;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	public LocalAppInfo(Drawable iconDrawable, String appName,
			String appPackageName, String appFilePath) {
		super();
		this.iconDrawable = iconDrawable;
		this.appName = appName;
		this.appPackageName = appPackageName;
		this.appFilePath = appFilePath;
	}
	public String getAppFilePath() {
		return appFilePath;
	}
	public void setAppFilePath(String appFilePath) {
		this.appFilePath = appFilePath;
	}
	public LocalAppInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
