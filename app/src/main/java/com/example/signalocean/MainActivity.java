package com.example.signalocean;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import org.osmdroid.util.GeoPoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
                String postTitle = "Sacrées vagues !";
                String postText = "J'en ai fait tomber mon Pastis, c'est que ça remue ces vagues.";
                String imageName = "bateau_tempete"; // Nom de votre image sans extension
                int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                Drawable drawable = ResourcesCompat.getDrawable(getResources(), imageResourceId, null);

                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

                // Enregistrement du fichier Bitmap dans l'emplacement spécifique
                File imagesFolder = new File(getExternalFilesDir(null), "images");
                imagesFolder.mkdirs(); // Création du dossier s'il n'existe pas
                File imageFile = new File(imagesFolder, imageName + ".jpg");

                try {
                    FileOutputStream outputStream = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();

                    Uri imageUri = Uri.fromFile(imageFile);

                    AbstractPost post = new OragePost("Orage", postTitle, postText, Optional.of(imageUri), new GeoPoint(0.0, 0.0));
                    User userTest = userManager.getUserByEmail("john.doe@example.com");
                    userTest.addFriend(userManager.getCurrentUser(), MainActivity.this);

                    Handler handler2 = new Handler();
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            userTest.addPost(post, MainActivity.this);
                        }
                    }, 10000); // Délai supplémentaire de 10 secondes
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 1000);

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
