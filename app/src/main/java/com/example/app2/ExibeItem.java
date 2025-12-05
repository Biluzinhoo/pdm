package com.example.app2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ExibeItem extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exibe_item);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        ContentValues cv = new ContentValues();

        TextView titulo = findViewById(R.id.dado);
        TextView texto = findViewById(R.id.campoTexto);
        TextView id = findViewById(R.id.idDado);
        Button save = findViewById(R.id.salvar);
        Long idB = bundle.getLong("id");

        String tituloA = bundle.getString("titulo", "Default String");
        String textoA = bundle.getString("texto", "Default String");
        titulo.setText(tituloA);
        texto.setText(textoA);
        id.setText(idB.toString());

        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        save.setOnClickListener(b -> {
            cv.put("texto", texto.getText().toString());
            db.update("notas", cv, "titulo = ?", new String[]{tituloA});
            db.close();
        });
    }
}