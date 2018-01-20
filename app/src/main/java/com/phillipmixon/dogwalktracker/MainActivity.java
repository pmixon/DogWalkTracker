package com.phillipmixon.dogwalktracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.phillipmixon.dogwalktracker.data.DogWalkDbHelper;
import com.phillipmixon.dogwalktracker.data.DogWalkEntryContract;

public class MainActivity extends AppCompatActivity {

    private DogWalkDbHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DogWalkDbHelper(this);
        db = dbHelper.getReadableDatabase();

        insertDogWalkEntries();
        getDogWalkEntries();

        View view = (View) findViewById(R.id.dog_walk_tracker_textview);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDogWalkEntries();
                getDogWalkEntries();
            }
        });
    }

    public void insertDogWalkEntries() {

        int numberOfDogs = 4;
        String location = "Athens";

        ContentValues values = new ContentValues();
        values.put(DogWalkEntryContract.DogWalkEntry.COLUMN_NUMBER_OF_DOGS, numberOfDogs);
        values.put(DogWalkEntryContract.DogWalkEntry.COLUMN_LOCATION, location);

        long columnNum = db.insert(DogWalkEntryContract.DogWalkEntry.TABLE_NAME, null, values);

        if (columnNum != -1) {
            Toast.makeText(this,"Created column " + Long.toString(columnNum),Toast.LENGTH_LONG).show();
        }
    }

    public void getDogWalkEntries () {
        TextView dogWalksTextView = (TextView) findViewById(R.id.dog_walk_tracker_textview);

        String[] projection = new String[] {
                DogWalkEntryContract.DogWalkEntry._ID,
                DogWalkEntryContract.DogWalkEntry.COLUMN_NUMBER_OF_DOGS,
                DogWalkEntryContract.DogWalkEntry.COLUMN_LOCATION
        };

        Cursor cursor = db.query(DogWalkEntryContract.DogWalkEntry.TABLE_NAME,projection,null,null,null,null,null,null);

        int idColumnIndex = cursor.getColumnIndex(DogWalkEntryContract.DogWalkEntry._ID);
        int dogNumColumnIndex = cursor.getColumnIndex(DogWalkEntryContract.DogWalkEntry.COLUMN_NUMBER_OF_DOGS);
        int locationColumnIndex = cursor.getColumnIndex(DogWalkEntryContract.DogWalkEntry.COLUMN_LOCATION);

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(idColumnIndex);
                int dogsTotal = cursor.getInt(dogNumColumnIndex);
                String location = cursor.getString(locationColumnIndex);

                dogWalksTextView.append("ID: " + Integer.toString(id) + " - ");
                dogWalksTextView.append("Number of Dogs: " + Integer.toString(dogsTotal) + " - ");
                dogWalksTextView.append("Location: " + location + "\n");
            }
        }
        finally {
            cursor.close();
        }
    }
}
