package com.zzptc.zheng.notificationdemo;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends Activity {
   private Random rand =  new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.notice_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //API level 11Context context;1、准备通知对象
                Notification.Builder builder = new Notification.Builder(MainActivity.this);
                //2、准备内容实体，参数1当前环境，参数2标题，参数3内容，参数4\PendimgIntent
                //2.1准备PendimgIntent,参数1：环境，参数2请求编码，主要是用在当通知关联了多个程序的情况下，现在给1
                //参数3你要执行的动作，参数4一个标记给0
                PendingIntent pt = PendingIntent.getActivity(MainActivity.this, 0,
                        new Intent(MainActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0);
                builder.setContentIntent(pt);
                builder.setContentTitle("内容标题");
                builder.setContentText("你有一条消息！");
                builder.setSmallIcon(R.drawable.ic_launcher);
                Notification notice = builder.getNotification();
                // 发出通知
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //id:是通知的标记，如果发出通知ID相同就覆盖
                //manager.notify(100, notice);
               manager.notify(rand.nextInt(), notice);

                System.out.println("发送通知成功！");
            }
        });
    }
}
