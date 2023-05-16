package com.example.signalocean;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.signalocean.CreatePostActivity;
import com.example.signalocean.R;

public class PostFragment extends Fragment implements View.OnClickListener {

    private TextView messageTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        messageTextView = view.findViewById(R.id.message);

        view.findViewById(R.id.soleil).setOnClickListener(this);
        view.findViewById(R.id.nuage).setOnClickListener(this);
        view.findViewById(R.id.pluie).setOnClickListener(this);
        view.findViewById(R.id.vent).setOnClickListener(this);
        view.findViewById(R.id.orage).setOnClickListener(this);
        view.findViewById(R.id.temperature).setOnClickListener(this);

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
            case R.id.temperature:
                onImageButtonClicked("Température");
                break;
        }
    }

    private void onImageButtonClicked(String message) {
        messageTextView.setText(message);
    }
}
