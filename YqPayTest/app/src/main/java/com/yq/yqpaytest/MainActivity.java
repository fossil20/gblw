package com.yq.yqpaytest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.btnInit)
    private Button btnInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        btnInit.setOnClickListener(this);
        Button btnPay = (Button) findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
        //EventBus.TAG;
        
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPay) {
            Unipay.getInstance().pay(this,"20",new UniCallback() {
                @Override
                public void paySuccess() {
                }

                @Override
                public void payFailed(Exception e) {
                    System.out.println("支付失败：" + e.getMessage());
                }
            });
        } else if (view.getId() == R.id.btnInit) {
            //LogManager.writeInlog(this,"sddsd");
            Unipay.getInstance().init(this);
        }
    }
}
