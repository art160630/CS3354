package com.example.android.teamnahhseproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import java.util.Random;

public class ProfessorActivity extends AppCompatActivity {
    private Button attendanceHistory, qrGenerator, classes;
    private TextView currentClass;
    private ImageView imageView;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    Bitmap bitmap;
    public static final int qrCodeWidth = 500;
    final int minRand = 1000;
    final int maxRand = 9000;
    int random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        attendanceHistory = (Button) findViewById(R.id.attendance_history);
        qrGenerator = (Button) findViewById(R.id.generate_qr);
        classes = (Button) findViewById(R.id.choose_class);
        currentClass = (TextView) findViewById(R.id.current_class);
        imageView = (ImageView) findViewById(R.id.imageView);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("code");

        attendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Random r = new Random();
                    random = r.nextInt(maxRand - minRand) + minRand;
                    reference.setValue(Integer.toString(random));

                    bitmap = TextToImageEncode(Integer.toString(random));

                    imageView.setImageBitmap(bitmap);
                    imageView.bringToFront();
                    imageView.setVisibility(View.VISIBLE);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

//This code was created by Juned Mughal at AndroidStudios.com:
//    https://www.android-examples.com/generate-qr-code-in-android-using-zxing-library-in-android-studio/
    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    qrCodeWidth, qrCodeWidth, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
