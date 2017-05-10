package cn.utopay.gblwsdk.preference;

import android.content.Context;

/**
 * class name:MyPreference<BR>
 * class description:用来存取简单数据的类<BR>
 * PS： <BR>
 * 
 * @version 1.00 2012-4-11
 * @author ZHENSHI)peijiangping
 */
public class MyPreference extends BasePreference {

	private static MyPreference instance;
	private static final String JSON = "json";
	private static final String GBLW_TIME = "gblw_time";

	public MyPreference(Context context) {
		super(context);
	}

	public synchronized static MyPreference getInstance(Context context) {
		if (instance == null) {
			instance = new MyPreference(context);
		}
		return instance;
	}

	public void saveJson(String jsonValue){
		write(JSON,jsonValue);
	}

	public String readJson(){
		return readString(JSON,"");
	}

    public void saveGblwTime(long time){
		write(GBLW_TIME,time);
	}

	public long readGblwTime(){
		return readLong(GBLW_TIME,0);
	}
}
