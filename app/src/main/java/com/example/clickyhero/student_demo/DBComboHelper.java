package com.example.clickyhero.student_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBComboHelper extends SQLiteOpenHelper {


    private ArrayList<Student2Backup> alStudents;

    public DBComboHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBComboHelper(Context context) {
        super(context, "dbClickyCombos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addCombos(ArrayList<Student2Backup> alStudents) {
        this.alStudents = alStudents;
    }
}
