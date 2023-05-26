package com.example.signalocean;

import android.os.Bundle;
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

        TextView detailsTextView = findViewById(R.id.detailsTextView);
        detailsTextView.setText(post.getPostDetails());
        Toast.makeText(PostDetailsActivity.this, "detail = " + post.getPostDetails() + String.valueOf(post instanceof SoleilPost), Toast.LENGTH_SHORT).show();

    }
}







