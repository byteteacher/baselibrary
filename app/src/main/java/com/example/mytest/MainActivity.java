package com.example.mytest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.byteteacher.library.base.BaseActivity;
import com.example.mytest2.R;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "cj";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e(TAG, "main onTick: "+isResume() );
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start:

                //跳转第二个activity
                startActivity(new Intent(this,SecondActivity.class));

                break;
            case R.id.bt_test_64:

                //冲突测试，再次测试。
                startActivity(new Intent(this,SecondActivity.class));

                break;
        }
    }


}
