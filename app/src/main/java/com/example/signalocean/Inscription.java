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
import com.google.firebase.auth.UserProfileChangeRequest;

public class Inscription extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inscription.this, Connexion.class);
                startActivity(intent);
            }
        });

        Button connexion = findViewById(R.id.connexion);
        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.emailInscription);
                EditText passwordEditText = findViewById(R.id.motdepasseInscription);
                EditText nomEditText = findViewById(R.id.nomInscription);
                EditText prenomEditText = findViewById(R.id.prenomInscription);
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String nom = nomEditText.getText().toString();
                String prenom = prenomEditText.getText().toString();

                // Create a new user with the provided email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Inscription.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // User creation successful
                                    Toast.makeText(Inscription.this, "User created successfully.",
                                            Toast.LENGTH_SHORT).show();

                                    // Set additional user metadata
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                            .setDisplayName(nom + " " + prenom)
                                            .build();

                                    user.updateProfile(profileUpdates)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Additional user metadata set successfully
                                                        Toast.makeText(Inscription.this, "Additional user metadata set successfully.",
                                                                Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        // Failed to set additional user metadata
                                                        Toast.makeText(Inscription.this, "Failed to set additional user metadata.",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                    // Proceed to the MainActivity or any other desired activity
                                    Intent intent = new Intent(Inscription.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    // User creation failed
                                    Toast.makeText(Inscription.this, "User creation failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
