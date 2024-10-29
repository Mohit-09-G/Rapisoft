package com.example.rapisoft.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TestDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Rapisoft.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "Dataset";

    public static final String COL_PHONE = "phone";
    public static final String COL_NAME = "name";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_CATEGORY = "category";
    public static final String COL_ROLE = "role";

    public TestDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_PHONE + " TEXT , " +
                COL_NAME + " TEXT, " +
                COL_EMAIL + " TEXT PRIMARY KEY, " +
                COL_PASSWORD + " TEXT," +
                COL_ROLE + " TEXT," +
                COL_CATEGORY + " TEXT" +
                ")";
        db.execSQL(createTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertLoginData( String phone, String name,String email, String password,String role,String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PHONE, phone);
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_ROLE, role);
        contentValues.put(COL_CATEGORY, category);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return result != -1;
    }

    public boolean checkUserCredential(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ?", new String[]{email, password});

        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isValid;
    }
    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_EMAIL+ "=?",new String[]{email});
        boolean exists= cursor.getCount() >0;
        cursor.close();
        return exists;
    }
    public boolean checkUserCredentialsWithRole(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " +
                        COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ? AND (" +
                        COL_ROLE + " = ? OR " + COL_ROLE + " = ?)",
                new String[]{email, password, "Employee", "Worker"});

        boolean isValid = cursor.getCount() > 0;
        Log.d("UserCredentialsCheck", "Email: " + email + ", Valid: " + isValid);
        cursor.close();
        db.close();
        return isValid;
    }


    public String getUserRole(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_ROLE + " FROM " + TABLE_NAME + " WHERE " +
                COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ?", new String[]{email, password});

        String role = null;
        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndexOrThrow(COL_ROLE));
        }

        Log.d("UserRoleFetch", "Email: " + email + ", Role: " + role);
        cursor.close();
        db.close();
        return role;
    }


    public String checkUserCredentialsAndRole(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_ROLE + " FROM " + TABLE_NAME + " WHERE " +
                        COL_EMAIL + " = ? AND " + COL_PASSWORD + " = ? AND (" +
                        COL_ROLE + " = ? OR " + COL_ROLE + " = ?)",
                new String[]{email, password, "Employee", "Worker"});

        String role = null;
        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndexOrThrow(COL_ROLE));
        }

        Log.d("UserCredentialsCheck", "Email: " + email + ", Role: " + role);
        cursor.close();
        db.close();


        return role;
    }

    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_EMAIL + " = ?", new String[]{email});

        String name = null;
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
        }

        Log.d("UserNameFetch", "Email: " + email + ", Name: " + name);
        cursor.close();
        db.close();
        return name;
    }



}
