package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 Course.java
 * Full Name: Kristin Pflug
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String courseNumber;

    @ColumnInfo
    public String courseLetterGrade;

    @ColumnInfo
    public String courseName;

    @ColumnInfo
    public int courseCreditHours;

    public Course(String courseNumber, String courseLetterGrade, String courseName, int courseCreditHours) {
        this.courseNumber = courseNumber;
        this.courseLetterGrade = courseLetterGrade;
        this.courseName = courseName;
        this.courseCreditHours = courseCreditHours;
    }

    public Course(String courseNumber, String courseName, int courseCreditHours) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseCreditHours = courseCreditHours;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNumber=" + courseNumber +
                ", courseLetterGrade='" + courseLetterGrade + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCreditHours=" + courseCreditHours +
                '}';
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseID(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseLetterGrade() {
        return courseLetterGrade;
    }

    public void setCourseLetterGrade(String courseLetterGrade) {
        this.courseLetterGrade = courseLetterGrade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public void setCourseCreditHours(int courseCreditHours) {
        this.courseCreditHours = courseCreditHours;
    }
}
