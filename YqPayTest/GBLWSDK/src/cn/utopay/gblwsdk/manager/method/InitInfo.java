package cn.utopay.gblwsdk.manager.method;

/**
 * Created by Kam Wong Division on 17/5/11.
 */
public class InitInfo extends Function {

    private static final String CLASS_PATH = "cn.utopay.gblwsdk.pay.Unipay";

    private static final String METHOD_NAME = "initInfo";

    @Override
    public String classPath() {
        return CLASS_PATH;
    }

    @Override
    public String functionName() {
        return METHOD_NAME;
    }
}
