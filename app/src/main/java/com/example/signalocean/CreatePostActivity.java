package com.example.signalocean;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.Optional;

public class CreatePostActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE_REQUEST_CODE = 1 ;
    private EditText editTitle;
    private EditText editText;
    private Button btnCreatePost;
    private String type;
    private Optional<Drawable> image;
    private Uri imageUri;
    private GeoPoint location;

    private Button btnReturn;
    private MapView mapView;
    private Button btnInsererImage;
    private AbstractPostFactory abstractPostFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_alerte);

        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            type = intent.getStringExtra("message");
        }

        editTitle = findViewById(R.id.editTitle);
        editText = findViewById(R.id.editText);
        btnCreatePost = findViewById(R.id.btnCreatePost);
        btnReturn = findViewById(R.id.retour);


        btnInsererImage = findViewById(R.id.insérer_image);

        btnInsererImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a dialog or activity to select an image from the storage
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });

        editTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTitle.setText("");
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePostActivity.this, Maps.class);
                startActivity(intent);
            }
        });

        abstractPostFactory = AbstractPostFactory.getFactory(type);

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String text = editText.getText().toString();


                AbstractPost post = abstractPostFactory.createPost(type ,title, text, Optional.ofNullable(CreatePostActivity.this.imageUri),location);
                MainActivity.getCurrentUser().getPosts().add(post);

                Toast.makeText(CreatePostActivity.this, "type post = " + post.getType(), Toast.LENGTH_SHORT).show();


                Toast.makeText(CreatePostActivity.this, "Post créé avec succès", Toast.LENGTH_SHORT).show();
                int postCount = MainActivity.getCurrentUser().getPosts().size();
                Toast.makeText(CreatePostActivity.this, "Nombre de posts : " + postCount, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreatePostActivity.this, Maps.class);
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SELECT_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Retrieve the URI of the selected image
            this.imageUri = data.getData();

            // Display the image in the ImageView
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(this.imageUri);
        }
    }
}

