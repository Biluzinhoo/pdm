package com.example.app2;

import android.content.ContentValues;
import android.content.Intent;
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
    Button button;
    EditText editText;
    ListView listView;

    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        listView = findViewById(R.id.listView);
        editText2 = findViewById(R.id.editTextText2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = openOrCreateDatabase("meu_banco.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas" +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, txt TEXT);");

        carregarListagem();

        button.setOnClickListener(v -> {
            String titulo = editText.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("titulo", titulo);
            db.insert("notas",null, cv);
            carregarListagem();
        });

        button.setOnClickListener(v -> {
            String texto = editText2.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("texto", texto);
            db.insert("notas",null, cv);
            carregarListagem();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String titulo = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ExibeItem.class);
            intent.putExtra("titulo",titulo);
            startActivity(intent);

        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String texto = (String) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ExibeItem.class);
            intent.putExtra("texto",texto);
            startActivity(intent);

        });




    }

    public void carregarListagem() {
        ArrayList<String> titulos = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            titulos.add(titulo);
            cursor.moveToNext();
        }

        ArrayAdapter<String> titulosAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                titulos
        );

        listView.setAdapter(titulosAdapter);
    }

}