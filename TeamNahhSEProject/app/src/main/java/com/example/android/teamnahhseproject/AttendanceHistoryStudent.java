package com.example.android.teamnahhseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;

public class AttendanceHistoryStudent extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference referenceCurrentClass = database.getReference("student_users/"+firebaseAuth.getUid()+"/current_class");
    DatabaseReference referenceVar;
    String attendanceText, currentClass;
    TextView attendanceBox, dateBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_student);

        attendanceBox = findViewById(R.id.attendance_box);
        dateBox = findViewById(R.id.date_box);

        referenceCurrentClass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentClass = dataSnapshot.getValue(String.class);

                referenceVar= database.getReference("student_users/"+firebaseAuth.getUid()+"/Classes/"+currentClass);

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
                    @Override
                    public void onCancelled(DatabaseError databaseError) { }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });


    }
}
