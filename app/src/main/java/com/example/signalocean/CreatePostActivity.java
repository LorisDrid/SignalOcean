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
    private EditText editText;
    private Button btnCreatePost;
    private String type;
    private Optional<Drawable> image;

    private AbstractPostFactory abstractPostFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        Intent intent = getIntent();
        if (intent.hasExtra("message")) {
            type = intent.getStringExtra("message");
        }

        editTitle = findViewById(R.id.editTitle);
        editText = findViewById(R.id.editText);
        btnCreatePost = findViewById(R.id.btnCreatePost);

        abstractPostFactory = AbstractPostFactory.getFactory(type);

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String text = editText.getText().toString();
                Optional<Drawable> image = Optional.empty();

                Post post = abstractPostFactory.createPost(type ,title, text, image);
                MainActivity.getCurrentUser().getPosts().add(post);

            }
        });
    }
}
