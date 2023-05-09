package com.example.signalocean;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Connexion extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

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
    }
}
