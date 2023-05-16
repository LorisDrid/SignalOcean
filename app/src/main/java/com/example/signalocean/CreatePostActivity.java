package com.example.signalocean;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Optional;

public class CreatePostActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editCategory;
    private EditText editText;
    private Button btnCreatePost;
    private String type;
    private Optional<Drawable> image;

    private PostFactory postFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            type = intent.getStringExtra("message");
        }

        // Initialize views
        editTitle = findViewById(R.id.editTitle);
        editCategory = findViewById(R.id.editCategory);
        editText = findViewById(R.id.editText);
        btnCreatePost = findViewById(R.id.btnCreatePost);

        // Initialize PostFactory
        postFactory = new PostFactory();

        // Set click listener for the create post button
        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the values entered by the user
                String title = editTitle.getText().toString();
                String category = editCategory.getText().toString();
                String text = editText.getText().toString();
                Optional<Drawable> image = Optional.empty();

                // Create the post using PostFactory
                Post post = postFactory.createPost(type ,title, category, text, image);

                // Do something with the created post
                // ...
            }
        });
    }
}

