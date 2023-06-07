package com.example.signalocean;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class PostFragment extends Fragment implements View.OnClickListener {

    private TextView messageTextView;
    private MainActivity parentActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        Log.d(TAG, "Le fragment est ajouté avec succès");

        messageTextView = view.findViewById(R.id.message);

        view.findViewById(R.id.soleil).setOnClickListener(this);
        view.findViewById(R.id.nuage).setOnClickListener(this);
        view.findViewById(R.id.pluie).setOnClickListener(this);
        view.findViewById(R.id.vent).setOnClickListener(this);
        view.findViewById(R.id.orage).setOnClickListener(this);
        view.findViewById(R.id.vague).setOnClickListener(this);
        view.findViewById(R.id.user).setOnClickListener(this);
        view.findViewById(R.id.home).setOnClickListener(this);

        Button btnCreatePost = view.findViewById(R.id.btn_poster);

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageTextView.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                    intent.putExtra("message", message);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Veuillez entrer un message", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.soleil:
                onImageButtonClicked("Soleil");
                break;
            case R.id.nuage:
                onImageButtonClicked("Nuage");
                break;
            case R.id.pluie:
                onImageButtonClicked("Pluie");
                break;
            case R.id.vent:
                onImageButtonClicked("Vent");
                break;
            case R.id.orage:
                onImageButtonClicked("Orage");
                break;
            case R.id.vague:
                onImageButtonClicked("Vague");
                break;
            case R.id.user:
                Intent intent = new Intent(getActivity(), UserInfo.class);
                startActivity(intent);
                break;
            case R.id.home:
                Intent intent2 = new Intent(getActivity(), MainActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void onImageButtonClicked(String message) {
        messageTextView.setText(message);
    }
}
