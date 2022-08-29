package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 CourseDAO.java
 * Full Name: Kristin Pflug
 */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {

    @Query("SELECT * from courses")
    List<Course> getAll();

    @Insert
    void insertAll(Course... courses);

    @Delete
    void delete(Course course);
}
