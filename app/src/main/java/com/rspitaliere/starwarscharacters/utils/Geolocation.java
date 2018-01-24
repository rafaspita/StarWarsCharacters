package com.rspitaliere.starwarscharacters.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;

/**
 * Created by rspitaliere on 23/01/18.
 */

public class Geolocation {

    Context context;
    CharacterDAO characterDAO;

    public Geolocation(Context context, String character){
        this.context = context;
        characterDAO = new CharacterDAO();
        getLocation(character);
    }

    private void getLocation(final String character){

        int per = ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
        if (per == PackageManager.PERMISSION_GRANTED) {

            FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location local = task.getResult();
                        if (local.getLatitude() != 0 && local.getLongitude() != 0) {
                            characterDAO.update(local, character);
                        }

                    }
                }
            });
        }
    }

}
