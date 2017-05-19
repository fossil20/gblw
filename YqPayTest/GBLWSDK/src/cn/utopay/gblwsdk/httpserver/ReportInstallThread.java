package cn.utopay.gblwsdk.httpserver;

import android.content.Context;

import java.util.Date;
import java.util.Map;

import cn.utopay.gblwsdk.preference.MyPreference;

import static cn.utopay.gblwsdk.utils.InvokeUtil.invokeHttp;

public class ReportInstallThread extends BaseHttpThread {

    private Context context;

	public ReportInstallThread(String url, Context context, Map<String, String> maps) {
		super(url, null, maps);
		this.context = context;
	}

	@Override
	public void run() {
		long t = MyPreference.getInstance(context).readGblwTime();
		if (t > 0) {
			if (new Date(t).getDate() == new Date().getDate())
				return;
		}
		//String v = HttpConnect.doHttpPost(url, getPostParams(maps), 0, false);
		String v = invokeHttp(url, getPostParams(maps), 0, false);
		if (v != null && v.equals("ok")) {
			MyPreference.getInstance(context).saveGblwTime(System.currentTimeMillis());
		}
	}
	
}
