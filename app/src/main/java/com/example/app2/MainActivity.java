package com.example.app2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button b;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = openOrCreateDatabase("app_database", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo VARCHAR, " +
                "texto TEXT)");

        b = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        listView = findViewById(R.id.listView);

        b.setOnClickListener(v -> {
            String texto = editText.getText().toString().trim();

            if (texto.isEmpty()) {
                Toast.makeText(this, "Digite algo antes de salvar!", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues cv = new ContentValues();
            cv.put("titulo", "Nota do Usuário");
            cv.put("texto", texto);
            db.insert("notas", null, cv);

            Toast.makeText(this, "Nota salva!", Toast.LENGTH_SHORT).show();

            editText.setText("");
            carregarListagem();
        });

        carregarListagem();
    }

    public void carregarListagem() {
        ArrayList<String> titulos = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT titulo, texto FROM notas", null);

        if (cursor.moveToFirst()) {
            int tituloIndex = cursor.getColumnIndex("titulo");
            int textoIndex = cursor.getColumnIndex("texto");

            do {
                String titulo = cursor.getString(tituloIndex);
                String texto = cursor.getString(textoIndex);
                titulos.add(titulo + ": " + texto);
            } while (cursor.moveToNext());
        }

        cursor.close();

        ArrayAdapter<String> titulosAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                titulos
        );

        listView.setAdapter(titulosAdapter);
    }
}
