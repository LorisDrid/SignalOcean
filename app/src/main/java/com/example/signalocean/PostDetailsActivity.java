package com.example.signalocean;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details_activity);
        AbstractPost post = getIntent().getParcelableExtra("post");

        updateBackground(post);
        setPostText(post);
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

    private void setPostText(AbstractPost post){
        String postDetails = post.getPostDetails();
        String[] parts = postDetails.split("&"); // Diviser la chaîne en utilisant le caractère "&"
        String title = parts[0].trim(); // Récupérer la partie du titre (première partie)
        String text = parts[1].trim(); // Récupérer la partie du texte (deuxième partie)

        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView detailsTextView = findViewById(R.id.detailsTextView);
        titleTextView.setText(title);
        detailsTextView.setText(text);
    }
}
