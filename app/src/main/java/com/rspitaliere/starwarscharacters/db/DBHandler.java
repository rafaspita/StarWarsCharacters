package com.rspitaliere.starwarscharacters.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rspitaliere.starwarscharacters.dao.CharMovieDAO;
import com.rspitaliere.starwarscharacters.dao.CharacterDAO;
import com.rspitaliere.starwarscharacters.dao.MoviesDAO;


/**
 * Created by rspitaliere on 27/07/17.
 */

public class DBHandler extends SQLiteOpenHelper{

    // Database Version - OLD 1
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "starwarsdb";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CharacterDAO.createTable());
        db.execSQL(CharMovieDAO.createTable());
        db.execSQL(MoviesDAO.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
