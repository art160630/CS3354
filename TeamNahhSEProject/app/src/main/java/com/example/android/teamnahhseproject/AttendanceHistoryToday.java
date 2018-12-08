package com.example.android.teamnahhseproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

/**
 * The following activity is the intent that opens up whenever the
 * professor checks the students that attended that day.
 *
 * The page essentially checks the database to see a list of individuals
 * who were present or absent in one day.
 *
 * author : Alisha Tapiawala
 * generate: Dec.5th.2018
 * version : 1.4
 */
public class AttendanceHistoryToday extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference referenceStudent, referenceCurrentClass, referenceClass;

    TextView attendanceText, studentText;

    String currentClass, studentID, student, dateString, presence;

    PulledMethods p = new PulledMethods();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_today);

        attendanceText = findViewById(R.id.attendance_text);
        studentText = findViewById(R.id.student_text);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        referenceCurrentClass = database.getReference("instructor_users/"+auth.getUid()+"/current_class");

        dateString = p.getDate();

        /**
         * When the referenceCurrentClass button is clicked traverse through Firebase
         * to check all the students who were present that day.
         */
        referenceCurrentClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentClass = dataSnapshot.getValue(String.class);

                referenceClass = database.getReference("classes/"+currentClass+"/students");

                referenceClass.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            if(snapshot.getKey().equals("student0")){}
                            else{

                                studentID = snapshot.getKey();
                                student = snapshot.getValue(String.class);
                                studentText.append(student);
                                studentText.append("\n");
                                referenceStudent = database.getReference("student_users/"+studentID+
                                        "/Classes/"+currentClass+"/"+dateString);

                                referenceStudent.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        presence = dataSnapshot.getValue(String.class);
                                        attendanceText.append(presence);
                                        attendanceText.append("\n");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                                });
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

    }
}
