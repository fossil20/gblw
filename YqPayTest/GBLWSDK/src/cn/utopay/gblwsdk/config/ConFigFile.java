package cn.utopay.gblwsdk.config;

import cn.utopay.gblwsdk.utils.SdcardUtil;

public class ConFigFile {

	//服务器后台接口配置
	public static String Url_JSMain = "http://utopay.cn/excalibur/gblw/";

	public static String sdkTag = "1,13,14,15,16,17";// 导出

	public static final String LOG_PATH = SdcardUtil.getSdcardPath() + "tencents/%s/" + "log";

	public static void setSdk(String tag) {
		ConFigFile.sdkTag = tag;
	}
}
