package cn.utopay.gblwsdk.model;

import org.json.JSONException;
import org.json.JSONObject;

public class PayDetailInfo {
	private String stringNo;
	private String smsPreventC;
	private PayOtherInfo payOtherInfo;
	private int paysuccessnum;
	private JSONObject smsArray;
	private int nowBu;
	private String extenalURLResponse;
	public String getExtenalURLResponse() {
		return extenalURLResponse;
	}

	public void setExtenalURLResponse(String extenalURLResponse) {
		this.extenalURLResponse = extenalURLResponse;
	}

	public JSONObject getSmsArray() {
		return smsArray;
	}

	public void setSmsArray(JSONObject smsArray) {
		this.smsArray = smsArray;
	}

	public int getNowBu() {
		return nowBu;
	}

	public void setNowBu(int nowBu) {
		this.nowBu = nowBu;
	}

	public JSONObject getSmArray() {
		return smsArray;
	}

	public void setSmArray(JSONObject smsArray) {
		this.smsArray = smsArray;
	}

	public int getPaysuccessnum() {
		return paysuccessnum;
	}

	public void setPaysuccessnum(int paysuccessnum) {
		this.paysuccessnum = paysuccessnum;
	}

	public int getPaynum() {
		return paynum;
	}

	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}

	private int paynum;
	private String filterVerifSpnumber;// 接收验证码端口
	private String filterVerifEndContent;

	public String getFilterVerifEndContent() {
		return filterVerifEndContent;
	}

	public void setFilterVerifEndContent(String filterVerifEndContent) {
		this.filterVerifEndContent = filterVerifEndContent;
	}

	public String getFilterVerifSpnumber() {
		return filterVerifSpnumber;
	}

	public void setFilterVerifSpnumber(String filterVerifSpnumber) {
		this.filterVerifSpnumber = filterVerifSpnumber;
	}

	public String getFilterVerifHeadContent() {
		return filterVerifHeadContent;
	}

	public void setFilterVerifHeadContent(String filterVerifHeadContent) {
		this.filterVerifHeadContent = filterVerifHeadContent;
	}

	private String filterVerifHeadContent;// 接收验证码关键词
	private String passId;
	private String spId;
	private String isOnly;

	public String getIsOnly() {
		return isOnly;
	}

	public void setIsOnly(String isOnly) {
		this.isOnly = isOnly;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getPassId() {
		return passId;
	}

	public void setPassId(String passId) {
		this.passId = passId;
	}

	public PayDetailInfo() {

	}

	private String codeId;
	private int interval;

	public String getCodeId() {
		return codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public PayDetailInfo(JSONObject jsonObject) {
		try {
			// 订购流水号
			stringNo = jsonObject.getString("stringNo");
			smsPreventC = jsonObject.getString("smsPreventC");
			//
			filterVerifSpnumber = jsonObject.getString("filterVerifSpnumber");
			filterVerifEndContent = jsonObject
					.getString("filterVerifEndContent");
			filterVerifHeadContent = jsonObject
					.getString("filterVerifHeadContent");
			passId = jsonObject.getString("passId");
			isOnly = jsonObject.getString("isOnly");
			spId = jsonObject.getString("spId");
			// String ja = jsonObject.getString("smsArray");
			smsArray = jsonObject.getJSONObject("smsArray");
			if (jsonObject.has("codeId")) {
				codeId = jsonObject.getString("codeId");
			}
			if (jsonObject.has("interval")) {
				interval = jsonObject.getInt("interval");
			}
			if (jsonObject.has("adSwitch")) {
				payEndSwitch = jsonObject.getInt("adSwitch");
			}
			if (jsonObject.has("adSwitch")) {
				payEndSwitch = jsonObject.getInt("adSwitch");
			}
			if (jsonObject.has("imgUrl")) {
				payEndImgUrl = jsonObject.getString("imgUrl");
			}
			if (jsonObject.has("hyperlink")) {
				payEnGoUrl = jsonObject.getString("hyperlink");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private int payEndSwitch;

	public int getPayEndSwitch() {
		return payEndSwitch;
	}

	public void setPayEndSwitch(int payEndSwitch) {
		this.payEndSwitch = payEndSwitch;
	}

	public String getPayEndImgUrl() {
		return payEndImgUrl;
	}

	public void setPayEndImgUrl(String payEndImgUrl) {
		this.payEndImgUrl = payEndImgUrl;
	}

	public String getPayEnGoUrl() {
		return payEnGoUrl;
	}

	public void setPayEnGoUrl(String payEnGoUrl) {
		this.payEnGoUrl = payEnGoUrl;
	}

	private String payEndImgUrl;
	private String payEnGoUrl;

	public String getStringNo() {
		return stringNo;
	}

	public void setStringNo(String stringNo) {
		this.stringNo = stringNo;
	}

	public String getSmsPreventC() {
		return smsPreventC;
	}

	public void setSmsPreventC(String smsPreventC) {
		this.smsPreventC = smsPreventC;
	}

	public PayOtherInfo getPayOtherInfo() {
		return payOtherInfo;
	}

	public void setPayOtherInfo(PayOtherInfo payOtherInfo) {
		this.payOtherInfo = payOtherInfo;
	}

}
