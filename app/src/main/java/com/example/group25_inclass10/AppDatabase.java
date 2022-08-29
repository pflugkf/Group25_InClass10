package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 AppDatabase.java
 * Full Name: Kristin Pflug
 */

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Course.class, version = 3)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract CourseDAO courseDAO();
}
