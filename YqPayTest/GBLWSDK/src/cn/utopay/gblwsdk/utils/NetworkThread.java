package cn.utopay.gblwsdk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

public class NetworkThread implements Runnable{

	private Handler handler;
	private Context context;

	public NetworkThread(Handler handler, Context context) {
		this.handler = handler;
		this.context = context;
	}

	@Override
	public void run() {
		int index = 0;
		while (true) {
			ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (gprs.isConnected()) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			index++;
			if (index > 6) {
				break;
			}
		}
		this.handler.sendEmptyMessage(1);
	}

}
