package com.example.signalocean;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfo extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        this.currentUser = MainActivity.getCurrentUser();

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

