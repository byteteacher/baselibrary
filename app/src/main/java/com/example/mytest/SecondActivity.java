package com.example.mytest;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.byteteacher.library.base.BaseActivity;
import com.example.mytest2.R;


public class SecondActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "cj";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.e(TAG, "second onTick: " + isResume());
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

                break;
        }
    }
}
