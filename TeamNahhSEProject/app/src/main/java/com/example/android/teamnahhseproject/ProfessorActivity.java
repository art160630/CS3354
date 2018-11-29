package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfessorActivity extends AppCompatActivity {
    private Button attendanceHistory, qrGenerator, classes;
    private TextView currentClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        attendanceHistory = (Button) findViewById(R.id.attendance_history);
        qrGenerator = (Button) findViewById(R.id.generate_qr);
        classes = (Button) findViewById(R.id.choose_class);
        currentClass = (TextView) findViewById(R.id.current_class);

        attendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
