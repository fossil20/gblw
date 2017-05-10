package cn.utopay.gblwsdk.model;

public class AppInfo {
	private String appUrl;
	private String appName;
	private String iconUrl;
	private String appSize;
	private String slogan;
	private String downListButtonText;
	private String playerNum;
	private String pathType;
	private String popupType;
	
	

	public String getPathType() {
		return pathType;
	}

	public void setPathType(String pathType) {
		this.pathType = pathType;
	}

	public String getPopupType() {
		return popupType;
	}

	public void setPopupType(String popupType) {
		this.popupType = popupType;
	}

	public String getDownListButtonText() {
		return downListButtonText;
	}

	public void setDownListButtonText(String downListButtonText) {
		this.downListButtonText = downListButtonText;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(String playerNum) {
		this.playerNum = playerNum;
	}
	
	

	@Override
	public String toString() {
		return "AppInfo [appUrl=" + appUrl + ", appName=" + appName
				+ ", iconUrl=" + iconUrl + ", appSize=" + appSize + ", slogan="
				+ slogan + ", downListButtonText=" + downListButtonText
				+ ", playerNum=" + playerNum + ", pathType=" + pathType
				+ ", popupType=" + popupType + ", appId=" + appId
				+ ", packageName=" + packageName + ", imgUrl=" + imgUrl + "]";
	}



	private String appId;
	private String packageName;
	private String imgUrl;

	public AppInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
