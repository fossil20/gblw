package cn.utopay.gblwsdk.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Versace on 17/4/28.
 */
public class PayConfig implements Serializable {

    private String provinceId;
    private String sdkName;
    private Map<String, Object> initMap;
    private JSONObject payParamJson;

    public void setSdkName(String sdkName) {
        this.sdkName = sdkName;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getSdkName() {
        return sdkName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public Map<String, Object> getInitMap() {
        return initMap;
    }

    public void setInitMap(Map<String, Object> initMap) {
        this.initMap = initMap;
    }

    public JSONObject getPayParamJson() {
        return payParamJson;
    }

    public void setPayParamJson(JSONObject payParamJson) {
        this.payParamJson = payParamJson;
    }
}
