package com.example.signalocean;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Notification {

    public static void createNotification(Context context, String title, String message, String channelID) {
        int priority = NotificationCompat.PRIORITY_DEFAULT;
        int drawable = R.drawable.appareil_photo;
        int notificationId = (channelID + title + message).hashCode();
        if(channelID.equals(NotificationChannelManager.CHANNEL_ID_FRIEND_REQUEST)){
            priority = NotificationCompat.PRIORITY_MAX;
        }
        if(channelID.equals(NotificationChannelManager.CHANNEL_ID_NEW_POST)){
            priority = NotificationCompat.PRIORITY_HIGH;
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(drawable)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(priority);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Gérer les permissions manquantes
            return;
        }
        notificationManager.notify(notificationId, builder.build());
        Log.d("MainActivity", "Notification created");
    }
}


