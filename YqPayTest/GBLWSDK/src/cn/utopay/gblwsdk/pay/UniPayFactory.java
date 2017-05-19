package cn.utopay.gblwsdk.pay;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;

import cn.utopay.gblwsdk.model.PayConfig;
import cn.utopay.gblwsdk.payclass.BasePay;
import cn.utopay.gblwsdk.utils.ThreadPool;

/**
 * pay factory
 * Created by Versace on 17/4/28.
 */
public class UniPayFactory<T extends BasePay> {

    private static class InstanceHolder {
        private static final UniPayFactory uniPayFactory = new UniPayFactory();
    }

    public static UniPayFactory getInstance() {
        return InstanceHolder.uniPayFactory;
    }

    public void init(final Activity activity, final PayConfig payConfig, final Class<T> payClass) {
        new Handler(activity.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    T t = payClass.newInstance();
                    t.setPayConfig(payConfig);
                    t.init(activity);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                } finally {
                    BasePay.print(activity,payConfig.getSdkName() + "初始化结束");
                }
            }
        });
    }

    public void init(final Activity activity, final PayConfig payConfig, final UniCallback uniCallback, final Class<T> payClass) {
        new Handler(activity.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                try {
                    T t = payClass.newInstance();
                    t.setPayConfig(payConfig);
                    t.init(activity, uniCallback);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                }finally {
                    BasePay.print(activity,payConfig.getSdkName() + "初始化结束");
                }
            }
        });
    }

    public void pay(final Activity activity, final PayConfig payConfig, final UniCallback uniCallback, final Class<T> payClass) {
        ThreadPool.getInstance().submitTask(new Runnable() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            T t = payClass.newInstance();
                            t.setPayConfig(payConfig);
                            t.pay(activity, uniCallback);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                            BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                            if (uniCallback != null) {
                                uniCallback.payFailed(e);
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                            BasePay.print(activity,payConfig.getSdkName() + ":" + e.toString());
                            if (uniCallback != null) {
                                uniCallback.payFailed(e);
                            }
                        } finally {
                            BasePay.print(activity,payConfig.getSdkName() + "支付结束");
                        }
                    }
                });
            }
        });
    }

}
