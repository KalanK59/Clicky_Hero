package com.example.clickyhero.combos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "tblCombos"; // Table name for combos

    // Constructor
    public DBHelper(Context context) {
        super(context, "dbClickyCombos", null, 1);
    }

    // Create the database table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE tblCombos " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "correct INTEGER DEFAULT 0, " +
                "combos TEXT, " +
                "name TEXT" +
                ")";
        db.execSQL(query);
    }

    // Handle database schema upgrades if needed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No implementation needed for now
    }

    // Get all combos from the database
    public ArrayList<Combos> getAllCombos() {
        ArrayList<Combos> alCombosList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor resultSet = db.query(TABLE_NAME,
                new String[]{"id", "name", "combos", "correct"},
                null, null, null, null, null);

        // Loop through the result set and add each combo to the list
        while (resultSet.moveToNext()) {
            Combos student = new Combos();
            student.setComboID(resultSet.getInt(0));
            student.setName(resultSet.getString(1));
            student.setCombos(stringToIntArray(resultSet.getString(2)));
            student.setCorrect(resultSet.getInt(3));
            alCombosList.add(student);
        }
        //Logs all the combos in the arrayList.
        Log.d("getAllCombos", alCombosList.size() + "");

        resultSet.close();
        db.close();
        return alCombosList;
    }

    // Add a list of combos to the database
    public void addCombos(ArrayList<Combos> alStudents) {
        SQLiteDatabase db = getWritableDatabase();
        for (Combos combo : alStudents) {
            ContentValues values = new ContentValues();
            values.put("name", combo.getName());
            values.put("combos", Arrays.toString(combo.getCombos()));
            values.put("correct", combo.getCorrect());
            Log.d("addCombos", values.toString());
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    // Convert a string to an int array
    private int[] stringToIntArray(String str) {
        String[] parts = str.replaceAll("\\[|\\]", "").split(", ");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }

    // Remove all combos from the database
    public boolean removeCombos() {
        String query = "DELETE FROM tblCombos";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        return true;
    }

    // Update the status of a specific combo
    public void updateComboStatus(Combos selectedCombo, int pressStatus) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("correct", pressStatus);
        Log.d("updateComboStatus", values.toString());
        db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(selectedCombo.getComboID())});
        db.close();
    }
}
