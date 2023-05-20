package com.example.signalocean;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    private User currentUser;
    private LinearLayout postContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        this.currentUser = MainActivity.getCurrentUser();
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> dataList = new ArrayList<>();
        ArrayList<Post> posts = MainActivity.getCurrentUser().getPosts();
        Toast.makeText(UserInfo.this, "Nombre de posts : " + posts.size(), Toast.LENGTH_SHORT).show();
        for (Post post : posts) {
            dataList.add(post.toString());
        }
        /*dataList.add("Item 1");
        dataList.add("Item 2");
        dataList.add("Item 3");*/

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        TextView textFirstName = findViewById(R.id.textFirstName);
        TextView textLastName = findViewById(R.id.textLastName);
        TextView textEmail = findViewById(R.id.textEmail);
        TextView textPassword = findViewById(R.id.textPassword);

        textFirstName.setText(currentUser.getFirstName());
        textLastName.setText(currentUser.getLastName());
        textEmail.setText(currentUser.getEmail());
        textPassword.setText(currentUser.getPassword());
    }
}

