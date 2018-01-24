package com.rspitaliere.starwarscharacters.application;

import com.rspitaliere.starwarscharacters.component.DaggerMyComponent;
import com.rspitaliere.starwarscharacters.component.MyComponent;
import com.rspitaliere.starwarscharacters.modules.DBModule;

public class MainApplication extends android.app.Application {

    private static MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

   private void initDagger() {
        myComponent = DaggerMyComponent
                .builder()
                .dBModule(new DBModule(this))
                .build();
    }

    public static MyComponent getMyComponent() {
        return myComponent;
    }
}