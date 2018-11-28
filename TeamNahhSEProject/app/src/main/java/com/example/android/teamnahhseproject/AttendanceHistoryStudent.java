package com.example.android.teamnahhseproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.*;
import com.google.firebase.auth.FirebaseAuth;

public class AttendanceHistoryStudent extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth authentication = FirebaseAuth.getInstance();
    DatabaseReference referenceVar = database.getReference("student_users/"+authentication.getUid()+"/Classes/CS_3354_003");
    String attendanceText;
    TextView t = (TextView) findViewById(R.id.textBox);

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_student);

      referenceVar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    attendanceText = postSnapshot.getKey();
                    t.append(attendanceText + "\t");
                    attendanceText = postSnapshot.getValue(String.class);
                    t.append(attendanceText + "\n");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

    }

}
