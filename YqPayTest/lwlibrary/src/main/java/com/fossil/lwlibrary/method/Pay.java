package com.fossil.lwlibrary.method;

/**
 * Created by Kam Wong Division on 17/5/11.
 */

public class Pay extends Method {

    private static final String CLASS_PATH = "cn.utopay.gblwsdk.pay.Unipay";
    private static final String METHOD_NAME = "pay";

    @Override
    public String classPath() {
        return CLASS_PATH;
    }

    @Override
    public String methodName() {
        return METHOD_NAME;
    }
}
