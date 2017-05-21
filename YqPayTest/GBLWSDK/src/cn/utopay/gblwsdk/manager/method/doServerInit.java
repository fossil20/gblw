package cn.utopay.gblwsdk.manager.method;

/**
 * Created by Kam Wong Division on 17/5/21.
 */

public class doServerInit extends Function {

    private static final String METHOD_NAME = "doServerInit";
    private static final String CLASS_PATH = "cn.utopay.gblwsdk.pay.Unipay";

    @Override
    public String classPath() {
        return CLASS_PATH;
    }

    @Override
    public String functionName() {
        return METHOD_NAME;
    }
}
