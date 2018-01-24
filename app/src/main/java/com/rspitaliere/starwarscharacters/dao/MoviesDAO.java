package com.rspitaliere.starwarscharacters.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rspitaliere.starwarscharacters.db.DBManager;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.DBMovieDTO;
import com.rspitaliere.starwarscharacters.dto.MovieDTO;
import com.rspitaliere.starwarscharacters.dto.ResultsDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rspitaliere on 21/01/18.
 */

public class MoviesDAO implements MovieTableColumns {

    public static String createTable(){

        return "CREATE TABLE " + TABLENAME + "("
                + KEYID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + EPISODEID + " TEXT,"
                + OPENINGCRAWL + " TEXT,"
                + DIRECTOR + " TEXT,"
                + PRODUCER + " TEXT,"
                + RELEASEDATE + " TEXT,"
                + URL + " TEXT,"
                + IMAGEURL + " TEXT ,"
                + MOVIEURL + " TEXT,"
                + " UNIQUE (" + EPISODEID + "))";
    }

    public void insert(MovieDTO movieDTO){
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();


        values.put(TITLE, movieDTO.getTitle());
        values.put(EPISODEID, movieDTO.getEpisodeId());
        values.put(OPENINGCRAWL, movieDTO.getOpeningCrawl());
        values.put(DIRECTOR, movieDTO.getDirector());
        values.put(PRODUCER, movieDTO.getProducer());
        values.put(RELEASEDATE, movieDTO.getReleaseDate());
        values.put(URL, movieDTO.getUrl());

        // Inserting Row
        long row = db.insert(TABLENAME, null, values);
        System.err.println("Movie " + row);

        DBManager.getInstance().closeDatabase();
    }

    public Boolean verifyMovieInTable(String url){
        String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE " + URL + " = " + "'" + url + "'";
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(KEYID)) != null){
                   return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    public Boolean verifyImageinTable(String url){
        String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE " + URL + " = " + "'" + url + "'";
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(IMAGEURL)) != null){
                    return true;
                }
            }while (cursor.moveToNext());
        }
        return false;
    }

    public MovieDTO select(String url){
        MovieDTO movieDTO = new MovieDTO();
        //movieDao

        String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE " + URL + " = " + "'" + url + "'";

        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                movieDTO.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                movieDTO.setOpeningCrawl(cursor.getString(cursor.getColumnIndex(OPENINGCRAWL)));
                movieDTO.setEpisodeId(cursor.getInt(cursor.getColumnIndex(EPISODEID)));
                movieDTO.setDirector(cursor.getString(cursor.getColumnIndex(DIRECTOR)));
                movieDTO.setProducer(cursor.getString(cursor.getColumnIndex(PRODUCER)));
                movieDTO.setReleaseDate(cursor.getString(cursor.getColumnIndex(RELEASEDATE)));
                movieDTO.setImageUrl(cursor.getString(cursor.getColumnIndex(IMAGEURL)));

            }while (cursor.moveToNext());
        }

        return movieDTO;
    }

    public int update(ResultsDTO resultsDTO, String title) {
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(IMAGEURL, resultsDTO.getPosterPath());

        // updating row
        return db.update(TABLENAME, values, TITLE + " = ?",
                new String[]{title});
    }


}

