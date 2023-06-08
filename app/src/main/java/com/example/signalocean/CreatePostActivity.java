package com.example.signalocean;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
public class CreatePostActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int REQUEST_CAMERA_PERMISSION = 3;
    private static final int REQUEST_STORAGE_PERMISSION = 4;

    private EditText editTitle;
    private EditText editText;
    private Button btnCreatePost;
    private Button btnReturn;
    private MapView mapView;
    private Button btnInsererImage;
    private String type;

    private Uri imageUri;
    private GeoPoint location;

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
                showImageSourceSelectionDialog();
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

                AbstractPost post = abstractPostFactory.createPost(type, title, text, Optional.ofNullable(CreatePostActivity.this.imageUri), location);
                MainActivity.userManager.getCurrentUser().addPost(post, CreatePostActivity.this);


                int postCount = MainActivity.userManager.getCurrentUser().getPosts().size();
                Intent intent = new Intent(CreatePostActivity.this, Maps.class);
                startActivity(intent);
            }
        });
    }

    private void showImageSourceSelectionDialog() {
        String[] options = {"Galerie", "Caméra"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionner une image depuis :");
        builder.setItems(options, (dialog, which) -> {
            switch (which) {
                case 0:
                    // Gallery option selected
                    if (checkStoragePermission()) {
                        openGallery();
                    } else {
                        requestStoragePermission();
                    }
                    openGallery();
                    break;
                case 1:
                    // Camera option selected
                    if (checkCameraPermission()) {
                        openCamera();
                    } else {
                        requestCameraPermission();
                    }
                    break;
            }
        });
        builder.show();
    }

    private boolean checkStoragePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkCameraPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int result2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
    }


    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_IMAGE_REQUEST_CODE);
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
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
        } else if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Retrieve the captured image
            Bundle extras = data.getExtras();
            if (extras != null && extras.containsKey("data")) {
                // Get the image bitmap
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                // Convert the bitmap to URI
                this.imageUri = getImageUri(imageBitmap);

                // Display the image in the ImageView
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageURI(this.imageUri);
            }
        }
    }
    private Uri getImageUri(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        } else if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
        }
    }

}
