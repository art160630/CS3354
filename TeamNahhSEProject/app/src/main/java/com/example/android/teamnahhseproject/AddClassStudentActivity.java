package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddClassStudentActivity extends AppCompatActivity {
    private EditText classInfo;
    private EditText semesterInfo;
    private Button addClassButton;
    boolean found;
    int f;
    String name, classString, semesterString, combinedString, getKey;
    FirebaseDatabase database;
    DatabaseReference referenceClasses, referenceStudents, referenceName, referenceStudentAdd, referenceCurrentClass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        classInfo = findViewById(R.id.class_text);
        semesterInfo = findViewById(R.id.semester_text);
        addClassButton = findViewById(R.id.add_class);
        database = FirebaseDatabase.getInstance();
        referenceClasses = database.getReference("classes");
        auth = FirebaseAuth.getInstance();
        referenceName = database.getReference("student_users").child(auth.getUid()).child("name");
        referenceCurrentClass = database.getReference("student_users").child(auth.getUid()).child("current_class");

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                classString = classInfo.getText().toString();
                semesterString = semesterInfo.getText().toString();
                combinedString = classString.concat(" ").concat(semesterString);

                if(validate(classString, semesterString)) {
                    referenceClasses.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if (snapshot.getKey().compareTo(combinedString) == 0) {
                                    found = true;
                                    break;
                                }
                            }

                            if(found) {
                                referenceName.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        name = dataSnapshot.getValue(String.class);
                                        referenceStudents = database.getReference("classes").child(combinedString).child("students").child(auth.getUid());
                                        referenceStudents.setValue(name);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                                });

                                referenceStudentAdd = database.getReference("student_users").child(auth.getUid()).child("Classes").child(combinedString).child("date0");
                                referenceStudentAdd.setValue("-");
                                referenceCurrentClass.setValue(combinedString);
                                Toast.makeText(AddClassStudentActivity.this, "Class was successfully added.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AddClassStudentActivity.this, StudentActivity.class));
                            }
                            else {
                                Toast.makeText(AddClassStudentActivity.this, "Class was not found.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                    });


                }
            }
        });


    }

    private Boolean validate(String s1, String s2) {
        Boolean result = false;

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

//    private void setFound(){
//        f = 1;
//    }
//
//    private int returnFound(){
//        return f;
//    }


}
