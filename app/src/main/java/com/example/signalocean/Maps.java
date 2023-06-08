package com.example.signalocean;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;

public class Maps extends AppCompatActivity {
    private MapView map;
    private MyLocationNewOverlay myLocationOverlay;


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
        // Initialiser le gestionnaire de localisation
        myLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), map);
        myLocationOverlay.enableMyLocation();
        map.getOverlays().add(myLocationOverlay);
        GeoPoint moi = myLocationOverlay.getMyLocation();
        //GeoPoint startPoint = new GeoPoint(43.65020, 7.00517);
        IMapController mapController = map.getController();
        mapController.setZoom(18.0);
        mapController.setCenter(moi);

        ArrayList<OverlayItem> items = new ArrayList<>();
        OverlayItem home = new OverlayItem("Yacine le crack", " my office", new GeoPoint(43.65020, 7.00517));
        Drawable m = home.getMarker(0);
        items.add(home);
        items.add(new OverlayItem("fast-food", "mcdo", new GeoPoint(43.64020, 7.00517)));


        ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getApplicationContext(),
                items, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {

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
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Vérifier les autorisations d'accès au GPS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                // Demander les mises à jour de localisation
                myLocationOverlay.enableMyLocation();
                myLocationOverlay.enableFollowLocation();
            }
        } else {
            // Demander les mises à jour de localisation
            myLocationOverlay.enableMyLocation();
            myLocationOverlay.enableFollowLocation();
        }
        map.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Autorisations accordées, démarrer les mises à jour de localisation
                myLocationOverlay.enableMyLocation();
                myLocationOverlay.enableFollowLocation();
            } else {
                // Autorisations refusées, afficher un message d'erreur
                Toast.makeText(this, "Permissions denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ajouterPostFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PostFragment fragment = new PostFragment();
        fragmentTransaction.add(R.id.fragmentContainer, fragment);

        fragmentTransaction.commit();
    }
}
