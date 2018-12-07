package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The following activity validates if the user is a professor or not.
 *
 * The page asks the user to enter in the code and validates it to the
 * the user has inputted the correct code.

 *
 * author : Nabeel Khan
 * generate: Nov.21.2018
 * version : 1.2
 */
public class ProfessorValidationActivity extends AppCompatActivity {

    private EditText proveProfessor;
    private Button validate;
    private TextView returnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_validation);
        proveProfessor = (EditText) findViewById(R.id.etValidateProfessor);
        validate = (Button) findViewById(R.id.btnValidate);
        returnLogin = (TextView) findViewById(R.id.tvReturnLogin);


        /**
         * begins the method that validates the user's string, and converts the
         * input of the user into a String
         */
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = proveProfessor.getText().toString().trim();
                validate(code);
            }
        });

        /**
         *  when the user clicks the "return"  button it returns them back to the login page
         */
        returnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorValidationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
    /**
     * The following method validates the password that the user enters
     */
    private void validate(String pass){
        if(pass.equals("1234")){
            Intent intent = new Intent(ProfessorValidationActivity.this, ProfessorRegistrationPage.class);
            startActivity(intent);
        } else {
            Toast.makeText(ProfessorValidationActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
        }
    }
}
