package com.example.signalocean;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    private User currentUser;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        this.currentUser = MainActivity.userManager.getCurrentUser();

        listView = findViewById(R.id.listView);
        ArrayList<AbstractPost> posts = currentUser.getPosts();
        Toast.makeText(UserInfo.this, "Nombre de posts : " + posts.size(), Toast.LENGTH_SHORT).show();

        PostAdapter<AbstractPost> adapter = new PostAdapter<>(this, posts, AbstractPost.class);
        listView.setAdapter(adapter);

        TextView textFirstName = findViewById(R.id.textFirstName);
        TextView textLastName = findViewById(R.id.textLastName);
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textPassword = findViewById(R.id.textPassword);

        textFirstName.setText(currentUser.getFirstName());
        textLastName.setText(currentUser.getLastName());
        textEmail.setText(currentUser.getEmail());
        textPassword.setText(currentUser.getPassword());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AbstractPost post = adapter.getTypedItem(position);
                Intent intent = new Intent(UserInfo.this, PostDetailsActivity.class);
                intent.putExtra("post", post);
                startActivity(intent);
            }
        });
    }
}
