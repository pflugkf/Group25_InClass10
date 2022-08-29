package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 GradesFragment.java
 * Full Name: Kristin Pflug
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GradesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GradesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GradesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GradesFragment newInstance(String param1, String param2) {
        GradesFragment fragment = new GradesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.action_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd) {
            mListener.goToAddCourse();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }

    RecyclerView gradesRecyclerView;
    LinearLayoutManager layoutManager;
    GradesRecyclerViewAdapter adapter;
    ArrayList<Course> courses;
    TextView gpaText, hoursText;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.grade_fragment_title);

        gpaText = view.findViewById(R.id.grade_gpaLabel);
        hoursText = view.findViewById(R.id.grade_hoursLabel);
        courses = mListener.getCurrentList();

        gradesRecyclerView = view.findViewById(R.id.recyclerView);
        gradesRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        gradesRecyclerView.setLayoutManager(layoutManager);
        adapter = new GradesRecyclerViewAdapter(courses);
        gradesRecyclerView.setAdapter(adapter);

        if(courses.size() == 0) {
            gpaText.setText("GPA: 4.0");
            hoursText.setText("Hours: 0.0");
        } else {
            double totalHours = 0.0;
            double totalGradePoints = 0.0;

            for (Course course : courses) {
                double courseHours = course.getCourseCreditHours();
                totalHours += courseHours;

                totalGradePoints += (getGradePoints(course.getCourseLetterGrade()) * courseHours);
            }
            hoursText.setText("Hours: " + String.valueOf(totalHours));

            double gpa = totalGradePoints/totalHours;
            gpaText.setText(String.format("GPA: %.2f", gpa));

        }
    }

    double getGradePoints(String letterGrade) {
        double gradePoints = 0.0;

        switch (letterGrade) {
            case "A":
                gradePoints = 4.0;
                break;
            case "B":
                gradePoints = 3.0;
                break;
            case "C":
                gradePoints = 2.0;
                break;
            case "D":
                gradePoints = 1.0;
                break;
            default:
                gradePoints = 0.0;
                break;
        }

        return gradePoints;
    }

    class GradesRecyclerViewAdapter extends RecyclerView.Adapter<GradesRecyclerViewAdapter.GradesViewHolder> {
        ArrayList<Course> coursesArray;

        public GradesRecyclerViewAdapter (ArrayList<Course> courseArrayList) {
            this.coursesArray = courseArrayList;
        }

        @NonNull
        @Override
        public GradesRecyclerViewAdapter.GradesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_list_item, parent, false);
            GradesViewHolder listsViewHolder = new GradesRecyclerViewAdapter.GradesViewHolder(view);

            return listsViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull GradesRecyclerViewAdapter.GradesViewHolder holder, int position) {
            if(coursesArray.size() != 0){
                Course course = coursesArray.get(position);
                holder.letterGrade.setText(course.getCourseLetterGrade());
                holder.courseNumber.setText(course.getCourseNumber());
                holder.courseName.setText(course.getCourseName());
                holder.creditHours.setText(String.valueOf(course.getCourseCreditHours()) + " Credit Hours");
                holder.deleteButton.setClickable(true);
                holder.position = position;
            }
        }

        @Override
        public int getItemCount() {
            return coursesArray.size();
        }

        class GradesViewHolder extends RecyclerView.ViewHolder {
            TextView letterGrade, courseNumber, courseName, creditHours;
            ImageView deleteButton;
            int position;

            public GradesViewHolder(@NonNull View itemView) {
                super(itemView);
                letterGrade = itemView.findViewById(R.id.gradeItem_letterGrade);
                courseNumber = itemView.findViewById(R.id.gradeItem_courseNumber);
                courseName = itemView.findViewById(R.id.gradeItem_courseName);
                creditHours = itemView.findViewById(R.id.gradeItem_creditHours);
                deleteButton = itemView.findViewById(R.id.gradeItem_deleteButton);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.deleteCourse(courses.get(position));
                    }
                });
            }
        }
    }

    GradesFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (GradesFragmentListener) context;
    }

    interface GradesFragmentListener {
        ArrayList<Course> getCurrentList();
        void goToAddCourse();
        void deleteCourse(Course courseToDelete);
    }
}