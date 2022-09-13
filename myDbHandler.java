package com.example.imuza.bookproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.accounts.AccountManager.KEY_PASSWORD;

public class myDbHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Booksdb.db";
    public static final String TABLE_NAME = "Library";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BookIMG = "Bookimg";
    public static final String COLUMN_BOOkNAME = "Book";
    public static final String COLUMN_WRITERNAME = "Writer";
    public static final String COLUMN_PRICE = "Price";

    //   public static final String COLUMN_IMAGE = "Image";


    public myDbHandler(Context context) {
        super( context, DATABASE_NAME, null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_BOOkNAME + " TEXT," + COLUMN_WRITERNAME + " TEXT,"   + COLUMN_PRICE
                + " INTEGER," + COLUMN_BookIMG + " String " +")";
        //                +COLUMN_IMAGE+ " blob not null)";
      //
        db.execSQL( CREATE_TABLE );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );

    }


    public boolean insertData( String Book, String Writer, String Price, String Bookimg) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( COLUMN_BOOkNAME, Book );
        contentValues.put( COLUMN_WRITERNAME, Writer );
        contentValues.put( COLUMN_PRICE, Price );
        contentValues.put( COLUMN_BookIMG, Bookimg );

        long result = db.insert( TABLE_NAME, null, contentValues );
        if (result == -1) {

            return false;
        } else {
            return true;
        }

    }


    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery( "select *from " + TABLE_NAME, null );
        return res;
    }


    public boolean updateData( String Bookimg,String ID,String Book, String Writer, String Price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put( COLUMN_BookIMG, Bookimg );
        contentValues.put( COLUMN_ID,ID );
        contentValues.put( COLUMN_BOOkNAME, Book );
        contentValues.put( COLUMN_WRITERNAME, Writer );
        contentValues.put( COLUMN_PRICE, Price );
        db.update( TABLE_NAME, contentValues, "ID=?", new String[]{ID} );
        return true;


    }

    public Integer deleteData(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete( TABLE_NAME, "ID" + "=?", new String[]{ID } );

    }
}



