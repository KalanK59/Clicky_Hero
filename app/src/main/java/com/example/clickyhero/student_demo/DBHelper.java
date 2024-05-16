package com.example.clickyhero.student_demo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.clickyhero.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper(Context context) {
        this(context, "dbCombos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating tables

        //Creating table combos
        String query = "";

        query = "CREATE TABLE tblCombos" +
                "(" +
                "comboID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ComboName TEXT, " +
                "ComboSequence INTEGER" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Student2> getAllCombos() {
        ArrayList<Student2> alCombos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor resultSet = db.query("tblCombos", new String[]{"ComboName", "ComboSequence"}, null, null, null, null, null);
        while (resultSet.moveToNext()) {
            String comboName = resultSet.getString(0);
            String comboSequenceString = resultSet.getString(1);

            // Convert the combo sequence string to an array of integers
            String[] comboSequenceArray = comboSequenceString.split(",");
            int[] comboSequence = new int[comboSequenceArray.length];
            for (int i = 0; i < comboSequenceArray.length; i++) {
                comboSequence[i] = Integer.parseInt(comboSequenceArray[i].trim());
            }

            alCombos.add(new Student2(comboName, comboSequence));
        }

        return alCombos;
    }

    public long add(Student2 resupply) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ComboName", resupply.getName());
        // Convert the combo sequence array to a string
        StringBuilder comboSequenceString = new StringBuilder();
        for (int i = 0; i < resupply.getCombos().length; i++) {
            comboSequenceString.append(resupply.getCombos()[i]);
            if (i < resupply.getCombos().length - 1) {
                comboSequenceString.append(",");
            }
        }
        values.put("ComboSequence", comboSequenceString.toString());

        return db.insert("tblCombos", null, values);
    }
}