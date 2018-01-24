package com.rspitaliere.starwarscharacters.modules;

import android.content.Context;

import com.rspitaliere.starwarscharacters.dao.CharMovieDAO;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;
import com.rspitaliere.starwarscharacters.dao.MoviesDAO;
import com.rspitaliere.starwarscharacters.dao.SharedLocalDAO;
import com.rspitaliere.starwarscharacters.db.DBHandler;
import com.rspitaliere.starwarscharacters.db.DBManager;
import com.rspitaliere.starwarscharacters.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rspitaliere on 23/01/18.
 */

@Module
public class DBModule {

    private Context context;

    public DBModule(Context context){
        this.context = context;
    }

    @Provides
    @PerActivity
    public CharacterDAO provideCharacterDAO(){
        return new CharacterDAO();
    }

    @Provides
    @PerActivity
    public MoviesDAO provideMoviesDAO(){
        return new MoviesDAO();
    }

    @Provides
    @PerActivity
    public CharMovieDAO provideCharMovieDAO(){
        return new CharMovieDAO();
    }

    @Provides
    @PerActivity
    public SharedLocalDAO provideSharedLocal(){
        return new SharedLocalDAO(context);
    }

    @Provides
    @PerActivity
    public DBHandler provideDBHandler(){
        return new DBHandler(context);
    }

}
