package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The following activity is the intent that opens up whenever the professor
 * wants to view the classes that they teach.
 *
 * The page allows for the professor to see everything
 * in their class list
 *
 * author : Alisha Tapiawala
 * generate: Nov.30th.2018
 * version : 1.3
 */
public class ClassesProfessor extends AppCompatActivity{

    private Button addClass, changeClass;
    private TextView classes;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    String classesString = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        addClass = findViewById(R.id.add_class_button);
        changeClass = findViewById(R.id.change_class_button);
        classes = findViewById(R.id.classes_box);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = database.getReference("instructor_users/"+auth.getUid()+"/Classes");

        /**
         * When the following button is clicked, it traverses through the database
         * to find the classes that the professor teaches.
         */
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(snapshot.getKey().equals("class0")){}
                    else{
                        classesString = classesString.concat(snapshot.getKey());
                        classesString = classesString + "\n";
                    }

                }
                classes.setText(classesString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        /**
         * When the button for adding class is pressed open up the intent that adds classes for professors.
         */
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassesProfessor.this, AddClassProfessor.class));

            }
        });

        /**
         * When the button for changing class is clicked, then open up the intent that changes classes.
         */
        changeClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassesProfessor.this, ChangeClassProfessor.class));
            }
        });

    }
}
