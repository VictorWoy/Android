package com.example.appcrud.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appcrud.model.Projects;

import java.util.ArrayList;

public class ProjectDB extends SQLiteOpenHelper {

    private static final String DATABASE = "dbprojects";
    private static final int VERSION = 1;

    public ProjectDB(Context context) {
        super(context, DATABASE,null, VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String project = "CREATE TABLE projects(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, titleProject TEXT NOT NULL, descriptionProject TEXT NOT NULL, startDateProject TEXT NOT NULL, endDateProject TEXT NOT NULL, amountPeopleProject INTEGER)";
        db.execSQL(project);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVERSION, int newVersion) {
        String project = "DROP TABLE IF EXISTS projects";
        db.execSQL(project);
    }

    public void saveProject(Projects project){
        ContentValues values = new ContentValues();

        values.put("titleProject",project.getTitleProject());
        values.put("descriptionProject",project.getDescriptionProject());
        values.put("startDateProject",project.getStartDateProject());
        values.put("endDateProject",project.getEndDateProject());
        values.put("amountPeopleProject",project.getAmountPeopleProject());

        getWritableDatabase().insert("projects",null,values);
    }

    public void modifyProject(Projects project) {
        ContentValues values = new ContentValues();

        values.put("titleProject",project.getTitleProject());
        values.put("descriptionProject",project.getDescriptionProject());
        values.put("startDateProject",project.getStartDateProject());
        values.put("endDateProject",project.getEndDateProject());
        values.put("amountPeopleProject",project.getAmountPeopleProject());

        String [] args = {project.getId().toString()};
        getWritableDatabase().update("projects",values,"id=?",args);
    }

    public void deleteProject(Projects project) {
        String [] args = {project.getId().toString()};
        getWritableDatabase().delete("projects","id=?",args);
    }


    public ArrayList<Projects> getList() {
        String [] columns = {"id", "titleProject", "descriptionProject", "startDateProject", "endDateProject", "amountPeopleProject"};
        Cursor cursor = getWritableDatabase().query("projects", columns, null,null,null,null,null,null);
        ArrayList<Projects> projects = new ArrayList<Projects>();

        while (cursor.moveToNext()) {
            Projects project = new Projects();
            project.setId(cursor.getLong(0));
            project.setTitleProject(cursor.getString(1));
            project.setDescriptionProject(cursor.getString(2));
            project.setStartDateProject(cursor.getString(3));
            project.setEndDateProject(cursor.getString(4));
            project.setAmountPeopleProject(cursor.getInt(5));

            projects.add(project);
        }
        return projects;
    }
}