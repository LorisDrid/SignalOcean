package com.example.signalocean;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details_activity);
        AbstractPost post = getIntent().getParcelableExtra("post");
        updateBackground(post);
        setPostInfos(post);
        boolean isImageEmpty = post.getImage().equals(Optional.empty());
        String toastMessage = "Is Image Empty: " + String.valueOf(isImageEmpty);
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirection vers une autre activité
                Intent intent = new Intent(PostDetailsActivity.this, Maps.class);
                startActivity(intent);
            }
        });

    }

    private void updateBackground(AbstractPost post) {
        String type = post.getType();
        LinearLayout detailsLayout = findViewById(R.id.post_container);
        if (type.equals("Soleil")) {
            detailsLayout.setBackgroundResource(R.drawable.soleil_background);
        } else if (type.equals("Nuage")) {
            detailsLayout.setBackgroundResource(R.drawable.nuage_background);
        } else if (type.equals("Pluie")) {
            detailsLayout.setBackgroundResource(R.drawable.pluie_background);
        } else if (type.equals("Orage")) {
            detailsLayout.setBackgroundResource(R.drawable.orage_background);
        } else if (type.equals("Vent")) {
            detailsLayout.setBackgroundResource(R.drawable.vent_background);
        } else if (type.equals("Vague")) {
            detailsLayout.setBackgroundResource(R.drawable.vague_background);
        }
    }

    private void setPostInfos(AbstractPost post){
        String postTitle = post.getTitle();
        String postText = post.getText();
        Optional<Uri> image = post.getImage();
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView detailsTextView = findViewById(R.id.detailsTextView);
        titleTextView.setText(postTitle);
        detailsTextView.setText(postText);
        ImageView postImageView = findViewById(R.id.postImageView);
        if (image.isPresent()) {
            postImageView.setVisibility(View.VISIBLE); // Rendre l'ImageView visible
            postImageView.setImageURI(image.get());
        } else {
            postImageView.setVisibility(View.GONE); // Rendre l'ImageView invisible (disparaître)
        }
    }
}
