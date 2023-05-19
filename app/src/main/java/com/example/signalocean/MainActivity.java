package com.example.signalocean;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static User currentUser;
    public static User friendUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = new User("Bob", "Lennon", "pyrobarbare@gmail.com","fanta123");
        friendUser = new User("Homer", "Simpson", "homersimpson@outlook.fr", "donut3000");
        setContentView(R.layout.accueil);
        createNotificationChannel();
        Handler handler = new Handler();
        handler.postDelayed(() -> NotificationPost.createNotification(MainActivity.this, "notifiction test", "la notification a bien été envoyée"), 5000);
        Button connecter = (Button) findViewById(R.id.se_connecter);
        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Connexion.class);
                startActivity(intent);
            }
        });

        Button naviguer = (Button) findViewById(R.id.naviguer);
        naviguer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Mon canal de notification";
            String description = "Description de mon canal de notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NotificationPost.CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}