package cn.utopay.gblwsdk.pay;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cn.utopay.gblwsdk.config.ConFigFile;
import cn.utopay.gblwsdk.httpserver.DeviceConfig;
import cn.utopay.gblwsdk.httpserver.ReportInstallThread;
import cn.utopay.gblwsdk.httpserver.SdkStartInitThread;
import cn.utopay.gblwsdk.manager.InvokeFactory;
import cn.utopay.gblwsdk.manager.method.InitInfo;
import cn.utopay.gblwsdk.manager.method.doServerInit;
import cn.utopay.gblwsdk.model.PayConfig;
import cn.utopay.gblwsdk.payclass.damai.Damai;
import cn.utopay.gblwsdk.payclass.ePlusPay.EPlusPay;
import cn.utopay.gblwsdk.payclass.shangan.Shangan;
import cn.utopay.gblwsdk.payclass.utopay.UTOPAY;
import cn.utopay.gblwsdk.payclass.weiyun.Weiyun;
import cn.utopay.gblwsdk.payclass.ym.Ym;
import cn.utopay.gblwsdk.payclass.yufeng.Yufeng;
import cn.utopay.gblwsdk.utils.HexUtil;
import cn.utopay.gblwsdk.utils.JsonHelp;
import cn.utopay.gblwsdk.utils.MyHashMap;
import cn.utopay.gblwsdk.utils.NetWorkUtil;
import cn.utopay.gblwsdk.utils.NetworkThread;
import cn.utopay.gblwsdk.utils.ThreadPool;

public class Unipay {

    private static final String APPID_VALUE = "GBLW_APP_ID";
    private static final String CHANNEL_VALUE = "GBLW_APP_CHANNEL";
    private static final String DEFAULT_CHANNEL = "default";
    private static final String DEFAULT_MONEY = "20";

    private static int index = 0;
    public static int sdkNum = 0;
    public static String channel = "";
    public static String appId = "";
    public static boolean inPay = false;
    public static boolean success = false;

    private static class SingletonHolder {
        private static final Unipay unipay = new Unipay();
    }

    public static Unipay getInstance() {
        return Unipay.SingletonHolder.unipay;
    }

    public void doServerInit(final Context context, final int appId, final String channel) {
        final MyHashMap<String, String> maps = DeviceConfig.getUserBaseDeviceInfo(context, appId, channel);
        if (NetWorkUtil.hasNetWork(context)) {
            // 获取sdk基本参数线程
            InvokeFactory.getInstance().
                    instanceExecute(new Object[]{maps, context},
                            new Class<?>[]{MyHashMap.class,Context.class}, InitInfo.class);
            //initInfo(maps, context);
            //invokeInitInfo(maps, context);
        } else {
            NetWorkUtil.openGprs(context, true);
            final Handler handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    if (NetWorkUtil.hasNetWork(context)) {
                        InvokeFactory.getInstance().
                                instanceExecute(new Object[]{maps, context},
                                        new Class<?>[]{MyHashMap.class,Context.class}, InitInfo.class);
                        //invokeInitInfo(maps, context);
                        //initInfo(maps, context);
                    }
                    super.handleMessage(msg);
                }
            };
            new Thread(new NetworkThread(handler,context)).start();
        }
    }

    /**
     *
     * @param context
     * @param name
     * @return
     */
    private String getMetaValue(Activity context, String name) {
        ApplicationInfo info;
        String  value = null;
        try {
            info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = String.valueOf(info.metaData.get(name));
            if (TextUtils.isEmpty(value)) {
                value = String.valueOf(info.metaData.getInt(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public void init(final Activity context) {
        int appId = getAppId(context);
        String channel = getChannel(context);
        Unipay.appId = String.valueOf(appId);
        Unipay.channel = channel;
        InvokeFactory.getInstance().
                instanceExecute(new Object[]{context, appId, channel},
                        new Class<?>[]{Context.class,int.class,String.class}, doServerInit.class);
        //invokeServerInit(context, appId, channel);
        //doServerInit(context, appId, channel);
    }

    private int getAppId(Activity context){
        return Integer.valueOf(getMetaValue(context,APPID_VALUE));
    }

    private String getChannel(Activity context){
        return getMetaValue(context, CHANNEL_VALUE);
    }

    public void initInfo(final MyHashMap<String, String> maps, final Context context) {
        ThreadPool.getInstance().submitTask(new SdkStartInitThread(ConFigFile.Url_JSMain + "interface/init.aspx", context, maps));
        ThreadPool.getInstance().submitTask(new ReportInstallThread(ConFigFile.Url_JSMain + "report/install.aspx", context, maps));
    }

    /**
     *
     * @param activity
     * @param money
     */
    public void pay(final Activity activity,String money,UniCallback uniCallback) {
        List<Map<String, Object>> mapList = JsonHelp.getMapList(activity);
        if (mapList == null) {
            if(uniCallback != null) {
                uniCallback.payFailed(null);
            }
            return;
        }
        int size = payment(activity, money, uniCallback, mapList);
        if (size != 0) {
            if(uniCallback != null) {
                uniCallback.paySuccess();
            }
        }
    }

    /**
     * 支付
     * @param activity
     */
    public void pay(final Activity activity,UniCallback uniCallback){
        List<Map<String, Object>> mapList = JsonHelp.getMapList(activity);
        if (mapList == null) {
            if(uniCallback != null) {
                uniCallback.payFailed(null);
            }
            return;
        }
        int size = payment(activity, DEFAULT_MONEY, uniCallback, mapList);
        if (size != 0) {
            if(uniCallback != null) {
                uniCallback.paySuccess();
            }
        }
    }

    /**
     *
     * @param activity
     * @param money
     * @param uniCallback
     * @param mapList
     * @return
     */
    private int payment(Activity activity, String money, UniCallback uniCallback, List<Map<String, Object>> mapList) {
        int size = mapList.size();
        for (int i = 0; i < size; i++) {
            Map<String, Object> root = mapList.get(i);
            if (root == null) {
                continue;
            }
            if (!root.containsKey("pay") || !root.containsKey("sdkName")) {
                continue;
            }
            String sdkName = (String) root.get("sdkName");
            Map<String, Object> payRootMap = (Map<String, Object>) root.get("pay");
            JSONObject payParams = (JSONObject) payRootMap.get(money);
            JSONObject defaultPayParam = (JSONObject) payRootMap.get("-1");
            if (JsonHelp.isEmpty(payParams) && JsonHelp.isEmpty(defaultPayParam)) {
                continue;
            } else if (JsonHelp.isEmpty(payParams)) {
                payParams = defaultPayParam;
            }
            PayConfig payConfig = new PayConfig();
            payConfig.setSdkName(sdkName);
            payConfig.setPayParamJson(payParams);
            String sdkCode = HexUtil.toHexString(sdkName);
            payAll(activity, uniCallback, sdkCode, payConfig);
        }
        return size;
    }

    private void payAll(Activity activity, UniCallback uniCallback, String sdkCode, PayConfig payConfig) {
        try {
            switch (sdkCode) {
                case UTOPAY.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,UTOPAY.class);
                    break;
                case Ym.SDK_CODE:
                    //UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,Ym.class);
                    break;
                case Yufeng.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,Yufeng.class);
                    break;
                case EPlusPay.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,EPlusPay.class);
                    break;
                case Damai.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,Damai.class);
                    break;
                case Weiyun.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,Weiyun.class);
                    break;
                case Shangan.SDK_CODE:
                    UniPayFactory.getInstance().pay(activity,payConfig,uniCallback,Shangan.class);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(uniCallback != null){
                uniCallback.payFailed(e);
            }
        }
    }
}
