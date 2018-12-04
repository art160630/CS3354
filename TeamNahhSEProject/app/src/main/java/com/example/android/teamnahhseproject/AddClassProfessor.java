package com.example.android.teamnahhseproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClassProfessor extends AppCompatActivity {
    private EditText classInfo;
    private EditText semesterInfo;
    private Button addClassButton;
    String name, classString, semesterString, combinedString;
    FirebaseDatabase database;
    DatabaseReference referenceNewClass, referenceInstructor;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        classInfo = findViewById(R.id.class_text);
        semesterInfo = findViewById(R.id.semester_text);
        addClassButton = findViewById(R.id.add_class);
        database = FirebaseDatabase.getInstance();
        referenceNewClass = database.getReference("classes");
        auth = FirebaseAuth.getInstance();
        referenceInstructor = database.getReference("instructor_users").child(auth.getUid()).child("Classes");

        classString = classInfo.getText().toString();
        semesterString = semesterInfo.getText().toString();
        combinedString = classString + " " + semesterString;

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate(classString, semesterString)){
                    referenceNewClass.child(combinedString).child("Instructor ID").setValue(auth.getUid());
                    referenceNewClass.child(combinedString).child("students").child("student0").setValue("-");
                    referenceInstructor.child(combinedString).setValue("");
                    Toast.makeText(AddClassProfessor.this, "Class could not be added successfully.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddClassProfessor.this, "Class could not be added.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Boolean validate(String s1, String s2) {
        Boolean result = false;

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(AddClassProfessor.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
