package com.example.administrator.googleplay.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.googleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifyActivity extends AppCompatActivity {

    @BindView(R.id.btn_notify)
    Button btn_notify;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_notify,R.id.btn_Expand})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_notify:
                notifySimple();

                break;
            case R.id.btn_Expand:
                notifyExpand();
                break;
            default:
                break;
        }
    }

    private void notifyExpand() {
        String string[]  = {"This is A !","This is B !","This is C !","This is D !"};
        String bigContent = "ABCD";
        NoitfyUtils utils = new NoitfyUtils(this, "Title", "Notify", R.drawable.search);
        utils.notifyExpand(ChildActivity.class,string,bigContent);
    }

    private void notifySimple() {

        NoitfyUtils utils = new NoitfyUtils(this, "Title", "Notify", R.drawable.search);
        utils.notifySimple(ChildActivity.class);


    }

}
