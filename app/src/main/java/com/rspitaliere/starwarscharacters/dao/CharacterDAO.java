package com.rspitaliere.starwarscharacters.dao;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import com.rspitaliere.starwarscharacters.db.DBManager;
import com.rspitaliere.starwarscharacters.dto.CharacterDTO;
import com.rspitaliere.starwarscharacters.dto.ResultsDTO;
import com.rspitaliere.starwarscharacters.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;

/**
 * Created by rspitaliere on 21/01/18.
 */

public class CharacterDAO implements CharacterTableColumns {

    public static String createTable(){

        return "CREATE TABLE " + TABLENAME + "("
                + KEYID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + HEIGHT + " TEXT,"
                + MASS + " TEXT,"
                + HAIRCOLOR + " TEXT,"
                + SKINCOLOR + " TEXT,"
                + EYECOLOR + " TEXT,"
                + BIRTHYEAR + " TEXT,"
                + GENDER + " TEXT,"
                + TIME + " INTEGER,"
                + LATITUDE + " INTEGER,"
                + LONGITUDE + " INTEGER,"
                + URL + " TEXT,"
                + " UNIQUE (" + URL + ") ON CONFLICT REPLACE)";
    }

    public long insert(CharacterDTO characterDTO){
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, characterDTO.getName());
        values.put(HEIGHT, characterDTO.getHeight());
        values.put(MASS, characterDTO.getMass());
        values.put(HAIRCOLOR, characterDTO.getHairColor());
        values.put(SKINCOLOR, characterDTO.getSkinColor());
        values.put(EYECOLOR, characterDTO.getEyeColor());
        values.put(BIRTHYEAR, characterDTO.getBirthYear());
        values.put(GENDER, characterDTO.getGender());
        values.put(TIME, Utils.getTimeLong());
        values.put(URL, characterDTO.getUrl());

        // Inserting Row
        long row = db.insert(TABLENAME, null, values);
        System.err.println("CharacterDAO " + row);

        DBManager.getInstance().closeDatabase();

        return row;
        //CharMovieDAO charMovieDAO = new CharMovieDAO();
        //charMovieDAO.insert(characterDTO.getFilms(), row);
    }

    public List<CharacterDTO> selectCharacters(){
        List<CharacterDTO> list = new ArrayList<>();

        String selectQuery = "SELECT  " + KEYID + ", " + NAME + ", " + URL + " FROM " + TABLENAME;

        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CharacterDTO characterDTO = new CharacterDTO();
                characterDTO.setId(cursor.getInt(cursor.getColumnIndex(KEYID)));
                characterDTO.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                characterDTO.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                list.add(characterDTO);
            }while (cursor.moveToNext());
        }

        return list;
    }

    public CharacterDTO selectChar(int key){

        CharacterDTO characterDTO = new CharacterDTO();

        String selectQuery = "SELECT * FROM " + TABLENAME + " WHERE " + KEYID + " = '" + key + "'";

        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                characterDTO.setHeight(cursor.getString(cursor.getColumnIndex(HEIGHT)));
                characterDTO.setMass(cursor.getString(cursor.getColumnIndex(MASS)));
                characterDTO.setHairColor(cursor.getString(cursor.getColumnIndex(HAIRCOLOR)));
                characterDTO.setSkinColor(cursor.getString(cursor.getColumnIndex(SKINCOLOR)));
                characterDTO.setEyeColor(cursor.getString(cursor.getColumnIndex(EYECOLOR)));
                characterDTO.setBirthYear(cursor.getString(cursor.getColumnIndex(BIRTHYEAR)));
                characterDTO.setGender(cursor.getString(cursor.getColumnIndex(GENDER)));
                characterDTO.setTime(cursor.getLong(cursor.getColumnIndex(TIME)));
                characterDTO.setLatitude(cursor.getDouble(cursor.getColumnIndex(LATITUDE)));
                characterDTO.setLongitude(cursor.getDouble(cursor.getColumnIndex(LONGITUDE)));
            } while (cursor.moveToNext());
        }

        return characterDTO;
    }

    public int update(Location location, String character) {
        SQLiteDatabase db = DBManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(LATITUDE, location.getLatitude());
        values.put(LONGITUDE, location.getLongitude());

        // updating row
        return db.update(TABLENAME, values, NAME + " = ?",
                new String[]{character});
    }


}
