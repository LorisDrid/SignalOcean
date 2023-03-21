package edu.frallo.td2ex5_locations;

import android.content.Context;

public interface ClickableActivity {
    Context getContext();
    void onClick(int position);
}
