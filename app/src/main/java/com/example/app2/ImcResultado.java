package com.example.app2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ImcResultado extends AppCompatActivity {

    Button b;
    TextView tvResultado;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imc_resultado);
        tvResultado = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView2);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        Double peso = bundle.getDouble("peso");
        Double altura = bundle.getDouble("altura");
        Double imc = peso/(altura*altura);
        tvResultado.setText(Double.toString(imc));
        imageView.setImageResource(R.drawable.perfil);

        if (imc<18.9){imageView.setImageResource(R.drawable.abaixopeso);}
        if (imc>=18.9 && imc < 25 ){ imageView.setImageResource(R.drawable.normal);}
        if (imc>=25 && imc < 30 ){ imageView.setImageResource(R.drawable.sobrepeso);}
        if (imc>=25 && imc < 35 ){ imageView.setImageResource(R.drawable.obesidade1);}


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}