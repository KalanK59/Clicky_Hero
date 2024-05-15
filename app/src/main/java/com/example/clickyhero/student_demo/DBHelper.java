package com.example.clickyhero.student_demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        String query = "" ;

        query = "CREATE TABLE tblCombos" +
                "(" +
                "comboID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ComboName TEXT, " +
                "ComboSequence INTEGER" +
                ");" ;

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Student2> getAllCombos() {

        ArrayList<Student2> alStudents = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor resultSet = db.query("tblCombos", new String[]{"comboID", "ComboName", "ComboSequence"}, null, null, null, null, null);
        while (resultSet.moveToNext()) {
            Student2 student = new Student2();
            student.setComboID(String.valueOf(resultSet.getInt(0)));
            student.setName(resultSet.getString(1));

            // Parse the ComboSequence string into an ArrayList<Integer>
            String comboSequenceString = resultSet.getString(2);
            ArrayList<Integer> comboSequence = new ArrayList<>();
            String[] comboSequenceArray = comboSequenceString.split(",");
            for (String numString : comboSequenceArray) {
                comboSequence.add(Integer.parseInt(numString.trim()));
            }

            student.setCombos(comboSequence);
            alStudents.add(student);
        }
        return alStudents;
    }
}
