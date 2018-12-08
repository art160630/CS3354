package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The following activity is the intent that opens up whenever the professor
 * wants to change the class that they are viewing.
 *
 * The page allows for the professor to change the class  they
 * wish to view.
 *
 * author : Alisha Tapiawala
 * generate: Nov.30th.2018
 * version : 1.3
 */
public class ChangeClassProfessor extends AppCompatActivity {

    private EditText newClass;
    private Button changeClass;

    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference reference, referenceCurrentClass;

    String newClassString;

    Boolean found = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_class);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = database.getReference("instructor_users/"+auth.getUid()+"/Classes");
        referenceCurrentClass = database.getReference("instructor_users/"+auth.getUid()+"/current_class");

        newClass = findViewById(R.id.change_class_text);
        changeClass = findViewById(R.id.change_class_button);

        /**
         * If the user types in a valid class name then search the database to see if it is a existing class
         * and if the class exists then switch to it.
         */
        changeClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newClassString = newClass.getText().toString();
                if(validate(newClassString)){
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                if(snapshot.getKey().equals(newClassString)) {
                                    found = true;
                                    break;
                                }
                            }

                            if(found){
                                referenceCurrentClass.setValue(newClassString);
                                Toast.makeText(ChangeClassProfessor.this, "Class was changed successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangeClassProfessor.this, ProfessorActivity.class));
                            }
                            else{
                                Toast.makeText(ChangeClassProfessor.this, "The class was not found.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                    });
                }
            }
        });
    }

    /**
     * The following method validates if the user enters a stirng that is valid
     * which essentially means that both strings are a valid input.
     */
    private Boolean validate(String s) {
        Boolean result = false;

        if (s.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }
}
