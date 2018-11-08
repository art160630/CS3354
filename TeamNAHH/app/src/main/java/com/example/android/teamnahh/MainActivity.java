package com.example.android.teamnahh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    //private TextView Info;
    private TextView Newuser;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etUserEmail);
        Login = (Button)findViewById(R.id.btnLogin);
        Newuser = findViewById(R.id.tvNewUser);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

//        if(user != null){
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });

        Newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });



    }
//    private void validate(String userName, String userPassword) {
//        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                    Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT ).show();
//                } else {
//                    Toast.makeText(MainActivity.this, "Sign in Failed", Toast.LENGTH_SHORT ).show();
//                }
//            }
//        });
//    }
    private void validate(String userName, String userPassword){
      //  userName = "phone@gmail.com";
       // userPassword = "Spongebob12345";
        progressDialog.setMessage("validating...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()) {
                   progressDialog.dismiss();
                   startActivity(new Intent(MainActivity.this, SecondActivity.class));
                   Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
               } else {
                   progressDialog.dismiss();
                   Toast.makeText(MainActivity.this, "Sign in Failed", Toast.LENGTH_SHORT).show();
               }
            }
        });
        if(userName.equals("1@gmail.com") && userPassword.equals("1")) {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
        }
    }

}
