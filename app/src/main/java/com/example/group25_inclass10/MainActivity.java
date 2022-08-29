package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 MainActivity.java
 * Full Name: Kristin Pflug
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GradesFragment.GradesFragmentListener, AddCourseFragment.AddCourseFragmentListener {

    AppDatabase db;
    ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "course.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        courseList = (ArrayList<Course>) db.courseDAO().getAll();

        getSupportFragmentManager().beginTransaction().add(R.id.rootView, new GradesFragment()).commit();
    }


    @Override
    public ArrayList<Course> getCurrentList() {
        return (ArrayList<Course>) db.courseDAO().getAll();
    }

    @Override
    public void goToAddCourse() {
        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new AddCourseFragment()).addToBackStack(null).commit();
    }

    @Override
    public void deleteCourse(Course courseToDelete) {
        db.courseDAO().delete(courseToDelete);
        courseList = (ArrayList<Course>) db.courseDAO().getAll();

        getSupportFragmentManager().beginTransaction().replace(R.id.rootView, new GradesFragment()).commit();
    }

    @Override
    public void addNewCourse(Course newCourse) {
        db.courseDAO().insertAll(newCourse);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancel() {
        getSupportFragmentManager().popBackStack();
    }
}