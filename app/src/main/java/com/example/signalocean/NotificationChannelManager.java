package com.example.signalocean;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.signalocean.R;

public class NotificationChannelManager {
    public static final String CHANNEL_ID_FRIEND_REQUEST = "friend_request_channel";
    public static final String CHANNEL_ID_NEW_POST = "new_post_channel";

    public static void createNotificationChannels(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Création du canal pour les demandes d'amis
            NotificationChannel friendRequestChannel = new NotificationChannel(
                    CHANNEL_ID_FRIEND_REQUEST,
                    "Friend Request",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            friendRequestChannel.setDescription("Channel for friend request notifications");

            // Création du canal pour les nouveaux posts
            NotificationChannel newPostChannel = new NotificationChannel(
                    CHANNEL_ID_NEW_POST,
                    "New Post",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            newPostChannel.setDescription("Channel for new post notifications");

            // Obtention du gestionnaire de notifications
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                // Enregistrement des canaux de notifications
                notificationManager.createNotificationChannel(friendRequestChannel);
                notificationManager.createNotificationChannel(newPostChannel);
            }
        }
    }

    public static String getFriendRequestChannelId() {
        return CHANNEL_ID_FRIEND_REQUEST;
    }

    public static String getNewPostChannelId() {
        return CHANNEL_ID_NEW_POST;
    }
}
