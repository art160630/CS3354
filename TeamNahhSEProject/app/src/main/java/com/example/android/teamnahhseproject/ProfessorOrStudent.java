package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The following activity is the intent that opens up whenever the user clicks
 * "New User? Sign up by clicking here!"
 *
 * The page asks the user whether they are a professor or not.
 *
 * author : Nabeel Khan
 * generate: Nov.28th.2018
 * version : 1.3
 */
public class ProfessorOrStudent extends AppCompatActivity {
    private Button btnYes;
    private Button btnNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_or_student);

        btnNo = (Button) findViewById(R.id.btnNo);
        btnYes= (Button) findViewById(R.id.btnYes);

        /**
         * when the button that says "no" is clicked, open up the student registration activity.
         */
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorOrStudent.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        /**
         * when the button that says "yes" is clicked, open up the professor validation activity.
         */
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorOrStudent.this, ProfessorValidationActivity.class);
                startActivity(intent);
            }
        });

    }
}
