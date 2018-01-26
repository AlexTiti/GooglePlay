package com.example.administrator.googleplay.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/17
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class NoitfyUtils {

    private Context context;
    private String tittle;
    private String content;
    private int image;
    private int number;

    public NoitfyUtils(Context context, String tittle, String content, int image) {
        this.context = context;
        this.tittle = tittle;
        this.content = content;
        this.image = image;
    }

    private static final String CHANNEL_ID = "channel_o";
    private static final String CHANNEL_NAME = "channel_name";

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void creatChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
        NotificationManager manager = getManager();
        manager.createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private NotificationCompat.Builder getBuilderbefore_L(PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(context, "1")
                .setContentText(content)
                .setContentTitle(tittle)
                .setSmallIcon(image)
                .setNumber(number++)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setContentIntent(pendingIntent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification.Builder getBuilderAfter_O(PendingIntent pendingIntent) {
        return new Notification.Builder(context, CHANNEL_ID)
                .setContentText(content)
                .setContentTitle(tittle)
                .setSmallIcon(image)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setContentIntent(pendingIntent);
    }

    public void notifySimple(Class<?> c) {
        NotificationManager manager = getManager();
        PendingIntent pendingIntent = getPendingIntent(c);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creatChannel();
            manager.notify(1, getBuilderAfter_O(pendingIntent).build());
        } else {
            manager.notify(1, getBuilderbefore_L(pendingIntent).build());
        }
    }

    public PendingIntent getPendingIntent(Class<?> c) {
        Intent intent = new Intent(context, c);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(c);
        taskStackBuilder.addNextIntent(intent);
        return taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void notifyExpand(Class<?> c, String string[], String bigContentTitle) {
        NotificationManager manager = getManager();
        PendingIntent pendingIntent = getPendingIntent(c);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creatChannel();
            Notification.Builder builder = getBuilderAfter_O(pendingIntent);
            builder.setStyle(getStyleAfter_O(string, bigContentTitle));
            manager.notify(1, builder.build());
        } else {
            NotificationCompat.Builder builder = getBuilderbefore_L(pendingIntent);
            builder.setStyle(getStyleBefore_L(string, bigContentTitle));
            manager.notify(1, builder.build());
        }
    }

    private NotificationCompat.InboxStyle getStyleBefore_L(String string[], String bigContentTitle) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(bigContentTitle);
        for (String s : string) {
            inboxStyle.addLine(s);
        }
        return inboxStyle;
    }

    private Notification.InboxStyle getStyleAfter_O(String string[], String bigContentTitle) {
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle();
        inboxStyle.setBigContentTitle(bigContentTitle);
        for (String s : string) {
            inboxStyle.addLine(s);
        }
        return inboxStyle;
    }

}
