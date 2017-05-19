package cn.utopay.gblwsdk.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.utopay.gblwsdk.preference.MyPreference;

public class JsonHelp {

    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

	/** 
     * Json 转成 Map<> 
     * @param jsonStr 
     * @return 
     */
    public static Map<String, Object> getMapForJsonObj(String jsonStr) {
        if(TextUtils.isEmpty(jsonStr)){
            return null;
        }
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return null;
    }


//    public static Map<String, Object> getMapForJsoArray(String jsonStr) {
//        if(TextUtils.isEmpty(jsonStr)){
//            return null;
//        }
//        JSONArray jsonArray;
//        try {
//            jsonArray = new JSONArray(jsonStr);
//            for(int i = 0;i<jsonArray.length();i++){
//                JSONObject jsonObject = jsonArray.getJSONObject(i);
//            }
//            jsonArray.getJSONObject();
//            Iterator<String> keyIter = jsonObject.keys();
//            String key;
//            Object value;
//            Map<String, Object> valueMap = new HashMap<String, Object>();
//            while (keyIter.hasNext()) {
//                key = keyIter.next();
//                value = jsonObject.get(key);
//                valueMap.put(key, value);
//            }
//            return valueMap;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /** 
     * Json 转成 List<Map<>> 
     * @param jsonArray
     * @return 
     */
    public static List<Map<String, Object>> jsonToMapList(JSONArray jsonArray) {
        List<Map<String, Object>> list = null;
        try {
            JSONObject jsonObj;
            JSONObject initJsonObj;
            JSONObject payJsonObj;
            list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = (JSONObject) jsonArray.opt(i);
                initJsonObj = jsonObj.optJSONObject("init");
                payJsonObj = jsonObj.optJSONObject("pay");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("sdkName", jsonObj.get("sdkName"));
                map.put("init", getMapForJsonObj(initJsonObj.toString()));
                map.put("pay", getMapForJsonObj(payJsonObj.toString()));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Map<String, Object>> getMapList(Context context) {
        String json = MyPreference.getInstance(context).readJson();
        if (TextUtils.isEmpty(json)) {
            json = JsonHelp.getJson(context, "gblw.json");
            MyPreference.getInstance(context).saveJson(json);
        }
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(json);
            return jsonToMapList(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmpty(JSONObject jsonObject){
        return jsonObject == null || jsonObject.length() == 0;
    }

    public static boolean isEmpty(JSONArray jsonArray){
        return jsonArray == null || jsonArray.length() == 0;
    }
}
