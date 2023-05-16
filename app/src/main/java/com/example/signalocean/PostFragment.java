package com.example.signalocean;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PostFragment extends Fragment implements View.OnClickListener {

    private TextView messageTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        // Initialize the message TextView
        messageTextView = view.findViewById(R.id.message);

        // Set click listeners for all the ImageButtons
        view.findViewById(R.id.soleil).setOnClickListener(this);
        view.findViewById(R.id.nuage).setOnClickListener(this);
        view.findViewById(R.id.pluie).setOnClickListener(this);
        view.findViewById(R.id.vent).setOnClickListener(this);
        view.findViewById(R.id.orage).setOnClickListener(this);
        view.findViewById(R.id.temperature).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // Call the onImageButtonClicked method with the appropriate message for the clicked button
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
        // Update the message TextView with the appropriate text
        messageTextView.setText(message);
    }
}

