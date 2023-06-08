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
import android.widget.ImageView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;

import java.util.Optional;

public class MainActivity extends Activity {

    public static UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userManager = new UserManager();
        setContentView(R.layout.accueil);
        NotificationChannelManager.createNotificationChannels(MainActivity.this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String postTitle = "Nouveau post";
                String postText = "Contenu du nouveau post";
                AbstractPost post = new SoleilPost("Soleil", postTitle, postText, Optional.empty(), new GeoPoint(0.0, 0.0));
                User userTest = userManager.getUserByEmail("john.doe@example.com");
                userTest.addFriend(userManager.getCurrentUser(), MainActivity.this);

                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        userTest.addPost(post, MainActivity.this);
                    }
                }, 10000); // Délai supplémentaire de 2 secondes
            }
        }, 1000);

        Toast.makeText(MainActivity.this, "notif ", Toast.LENGTH_SHORT).show();
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
}