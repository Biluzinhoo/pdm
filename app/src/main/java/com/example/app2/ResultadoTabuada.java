package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultadoTabuada extends AppCompatActivity {

    Button b;
    TextView tvResultado;

    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.tabuada);
        tvResultado = findViewById(R.id.textView);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        tvResultado.setText(Double.toString(num));

        if (num == 1){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 2){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 3){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 4){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 5){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 6){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 7){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 8){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 9){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }
        if (num == 10){
            for (int j = 0; j < 10; j++) {
                int resul = num*j;
                System.out.println(num+"x"+j+"="+resul);
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}