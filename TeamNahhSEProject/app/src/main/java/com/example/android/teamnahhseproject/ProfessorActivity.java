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

/**
 * The following activity generates the professor's page and contains the
 * ability for the professor to access the abilities that professors are given
 * in the app.
 *
 * The page allows the professor user to generate a QR code, access their history
 * and view a myriad of other things.
 *
 * author : Alisha Tapiawala
 * generate: Nov.25th.2018
 * version : 1.4
 */

public class ProfessorActivity extends AppCompatActivity {
    private Button attendanceHistory, qrGenerator, classes, logOut;
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
        logOut = (Button) findViewById(R.id.log_out);
        currentClass = (TextView) findViewById(R.id.current_class);
        imageView = (ImageView) findViewById(R.id.imageView);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("code");

        /**
         * if attendance history button is clicked then show the attedance of the students in the
         * class chosen.
         */
        attendanceHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * qrGenerator button is clicked, then generate a random code in and display to user the
         * generated code.
         */
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

        /**
         * if classes button is clicked then open up the classes that the professor is teaching
         */
        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * if logout button is pressed then log out the professor, and bring them back to
         * login/user creation screen
         */
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfessorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


    /**
     * The code below is used to generate the QR code and all the aspects that are associated
     * with that.
     */
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
