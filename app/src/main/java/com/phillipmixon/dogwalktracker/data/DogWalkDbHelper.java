package com.phillipmixon.dogwalktracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by phill on 1/19/2018.
 */

public class DogWalkDbHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "dog_walks.db";

    public DogWalkDbHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_DOG_WALKS_TABLE = "CREATE TABLE " + DogWalkEntryContract.DogWalkEntry.TABLE_NAME + "( "
                + DogWalkEntryContract.DogWalkEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DogWalkEntryContract.DogWalkEntry.COLUMN_NUMBER_OF_DOGS + " INTEGER NOT NULL, "
                + DogWalkEntryContract.DogWalkEntry.COLUMN_LOCATION + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_DOG_WALKS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
