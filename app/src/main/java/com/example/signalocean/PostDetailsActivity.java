package com.example.signalocean;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_details_activity);

        AbstractPost post = (AbstractPost) getIntent().getSerializableExtra("post");

        TextView detailsTextView = findViewById(R.id.detailsTextView);
        detailsTextView.setText(post.getPostDetails());
    }
}







