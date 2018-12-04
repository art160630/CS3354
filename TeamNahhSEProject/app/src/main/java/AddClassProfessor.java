import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.android.teamnahhseproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClassProfessor extends AppCompatActivity {
    private EditText classInfo;
    private EditText semesterInfo;
    String name, s1, s2, s3;
    FirebaseDatabase database;
    DatabaseReference reference1, reference2, reference3;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        classInfo = findViewById(R.id.class_text);
        semesterInfo = findViewById(R.id.semester_text);
        database = FirebaseDatabase.getInstance();


    }
}
