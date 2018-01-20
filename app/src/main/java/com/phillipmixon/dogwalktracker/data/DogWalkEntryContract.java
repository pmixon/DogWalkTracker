package com.phillipmixon.dogwalktracker.data;

import android.provider.BaseColumns;

/**
 * Created by phill on 1/19/2018.
 */

public class DogWalkEntryContract {

    private DogWalkEntryContract() {};

    public static final class DogWalkEntry implements BaseColumns {

        public static final String TABLE_NAME = "dogwalks";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_NUMBER_OF_DOGS = "number_of_dogs";
        public static final String COLUMN_LOCATION = "location";

    }

}
