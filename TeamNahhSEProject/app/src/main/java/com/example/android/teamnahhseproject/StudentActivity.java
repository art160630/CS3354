package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import android.widget.Toast;

import java.util.Date;

// QR Scanner code was created with the ZXing Android application, created by JourneyApps:
//        https://github.com/journeyapps/zxing-android-embedded

public class StudentActivity extends AppCompatActivity {

    private Button returnBack;
    private Button attendanceHistory;
    private Button attend;
    String dateString;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference("code");
    private DatabaseReference reference2 = database.getReference("student_users/"+auth.getUid()+"Classes/CS_3345_003");

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

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(StudentActivity.this, "Scanning Error", Toast.LENGTH_LONG).show();
            } else {
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (result.getContents() == dataSnapshot.getValue(String.class)) {
                            Date date = new Date();
                            reference2.child(date.toString()).setValue("present");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
