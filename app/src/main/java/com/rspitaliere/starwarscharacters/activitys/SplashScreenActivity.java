package com.rspitaliere.starwarscharacters.activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rspitaliere.starwarscharacters.R;
import com.rspitaliere.starwarscharacters.application.MainApplication;
import com.rspitaliere.starwarscharacters.dao.SharedKey;
import com.rspitaliere.starwarscharacters.dao.SharedLocalDAO;
import com.rspitaliere.starwarscharacters.db.DBHandler;
import com.rspitaliere.starwarscharacters.db.DBManager;

import javax.inject.Inject;

public class SplashScreenActivity extends AppCompatActivity {

    @Inject SharedLocalDAO sharedLocalDAO;
    @Inject DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        MainApplication.getMyComponent().inject(this);

        DBManager.initializeInstance(db);

        goToMainOrLogin();
    }



    private void goToMainOrLogin(){
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(SplashScreenActivity.this, LoginActivity.class);
                if (sharedLocalDAO.getString(SharedKey.USER_NAME_KEY) != null){
                    it = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                startActivity(it);
                SplashScreenActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();
            }
        }, 2000);
    }

}
