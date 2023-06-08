package com.example.signalocean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Connexion extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button inscription = (Button) findViewById(R.id.inscription);
        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connexion.this, Inscription.class);
                startActivity(intent);
            }
        });

        Button mdpoublie = (Button) findViewById(R.id.mdp);
        mdpoublie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connexion.this, MdpOublie.class);
                startActivity(intent);
            }
        });

        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Connexion.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button connexion = findViewById(R.id.connexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the email and password entered by the user
                EditText emailEditText = findViewById(R.id.plain_text_input);
                EditText passwordEditText = findViewById(R.id.plain_text_input2);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Authenticate the user with the provided email and password
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Connexion.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(Connexion.this, "Authentication successful.",
                                            Toast.LENGTH_SHORT).show();
                                    // Proceed to the MainActivity or any other desired activity
                                    Intent intent = new Intent(Connexion.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Connexion.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
