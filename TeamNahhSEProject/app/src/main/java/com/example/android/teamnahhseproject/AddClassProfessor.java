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

/**
 * The following activity is the intent that opens up whenever a professor
 * wants to add a class.
 *
 * The page allows the professor to create the class and it updates on
 * FireBase so that students can then enroll in said class.
 *
 * author : Alisha Tapiawala
 * generate: Dec.5th.2018
 * version : 1.4
 */
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

        /**
         * When the professor clicks on the add class button it validates whether that is a class or not
         * and sets the instructor ID to the generated class while updating the database.
         */
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

    /**
     * The following method validates if the two strings are valid, and if the two criteria for
     * adding a class are followed (making sure that none of the strings are left empty).
     */
    Boolean validate(String s1, String s2) {
        Boolean result = false;

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(AddClassProfessor.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
