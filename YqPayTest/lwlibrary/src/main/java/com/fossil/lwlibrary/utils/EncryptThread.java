package com.fossil.lwlibrary.utils;

import android.content.Context;

/**
 * Created by Kam Wong Division on 17/5/12.
 */

public class EncryptThread implements Runnable {

    private Context context;

    public EncryptThread(Context context){
        this.context = context;
    }

    @Override
    public void run() {
        CommonUtil.decryptSource(context);
    }
}
