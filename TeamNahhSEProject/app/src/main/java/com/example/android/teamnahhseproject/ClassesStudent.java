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

public class ClassesStudent extends AppCompatActivity {

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
        reference = database.getReference("student_users/"+auth.getUid()+"/Classes");

        reference.addValueEventListener(new ValueEventListener() {
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

        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassesStudent.this, AddClassStudentActivity.class));

            }
        });

        changeClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ClassesStudent.this, ChangeClassStudent.class));
            }
        });


    }
}
