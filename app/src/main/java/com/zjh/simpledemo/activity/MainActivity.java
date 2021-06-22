package com.zjh.simpledemo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import com.zjh.simplebase.base.BaseActivity;
import com.zjh.simplebase.base.BaseViewModel;
import com.zjh.simpledemo.R;
import com.zjh.simpledemo.databinding.ActivityMainBinding;

/**
 * main
 *
 * @author zhujianhua
 * on 2021/1/6
 */
public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return 0;
    }

    @SuppressLint("BatteryLife")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initData() {
        binding.img.setOnClickListener(view -> startActivity(new Intent(this, ImageActivity.class)));
        binding.recycler.setOnClickListener(view -> startActivity(new Intent(this, RecyclerActivity.class)));
        binding.test.setOnClickListener(view -> {
////            Intent intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//            Intent intent = new Intent(Settings.);
//            intent.setData(Uri.parse("package:" + this.getPackageName()));
//            startActivity(intent);ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS

            Intent intent = new Intent();
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
            if (pm.isIgnoringBatteryOptimizations(packageName)) {
                //已在白名单
                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            } else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
            }
            startActivity(intent);

        });

//        String op = "10017";
//        AppOpsManager manager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
//        int check = manager.unsafeCheckOpNoThrow(op, android.os.Process.myUid(), getPackageName());
//        LogUtils.e("check : " + check);
    }

    @Override
    public void initObserve() {

    }
}