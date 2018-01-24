package com.rspitaliere.starwarscharacters.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rspitaliere on 14/12/17.
 */

public class Permissions extends AppCompatActivity {

    Context context;
    Activity activity;
    private static final int REQUEST_ID = 100;

    public Permissions (Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    public boolean checkAndRequestPermissions(List<String> permissions) {
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String permission : permissions){
            int per = ContextCompat.checkSelfPermission(context, permission);
            if (per != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }

        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID);
            return false;
        }
        return true;
    }

}
