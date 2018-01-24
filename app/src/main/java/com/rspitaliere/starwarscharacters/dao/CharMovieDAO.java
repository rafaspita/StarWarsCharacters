package com.rspitaliere.starwarscharacters.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rspitaliere.starwarscharacters.db.DBManager;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rspitaliere on 22/01/18.
 */

public class CharMovieDAO implements CharMovieTableColumns {

    public static String createTable(){

        return "CREATE TABLE " + TABLENAME + "("
                + KEYID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CHARACTERID + " TEXT,"
                + MOVIEURL + " TEXT)";
    }

    public void insert(String movieUrl, long row){
        MoviesDAO moviesDAO = new MoviesDAO();
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

            values.put(MOVIEURL, movieUrl);
            values.put(CHARACTERID, row);
            // Inserting Row
            db.insert(TABLENAME, null, values);
            System.err.println("CharacterDAO " + row);

        DBManager.getInstance().closeDatabase();
    }

    public List<MovieDTO> select(int id){
        List<MovieDTO> list = new ArrayList<>();
        MoviesDAO moviesDAO = new MoviesDAO();
        //movieDao

        String selectQuery = "SELECT  " + MOVIEURL + " FROM " + TABLENAME + " WHERE " + CHARACTERID + " = '" + id + "'" + " ORDER BY " + MOVIEURL + " ASC";

        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(moviesDAO.select(cursor.getString(cursor.getColumnIndex(MOVIEURL))));

            }while (cursor.moveToNext());
        }
        return list;
    }


}
