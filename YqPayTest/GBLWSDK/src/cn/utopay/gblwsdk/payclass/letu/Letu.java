package cn.utopay.gblwsdk.payclass.letu;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.JSONObject;
//
//import android.app.Activity;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.widget.Toast;
//import cn.utopay.gblwsdk.pay.UniCallback;
//import cn.utopay.gblwsdk.utils.ThreadPool;
//
//import com.lyhtgh.pay.SdkPayServer;
//
public class Letu {
//	public static final String TAG = "云贝支付";
//	public static final String id = "1";
//	private class PayHandler extends Handler {
//		@Override
//		public void handleMessage(Message msg) {
//			super.handleMessage(msg);
//			if (msg.what == SdkPayServer.MSG_WHAT_TO_APP) {
//				String retInfo = (String) msg.obj;
//				String[] keyValues = retInfo.split("&|=");
//				Map<String, String> resultMap = new HashMap<String, String>();
//				for (int i = 0; i < keyValues.length; i = i + 2) {
//					resultMap.put(keyValues[i], keyValues[i + 1]);
//				}
//				String payResult = resultMap.get(SdkPayServer.PAYRET_KEY_RESULT_STATUS);
//				if (null != payResult && Integer.parseInt(payResult) == SdkPayServer.PAY_RESULT_SUCCESS) {
//				
//				}
//				
//			}
//		}
//	}
//	public static void init(final Activity t, String appId) {
//		SdkPayServer.getInstance ().initSdkPayServer ();
//	}
//	
//	public static void pay(final Activity t, Map<String, Object> root, JSONObject pay, final String m, final UniCallback cb) throws Exception {
//		final String payId =(String) pay.get("payId");
//		final String mm = pay.getString("money");
//		ThreadPool.getInstance().submitTask(new Runnable() {
//			@Override
//			public void run() {
//				SdkPayServer.getInstance().startSdkSmsPay(t, 
//						new PayHandler(), 				/*计费结果回调*/
//						root.get("key"), 			/*商户秘钥*/
//						payId,						/*CP订单号，不重复*/
//						channelId, 					/*CP渠道ID*/
//						payPoint, 					/*计费点ID*/																			
//						keyValue					/*预留字段，默认即可*/				
//						);
//			}
//		});
//	}
}

