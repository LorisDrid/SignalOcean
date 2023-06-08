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
import com.google.firebase.auth.FirebaseAuth;

public class MdpOublie extends Activity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mdp_oublie);

        mAuth = FirebaseAuth.getInstance();

        Button suivant = findViewById(R.id.Suivant);
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailEditText = findViewById(R.id.emailOublie);
                String email = emailEditText.getText().toString();

                if (!email.isEmpty()) {
                    // Send password reset email
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Password reset email sent successfully
                                        Toast.makeText(MdpOublie.this, "Password reset email sent.",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        // Failed to send password reset email
                                        Toast.makeText(MdpOublie.this, "Failed to send password reset email.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Email field is empty
                    Toast.makeText(MdpOublie.this, "Please enter your email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button retour = findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MdpOublie.this, Connexion.class);
                startActivity(intent);
            }
        });
    }

}
