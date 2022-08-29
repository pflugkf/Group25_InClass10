package com.example.group25_inclass10;

/**
 * Assignment #: Group25_InClass10
 * File Name: Group25_InClass10 AddCourseFragment.java
 * Full Name: Kristin Pflug
 */

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCourseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCourseFragment newInstance(String param1, String param2) {
        AddCourseFragment fragment = new AddCourseFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_course, container, false);
    }

    EditText courseNameText, courseNumberText, creditHoursText;
    RadioGroup courseGradesGroup;
    Button submit, cancel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.add_fragment_title);

        courseNameText = view.findViewById(R.id.add_courseName);
        courseNumberText = view.findViewById(R.id.add_courseNumber);
        creditHoursText = view.findViewById(R.id.add_creditHours);
        courseGradesGroup = view.findViewById(R.id.add_gradeRadioGroup);

        submit = view.findViewById(R.id.add_submitButton);
        cancel = view.findViewById(R.id.add_cancelButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = courseNameText.getText().toString();
                String courseNumber = courseNumberText.getText().toString();
                String creditHours = creditHoursText.getText().toString();
                int chosenGradeID = courseGradesGroup.getCheckedRadioButtonId();

                if(courseName.isEmpty()) {
                    AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                    b.setTitle("Error")
                            .setMessage("Please enter a valid course name")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    b.create().show();
                } else if (courseNumber.isEmpty()) {
                    AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                    b.setTitle("Error")
                            .setMessage("Please enter a valid course number")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    b.create().show();
                } else if (creditHours.isEmpty()) {
                    AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                    b.setTitle("Error")
                            .setMessage("Please enter a valid number of credit hours")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    b.create().show();
                } else if (chosenGradeID == -1) {
                    AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
                    b.setTitle("Error")
                            .setMessage("Please choose a grade")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    b.create().show();
                } else {
                    String letterGrade = "";

                    if(chosenGradeID == R.id.add_gradeRadioA){
                        letterGrade = "A";
                    } else if(chosenGradeID == R.id.add_gradeRadioB) {
                        letterGrade = "B";
                    } else if(chosenGradeID == R.id.add_gradeRadioC) {
                        letterGrade = "C";
                    } else if(chosenGradeID == R.id.add_gradeRadioD) {
                        letterGrade = "D";
                    } else if(chosenGradeID == R.id.add_gradeRadioF) {
                        letterGrade = "F";
                    }

                    Course newCourse = new Course(courseNumber, letterGrade, courseName, Integer.parseInt(creditHours));
                    mListener.addNewCourse(newCourse);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancel();
            }
        });

    }

    AddCourseFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mListener = (AddCourseFragmentListener) context;
    }

    interface AddCourseFragmentListener {
        void addNewCourse(Course newCourse);
        void cancel();
    }
}