package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ProfessorRegistrationPage extends AppCompatActivity {

    private EditText userName, userPassword, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_registration_page);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload data to the database

                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();



                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ProfessorRegistrationPage.this, "The registration was successful!!", Toast.LENGTH_SHORT).show();
                                final String name = userName.getText().toString().trim();
                                final String email = userEmail.getText().toString().trim();
                                final String status = "Professor";

                                User user = new User(name, email, status);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(ProfessorRegistrationPage.this, "it worked", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ProfessorRegistrationPage.this, "did not work", Toast.LENGTH_LONG).show();
                                        }
                                    }                                });
                                startActivity(new Intent(ProfessorRegistrationPage.this, MainActivity.class));
                            } else {
                                Toast.makeText(ProfessorRegistrationPage.this, "Registration failed, try again later!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfessorRegistrationPage.this, MainActivity.class));
            }
        });
    }
    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userEmail = findViewById(R.id.etEmail);
        userPassword = (EditText)findViewById(R.id.etPassword);
        regButton = findViewById(R.id.btnRegister);
        userLogin = findViewById(R.id.tvReturn);

    }
    private Boolean validate(){
        Boolean result = false;
        String name = userName.getText().toString();
        String password = userPassword.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}