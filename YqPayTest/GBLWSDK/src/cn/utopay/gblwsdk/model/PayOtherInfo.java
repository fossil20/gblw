package cn.utopay.gblwsdk.model;

import org.json.JSONException;
import org.json.JSONObject;


public class PayOtherInfo {
	private String money;
	private String payDetail;
	private String orderDetail;
	private String spName;
	private String channelName;
	private String payClass;
	private String payAlert;
	private String showTheme;
	private String closeTime;
	private String img1, img2, img3, img4, img5, fontColor1, fontColor2;
	private String yys;
	private String orderid;
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public PayOtherInfo() {

	}

	public String getYys() {
		return yys;
	}

	public void setYys(String yys) {
		this.yys = yys;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public String getFontColor1() {
		return fontColor1;
	}

	public void setFontColor1(String fontColor1) {
		this.fontColor1 = fontColor1;
	}

	public String getFontColor2() {
		return fontColor2;
	}

	public void setFontColor2(String fontColor2) {
		this.fontColor2 = fontColor2;
	}

	public String getShowTheme() {
		return showTheme;
	}

	public void setShowTheme(String showTheme) {
		this.showTheme = showTheme;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getPayAlert() {
		return payAlert;
	}

	public void setPayAlert(String payAlert) {
		this.payAlert = payAlert;
	}

	private int payMode = 1;

	public int getPayMode() {
		return payMode;
	}

	public void setPayMode(int paymode) {
		this.payMode = paymode;
	}

	private int payWaitTime = 1000;

	public int getPayWaitTime() {
		return payWaitTime;
	}

	public void setPayWaitTime(int payWaitTime) {
		this.payWaitTime = payWaitTime;
	}

	private String cpName;

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public PayOtherInfo(JSONObject jsonObject) {
		try {
			money = jsonObject.getString("money");
			// 计费项名称
			payDetail = jsonObject.getString("payDetail");
			orderDetail = jsonObject.getString("orderDetail");
			channelName = jsonObject.getString("channelName");
			spName = jsonObject.getString("spName");
			payClass = jsonObject.getString("payclass");
			// 计费项描述
			payAlert = jsonObject.getString("payAlert");
			showTheme = jsonObject.getString("showTheme");
			closeTime = jsonObject.getString("closeTime");
			yys = jsonObject.getString("yys");
			orderid = jsonObject.getString("orderid");
			if (jsonObject.has("payMode")) {
				payMode = jsonObject.getInt("payMode");
			}
			if (jsonObject.has("payWaitTime")) {
				payWaitTime = jsonObject.getInt("payWaitTime");
			}
			if (jsonObject.has("productName")) {
				cpName = jsonObject.getString("productName");
			}
			if (jsonObject.has("showSrc")) {
				JSONObject src = jsonObject.getJSONObject("showSrc");
				img1 = src.getString("img1");
				img2 = src.getString("img2");
				img3 = src.getString("img3");
				img4 = src.getString("img4");
				img5 = src.getString("img5");
				fontColor1 = src.getString("fontColor1");
				fontColor2 = src.getString("fontColor2");
			}
		} catch (JSONException e) {
			
		}
	}

	public String getPayClass() {
		return payClass;
	}

	public void setPayClass(String payClass) {
		this.payClass = payClass;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getPayDetail() {
		return payDetail;
	}

	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

}
