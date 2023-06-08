package com.example.signalocean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Compte extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        EditText emailEditText = findViewById(R.id.emailMonCompte);
        EditText nomEditText = findViewById(R.id.displayName);

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            String email = user.getEmail();
            emailEditText.setText(email);
            nomEditText.setText(user.getDisplayName());
        }

        Button monCompteOublie = (Button) findViewById(R.id.mdp);
        monCompteOublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compte.this, MdpOublie.class);
                startActivity(intent);
            }
        });

        Button monCompteRetour = (Button) findViewById(R.id.retour);
        monCompteRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compte.this, Connexion.class);
                startActivity(intent);
            }
        });

        mAuth.getCurrentUser().getDisplayName();



    }




}
