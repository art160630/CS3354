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

/**
 * The following activity essentially opens up the user's login page
 * and is considered as the app's "home" page.
 *
 * The page has the option for a user to sign in, depending on whether they
 * are a student or a professor.
 * The page also offers a new user to create a new account by clicking on the
 * text box on the bottom of the page.
 *
 * author : Nabeel Khan
 * generate: Oct.10th.2018
 * version : 1.4
 */
public class MainActivity extends AppCompatActivity {

    //establish the entities that are associated with the XML file
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

        //assign the variables we defined earlier to the ones defined in the layout file
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        login = (Button) findViewById(R.id.btnLogin);
        newUser = (TextView) findViewById(R.id.tvNewUser);
        studentButton = (RadioButton) findViewById(R.id.student_radio_button);
        instructorButton = (RadioButton) findViewById(R.id.instructor_radio_button);

        /**
         * if button "login" is clicked validate the user's email and password and
         * communicate with the Firebase Database to see if they exist in the
         * database
         */
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
                                    Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                                else if (instructorButton.isChecked()) {
                                    Intent intent = new Intent(MainActivity.this, ProfessorActivity.class);
                                    finish();
                                    startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
        });

        /**
         * if TextView "newUser" is clicked open page that asks them if they are
         * a professor or a student
         */
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfessorOrStudent.class);
                startActivity(intent);
            }
        });


    }

    /**
     * Validates whether the user entered both the email and the password, and if they
     * didn't then inform the user using a Toast
     */
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
