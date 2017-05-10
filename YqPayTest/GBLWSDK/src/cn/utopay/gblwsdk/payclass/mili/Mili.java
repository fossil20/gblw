package cn.utopay.gblwsdk.payclass.mili;
//
//import java.util.Map;
//
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.util.Log;
//import cn.utopay.gblwsdk.httpserver.ReportPaidThread;
//import cn.utopay.gblwsdk.pay.UniCallback;
//import cn.utopay.gblwsdk.utils.LogUtil;
//import cn.utopay.gblwsdk.utils.SIMUtil;
//
//import com.dm.ml.MiLiNewApi;
//import com.yl.ml.listen.PCallback;
//
public class Mili {
//	public static final String TAG = "米粒";
//	public static final String id = "10";
//	public static void init(final Activity act) {
//		act.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				MiLiNewApi.init(act);
//			}
//		});
//	}
//	public static void pay(final Activity act, Map<String, Object> root, JSONObject pay, final String m, final UniCallback cb) throws Exception {
//		final String payId =  (String) pay.get("payId");
//		final String mm = pay.getString("money");
//		act.runOnUiThread(new Runnable() {
//			@Override
//			public void run() {
//				MiLiNewApi.Pay(act,new PCallback() {
//					@Override
//					public void payEnd(int payResult) {
//						LogUtil.v(TAG +":" + payResult);
//						if (payResult == 9000) {
//							ReportPaidThread.reportSuccess(act,TAG,mm,cb);
//						}
//					}
//				}, payId, SIMUtil.getIMSI2(act) + "" + System.currentTimeMillis(), 2 );
//				
//			}
//		});
//	}
}
