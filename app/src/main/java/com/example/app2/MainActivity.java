package com.example.app2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editTextMin, editTextMax;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button2);
        editTextMax = findViewById(R.id.edMax);
        editTextMin = findViewById(R.id.edMin);
        tv = findViewById(R.id.textView2);


        button.setOnClickListener(v -> {
            Random r = new Random();
            int min,max;
            min= Integer.parseInt(editTextMin.getText().toString());
            max= Integer.parseInt(editTextMax.getText().toString());
            int delta = max-min;
            int sortiado = r.nextInt(delta)+min;
            tv.setText(Integer.toString(sortiado));
        });
    }
}