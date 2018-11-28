package com.example.android.teamnahhseproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.*;

public class attendance_history_student extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    DatabaseReference reference = database.getReference("student_users/"+firebaseAuth.getUid()+"/Classes/CS_3354_003");
    String attendance_text;
    TextView textBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_history_student);

        textBox = findViewById(R.id.attendance);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    attendance_text = postSnapshot.getKey();
                    textBox.append(attendance_text + "\t");
                    attendance_text = postSnapshot.getValue(String.class);
                    textBox.append(attendance_text + "\n");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });
    }
}
