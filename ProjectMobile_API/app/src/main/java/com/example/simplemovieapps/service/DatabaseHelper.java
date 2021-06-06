package com.example.simplemovieapps.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.simplemovieapps.model.SaveModel;
import com.example.simplemovieapps.model.UserModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "quote_database";
    //Database Table name
    private static final String TABLE_NAME = "save_quote";
    public static final String TABLE_USER = "user";
    //Table columns
    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String BODY = "body";
    public static final String TAGS = "tags";
    public static final String FAVORITE = "favorite";
    public static final String NUM = "num";

    public static final String NAME = "name";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private SQLiteDatabase sqLiteDatabase;



    //Constructor
    public DatabaseHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME +"("+ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT," + AUTHOR + " TEXT NOT NULL,"+BODY+" TEXT NOT NULL,"+TAGS+" TEXT,"+FAVORITE+" TEXT NOT NULL,"+NUM+" INTEGER NOT NULL);";
        db.execSQL(query);
        String user = "create table " + TABLE_USER +"("+ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+USERNAME+" TEXT NOT NULL,"+PASSWORD+" TEXT NOT NULL);";
        db.execSQL(user);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    //Add  Data
    public void addQuote(SaveModel saveModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.AUTHOR, saveModel.getAuthor());
        contentValues.put(this.BODY, saveModel.getBody());
        contentValues.put(this.FAVORITE, saveModel.getFavorite());
        contentValues.put(this.TAGS, saveModel.getTags());
        int num = saveModel.getNum();
        contentValues.put(this.NUM, num);
        sqLiteDatabase.insert(TABLE_NAME, null,contentValues);
    }
    public void addUser(UserModel userModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(this.NAME, userModel.getNama());
        contentValues.put(this.USERNAME, userModel.getUsername());
        contentValues.put(this.PASSWORD, userModel.getPassword());
        sqLiteDatabase.insert(TABLE_USER, null,contentValues);
    }

    public ArrayList<SaveModel> getQuoteList(int user){
        String sql = "select * from " + TABLE_NAME +" WHERE " + NUM + " = " + user + " ORDER BY " + ID + " DESC";
        ArrayList<SaveModel> storeSave = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String author = cursor.getString(1);
                String body = cursor.getString(2);
                String tags = cursor.getString(3);
                String favorite = cursor.getString(4);
                int num = Integer.parseInt(cursor.getString(5));
                storeSave.add(new SaveModel(id,author,body,tags,favorite,num));
//                storeSave.add(new SaveModel(1,"apa","body","tags","favorite",2));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeSave;
    }
    public ArrayList<UserModel> getUser(){
        String sql = "select * from " + TABLE_USER;
        ArrayList<UserModel> storeSave = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String nama = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                storeSave.add(new UserModel(id,nama,username,password));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeSave;
    }
//    public Cursor oneData(int i){
//        Cursor cur = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME + " WHERE " + ID + " = " + i, null);
//        return cur;
//    }

    public void updateQuote(int id, String favorite){
        ContentValues contentValues = new ContentValues();
        contentValues.put(FAVORITE,favorite);
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = " + id, null);
    }

    public void deleteQuote(int id){
        sqLiteDatabase.delete(TABLE_NAME, ID + " = " + id , null);
    }

}
