package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.zxing.integration.android.IntentIntegrator;

public class StudentActivity extends AppCompatActivity {

    private Button returnBack;
    private Button attendanceHistory;
    private Button attend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        returnBack = (Button) findViewById(R.id.btnReturn);
        attendanceHistory = (Button) findViewById(R.id.attendance);
        //TODO: change these button names jfc
        attend = (Button) findViewById(R.id.button2);

        returnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        attendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AttendanceHistoryStudent.class);
                startActivity(intent);
            }
        });


        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(StudentActivity.this);
                integrator.setPrompt("Scan QR Code");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.initiateScan();
            }
        });
    }
}
