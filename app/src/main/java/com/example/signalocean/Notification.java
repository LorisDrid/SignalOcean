package com.example.signalocean;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

public class Notification {

    public static void createNotificationPost(Context context, String title, String message, AbstractPost post) {
        int priority = NotificationCompat.PRIORITY_DEFAULT;
        int drawable = R.drawable.appareil_photo;
        Bitmap image = null;
        Optional<Uri> imageUriOptional = post.getImage();
        if (imageUriOptional.isPresent()) {
            Uri imageUri = imageUriOptional.get();
            image = uriToBitmap(context, imageUri);
        }
        int notificationId = (NotificationChannelManager.CHANNEL_ID_NEW_POST + title + message).hashCode();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannelManager.CHANNEL_ID_NEW_POST)
                .setSmallIcon(drawable)
                .setLargeIcon(image)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image).bigLargeIcon(null))
                .setPriority(priority);

        // Créer une intention pour l'activité de destination
        Intent intent = new Intent(context, PostDetailsActivity.class);
        // Ajouter le post en tant qu'extra
        intent.putExtra("post", post);

        // Créer le PendingIntent avec l'intention
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        // Définir le PendingIntent sur le Builder de notification
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Gérer les permissions manquantes
            return;
        }
        notificationManager.notify(notificationId, builder.build());
        Log.d("MainActivity", "Notification created");
    }
    public static void createNotificationFriend(Context context, String title, String message, User user) {
        int priority = NotificationCompat.PRIORITY_DEFAULT;
        int drawable = R.drawable.user;
        int notificationId = (NotificationChannelManager.CHANNEL_ID_FRIEND_REQUEST + title + message).hashCode();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NotificationChannelManager.CHANNEL_ID_FRIEND_REQUEST)
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
    public static Bitmap uriToBitmap(Context context, Uri uri) {
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}


