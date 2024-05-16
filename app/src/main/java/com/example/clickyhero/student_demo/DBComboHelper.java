package com.example.clickyhero.student_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class DBComboHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "tblCombos";

    public DBComboHelper(Context context) {
        super(context, "dbClickyCombos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE tblCombos " +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Correct INTEGER DEFAULT 0, " +
                "combos TEXT, " +
                "name TEXT" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database schema upgrades if needed
    }

    public ArrayList<Combos> getAllCombos() {
        ArrayList<Combos> alStudents = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor resultSet = db.query(TABLE_NAME,
                new String[]{"comboID", "name", "combos", "correct"},
                null, null, null, null, null);

        while (resultSet.moveToNext()) {
            Combos student = new Combos();
            student.setComboID(resultSet.getInt(0));
            student.setName(resultSet.getString(1));
            student.setCombos(stringToIntArray(resultSet.getString(2)));
            student.setCorrect(resultSet.getInt(3) == 1);
            alStudents.add(student);
        }

        resultSet.close();
        db.close();
        return alStudents;
    }

    public void addCombos(ArrayList<Combos> alStudents) {
        SQLiteDatabase db = getWritableDatabase();
        for (Combos student : alStudents) {
            ContentValues values = new ContentValues();
            values.put("name", student.getName());
            values.put("combos", Arrays.toString(student.getCombos()));
            values.put("correct", student.isCorrect() ? 1 : 0);
            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    public void updateCombo(Combos student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("combos", Arrays.toString(student.getCombos()));
        values.put("correct", student.isCorrect() ? 1 : 0);
        db.update(TABLE_NAME, values, "comboID=?", new String[]{String.valueOf(student.getComboID())});
        db.close();
    }

    private int[] stringToIntArray(String str) {
        String[] parts = str.replaceAll("\\[|\\]", "").split(", ");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }
}