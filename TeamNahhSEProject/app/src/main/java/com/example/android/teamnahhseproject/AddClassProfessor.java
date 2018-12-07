package com.example.android.teamnahhseproject;

import android.content.Intent;
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
    DatabaseReference referenceNewClass, referenceInstructor, referenceCurrentClass;
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
        referenceCurrentClass = database.getReference("instructor_users").child(auth.getUid()).child("current_class");

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classString = classInfo.getText().toString();
                semesterString = semesterInfo.getText().toString();
                combinedString = classString.concat(" ").concat(semesterString);
                if(validate(classString, semesterString)){
                    referenceNewClass.child(combinedString).child("Instructor ID").setValue(auth.getCurrentUser().getUid());
                    referenceNewClass.child(combinedString).child("students").child("student0").setValue("-");
                    referenceInstructor.child(combinedString).setValue("");
                    referenceCurrentClass.setValue(combinedString);
                    Toast.makeText(AddClassProfessor.this, "Class added successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddClassProfessor.this, ProfessorActivity.class));
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
