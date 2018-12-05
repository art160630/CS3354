package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button login;
    private TextView newUser;
    private RadioButton studentButton;
    private RadioButton instructorButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmail = (EditText) findViewById(R.id.etUserEmail);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        login = (Button) findViewById(R.id.btnLogin);
        newUser = (TextView) findViewById(R.id.tvNewUser);
        studentButton = (RadioButton) findViewById(R.id.student_radio_button);
        instructorButton = (RadioButton) findViewById(R.id.instructor_radio_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    mAuth = FirebaseAuth.getInstance();
                    String email = userEmail.getText().toString().trim();
                    String password = userPassword.getText().toString().trim();

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign In failed! Put in a correct Email or Password", Toast.LENGTH_LONG).show();
                            } else {
                                if (studentButton.isChecked()) {
                                    Intent intent = new Intent(MainActivity.this, AddClassStudentActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                                else if (instructorButton.isChecked()) {
                                    Intent intent = new Intent(MainActivity.this, AddClassProfessor.class);
                                    finish();
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfessorOrStudent.class);
                startActivity(intent);
            }
        });


    }

    private Boolean validate(){
        Boolean result = false;
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(password.isEmpty() || email.isEmpty() || (!studentButton.isChecked() && !instructorButton.isChecked())){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
