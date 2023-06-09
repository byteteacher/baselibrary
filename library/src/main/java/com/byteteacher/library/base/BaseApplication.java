package com.byteteacher.library.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class BaseApplication extends Application {

    private static final String TAG = "BaseApplication";
    private static BaseApplication instance;
    private Activity mTopActivity;
    private int mActivityCount = 0;
    private static Handler mainHandler;//全局的主线程的handler


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(callbacks);

        mainHandler = new Handler();//初始化Handler
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {


        }

        @Override
        public void onActivityResumed(Activity activity) {
            mTopActivity = activity;
            mActivityCount++;
            Log.e(TAG, "topActivity == " + activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            mTopActivity = null;
            mActivityCount--;

        }

        @Override
        public void onActivityStopped(Activity activity) {


        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };


    /**
     * 获取当前栈顶activity
     */
    public Activity getTopActivity() {
        return mTopActivity;
    }

    /**
     * 判断应用是否在前台
     */
    public boolean isAppForeground() {
        return (mActivityCount & 1) == 1;
    }

    /**
     * 获取当前进程名字
     * 默认是包名
     * 开了子进程的就是包名:processname
     *
     * @return 进程名字
     */
    public String getProcesssName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfoList = am.getRunningAppProcesses();
        if (processInfoList == null) {
            return getPackageName();
        }
        for (ActivityManager.RunningAppProcessInfo info : processInfoList) {
            if (info.pid == pid) {
                processName = info.processName;
                break;
            }
        }
        return processName;
    }

    /**
     * 获取全局的主线程的handler对象
     *
     * @return
     */
    public static Handler getMainHandler() {
        return mainHandler;
    }

    public boolean isRemoteService() {
        String processName = getProcesssName();
        if (!processName.equals(getPackageName())) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(callbacks);
    }


}
