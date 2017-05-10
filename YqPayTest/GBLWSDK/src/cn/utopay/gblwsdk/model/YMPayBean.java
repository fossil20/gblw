package cn.utopay.gblwsdk.model;

import java.util.Map;

public class YMPayBean {
    private String cpparam;
    private int feeId;

    public YMPayBean(Map<String, Object> maps) {
        this.cpparam = (String) maps.get("cpparam");
        this.feeId = Integer.parseInt((String) maps.get("feeId"));
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public String getCpparam() {
        return cpparam;
    }

    public void setCpparam(String cpparam) {
        this.cpparam = cpparam;
    }
}
