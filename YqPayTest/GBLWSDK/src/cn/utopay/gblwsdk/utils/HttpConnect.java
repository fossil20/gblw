package cn.utopay.gblwsdk.utils;

import android.text.TextUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;


public class HttpConnect {

	public static final int TIMEOUT_VALUE = 10000;
	public static final int MAX_REHTTPTIME = 3;
	private static final String className = "org.apache.http.impl.client.DefaultHttpClient";
	private static final String methodName = "execute";

	public static DefaultHttpClient getHttpClient() {
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_VALUE);
		HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_VALUE);
		return new DefaultHttpClient(httpParameters);
	}

	public static String doHttpPost(String httpUrl, ArrayList<NameValuePair> params, int time, boolean gzip) {
		String str = null;
		try {
			DefaultHttpClient defaultHttpClient = getHttpClient();
			HttpPost request = new HttpPost(httpUrl);
			request.setHeader("Charset", "UTF-8");
			request.setHeader("Content-Type", "application/x-www-form-urlencoded");
			if (params != null) {
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}
			HttpResponse response = defaultHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 如果数据进行过压缩，先进行解压
				str = EntityUtils.toString(response.getEntity());
				if(gzip) {
					str = ZipUtil.ungzip(str);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (TextUtils.isEmpty(str)) {
			time++;
			if (time < MAX_REHTTPTIME) {
				return doHttpPost(httpUrl, params, time,gzip);
			}
		}
		return str;
	}
}