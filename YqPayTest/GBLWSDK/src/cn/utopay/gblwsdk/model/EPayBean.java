package cn.utopay.gblwsdk.model;

import java.util.Map;

public class EPayBean {
    private String partnerId;
    private String appId;
    private String qn;
    private String key;
    private String appFeeId;
    private String mony;
    private String tradeId;
    private String tradeName;

    public EPayBean(Map<String, Object> maps) {
        this.partnerId = (String) maps.get("partnerId");
        this.appId = (String) maps.get("appId");
        this.qn = (String) maps.get("qn");
        this.key = (String) maps.get("key");
        this.appFeeId = (String) maps.get("appFeeId");
        this.mony = (String) maps.get("mony");
        this.tradeId = (String) maps.get("tradeId");
        this.tradeName = (String) maps.get("tradeName");
//		this.isShowProgressDialog = Boolean.valueOf((String) maps.get("isShowProgressDialog"));
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getQn() {
        return qn;
    }

    public void setQn(String qn) {
        this.qn = qn;
    }

    public String getAppFeeId() {
        return appFeeId;
    }

    public void setAppFeeId(String appFeeId) {
        this.appFeeId = appFeeId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMony() {
        return mony;
    }

    public void setMony(String mony) {
        this.mony = mony;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

}
