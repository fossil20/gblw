package cn.utopay.gblwsdk.httpserver;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.utopay.gblwsdk.config.ConFigFile;
import cn.utopay.gblwsdk.manager.InvokeFactory;
import cn.utopay.gblwsdk.manager.method.DoHttpPost;
import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;
import cn.utopay.gblwsdk.payclass.BasePay;
import cn.utopay.gblwsdk.utils.ThreadPool;

public class ReportPaidThread extends BaseHttpThread {

	public ReportPaidThread(Map<String, String> maps) {
		super(ConFigFile.Url_JSMain + "report/report.aspx", null, maps);
	}

	@Override
	public void run() {
		InvokeFactory.getInstance().staticExecute(new Object[]{url,getPostParams(maps),0,false},
				new Class<?>[]{String.class, ArrayList.class, int.class, boolean.class},DoHttpPost.class);
		//invokeHttp(url, getPostParams(maps), 0, false);
		//HttpConnect.doHttpPost(url, getPostParams(maps), 0, false);
	}

	public static synchronized void reportSuccess(Activity activity,String id, String money, UniCallback uniCallback) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("appId", Unipay.appId);
		map.put("sdkId",id);
		map.put("placeCode", Unipay.channel);
		map.put("provinceId", "1");
		map.put("fee", "0");
		map.put("paid", money);
		BasePay.print(activity,id + ":支付成功");
		ThreadPool.getInstance().submitTask(new ReportPaidThread(map));
		if(uniCallback != null) {
			uniCallback.paySuccess();
		}
	}
}

