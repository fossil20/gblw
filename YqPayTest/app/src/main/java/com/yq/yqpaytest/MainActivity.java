package com.yq.yqpaytest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.utopay.gblwsdk.pay.UniCallback;
import cn.utopay.gblwsdk.pay.Unipay;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnInit = (Button) findViewById(R.id.btnInit);
        btnInit.setOnClickListener(this);
        Button btnPay = (Button) findViewById(R.id.btnPay);
        btnPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPay) {
            Unipay.getInstance().pay(MainActivity.this, new UniCallback() {
                @Override
                public void paySuccess() {

                }

                @Override
                public void payFailed(Exception e) {
                   System.out.print(e.toString());
                }
            });
        } else if (view.getId() == R.id.btnInit) {
            Unipay.getInstance().init(MainActivity.this);
        }
    }
}
