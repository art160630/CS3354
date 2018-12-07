package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeClassStudent extends AppCompatActivity {

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
        reference = database.getReference("student_users/"+auth.getUid()+"/Classes");
        referenceCurrentClass = database.getReference("student_users/"+auth.getUid()+"/current_class");

        newClass = findViewById(R.id.change_class_text);
        changeClass = findViewById(R.id.change_class_button);

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
                                Toast.makeText(ChangeClassStudent.this, "Class was changed successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ChangeClassStudent.this, StudentActivity.class));
                            }
                            else{
                                Toast.makeText(ChangeClassStudent.this, "The class was not found.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) { }
                    });
                }
            }
        });
    }

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
