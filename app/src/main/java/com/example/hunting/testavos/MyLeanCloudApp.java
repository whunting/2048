package com.example.hunting.testavos;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"oTIojPFMwJ9CDbmmqevpFhdx-gzGzoHsz","tlU0S179pPjikvaHbrqNaJUN");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
