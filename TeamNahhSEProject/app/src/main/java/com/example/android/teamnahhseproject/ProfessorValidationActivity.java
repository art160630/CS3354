package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfessorValidationActivity extends AppCompatActivity {

    private EditText proveProfessor;
    private Button validate;
    private TextView returnLogin;
    boolean test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_validation);
        proveProfessor = (EditText) findViewById(R.id.etValidateProfessor);
        validate = (Button) findViewById(R.id.btnValidate);
        returnLogin = (TextView) findViewById(R.id.tvReturnLogin);


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = proveProfessor.getText().toString().trim();
                validate(code);
            }
        });

        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorValidationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    //changed to public for testing purposes
    public void validate(String pass) {
        if (pass.equals("1234")) {
            Intent intent = new Intent(ProfessorValidationActivity.this,
                    ProfessorRegistrationPage.class);
            startActivity(intent);
            test = true;
        } else {
            Toast.makeText(ProfessorValidationActivity.this,
                    "Wrong Password", Toast.LENGTH_SHORT).show();
            test = false;
        }
    }
}
