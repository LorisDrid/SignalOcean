package com.example.signalocean;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.graphics.drawable.Drawable;
import android.os.Bundle;


import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import android.os.Bundle;
import android.preference.PreferenceManager;




import java.util.ArrayList;

public class Maps extends AppCompatActivity {
    private MapView map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(),
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_maps);
        ajouterPostFragment();
        map = (MapView) findViewById(R.id.maps);
        map.setTileSource(TileSourceFactory.MAPNIK);//Utilisation du design patter factory
        map.setBuiltInZoomControls(true);//Zoomable
        GeoPoint startPoint = new GeoPoint(43.65020 , 7.00517);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(startPoint);

        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem( "Yacine le crack"," my office",new GeoPoint(43.65020 , 7.00517));
        Drawable m = home.getMarker(0);
        items.add(home);
        items.add(new OverlayItem("fast-food","mcdo",new GeoPoint(43.64020 , 7.00517)));

        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items,new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item){

                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });

        mOverlay.setFocusItemsOnTap(true);
        map.getOverlays().add(mOverlay);
    }
    @Override
    public void onPause(){
        super.onPause();
        map.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
    }
    private void ajouterPostFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PostFragment fragment = new PostFragment();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);

        fragmentTransaction.commit();
    }
}
