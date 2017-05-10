package cn.utopay.gblwsdk.utils;

import android.annotation.SuppressLint;
import android.os.Environment;

/**
 * Created by Kam Wong Division on 17/5/8.
 */

public class SdcardUtil {

    @SuppressLint("SdCardPath")
    public static String getSdcardPath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        }
        return "/sdcard/";
    }

    private static boolean hasSDCard() {
        boolean mHasSDcard;
        if (Environment.MEDIA_MOUNTED.endsWith(Environment.getExternalStorageState())) {
            mHasSDcard = true;
        } else {
            mHasSDcard = false;
        }
        return mHasSDcard;
    }
}
