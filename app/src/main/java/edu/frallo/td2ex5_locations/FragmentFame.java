package edu.frallo.td2ex5_locations;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class FragmentFame extends Fragment {
    private final String TAG = "frallo "+getClass().getSimpleName();
    private String attachedActivity;

    public FragmentFame() {
        Log.d(TAG, "Fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fame, container, false);

        return view; }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        attachedActivity = getActivity().getClass().getSimpleName();
    }
}
