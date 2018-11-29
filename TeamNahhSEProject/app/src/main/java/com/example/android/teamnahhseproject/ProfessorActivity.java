package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class ProfessorActivity extends AppCompatActivity {
    private Button attendanceHistory, qrGenerator, classes;
    private TextView currentClass;
    Bitmap bitmap;
    public static final int qrCodeWidth = 500;
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
//                try {
//                    bitmap = TextToImageEncode(EditTextValue);
//
//                    imageView.setImageBitmap(bitmap);
//
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
            }
        });

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
