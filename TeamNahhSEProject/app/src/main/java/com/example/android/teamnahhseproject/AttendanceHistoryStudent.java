package com.example.android.teamnahhseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;

/**
 * The following activity is the intent that opens up whenever the student
 * accesses their attendance history page after signing in.
 *
 * The page displays the FireBase data associated with the attendance history
 * for the student.
 *
 * author : Alisha Tapiawala
 * generate: Nov.30th.2018
 * version : 1.3
 */
public class AttendanceHistoryStudent extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference referenceVar = database.getReference("student_users/"+firebaseAuth.getUid()+"/Classes/CS_3345_003");
    String attendanceText;
    TextView attendanceBox, dateBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_student);

        attendanceBox = findViewById(R.id.attendance_box);
        dateBox = findViewById(R.id.date_box);

        /**
         * when attendance history button is clicked then traverse through FireBase data
         * and show data to the student, along with the date
         */
        referenceVar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    attendanceText = postSnapshot.getKey();
                    dateBox.append(attendanceText + "\n");
                    attendanceText = postSnapshot.getValue(String.class);
                    attendanceBox.append(attendanceText + "\n");
                }
            }
            /**
             * cancel process if the database has an error
             */
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
