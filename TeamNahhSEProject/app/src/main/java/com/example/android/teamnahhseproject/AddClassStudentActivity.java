package com.example.android.teamnahhseproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassStudentActivity extends AppCompatActivity {
    private EditText classInfo;
    private EditText semesterInfo;
    private boolean found;
    String name, s1, s2, s3;
    FirebaseDatabase database;
    DatabaseReference reference1, reference2, reference3;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        classInfo = findViewById(R.id.class_text);
        semesterInfo = findViewById(R.id.semester_text);
        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("classes");
        auth = FirebaseAuth.getInstance();
        reference3 = database.getReference("student_users").child(auth.getUid()).child("name");

         s1 = classInfo.getText().toString();
         s2 = semesterInfo.getText().toString();
         s3 = s1 + " " + s2;

        if(validate(s1, s2)) {
            reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        if (snapshot.getKey().equals(s3)) {
                            found = true;
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });

            if(found) {
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        name = dataSnapshot.getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
                reference2 = database.getReference("classes").child(s3).child("students").child(auth.getUid());
                reference2.setValue(name);

            }
            else {
                Toast.makeText(this, "That class was not found.", Toast.LENGTH_LONG).show();
            }


        }


    }private Boolean validate(String s1, String s2) {
        Boolean result = false;

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }


}
