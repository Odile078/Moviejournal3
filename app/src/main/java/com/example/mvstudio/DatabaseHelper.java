package com.example.mvstudio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

   /* public static final String DATABASE_NAME ="mylist.db";
    public static final String TABLE_NAME ="mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";*/

    public static final String DB_NAME = "Movies.db";
    public static final String DB_TABLE = " movies_Table";

    public static final String ID = "ID";
    public static final String NAME = "NAME";

    public static final String CREATE_TABLE = "CREATE TABLE "+ DB_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + "TEXT "+ ")";

    public DatabaseHelper(Context context){
        /*super(context,DATABASE_NAME,null,1);*/
        super(context,DB_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       /* String createTable = "CREATE TABLE" + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + "ITEM1 TEXT)";
        sqLiteDatabase.execSQL(createTable);*/
        sqLiteDatabase.execSQL(CREATE_TABLE);


    }


    /* @Override
     public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
         sqLiteDatabase.execSQL("DROP IF TABLE EXISTS" + TABLE_NAME);


     }*/
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ DB_TABLE);
        onCreate(sqLiteDatabase);

    }

    public  boolean insertData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);

        long result = db.insert(DB_NAME, null, contentValues);

        return  result != -1;
    }
}


