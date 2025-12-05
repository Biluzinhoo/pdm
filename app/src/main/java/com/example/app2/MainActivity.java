package com.example.app2;

import android.annotation.SuppressLint;
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

    Button b;

    ListView listView;

    @SuppressLint("Range")
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


        listView = findViewById(R.id.listView2);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo VARCHAR, texto TEXT)");
        ContentValues cv = new ContentValues();


        b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.name);
            String texto = editText.getText().toString();
            cv.put("texto", "Texto da nota");
            cv.put("titulo", texto);
            db.insert("notas",null, cv);
            carregarListagem();
            //Toast.makeText("Nota salva com sucesso!", Toast.LENGTH_SHORT).show();
        });
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, ExibeItem.class);

            System.out.print(position);
            Cursor cursor = db.rawQuery("SELECT * FROM notas where titulo = \""+parent.getItemAtPosition(position)+"\"", null);
            cursor.moveToFirst();

            Bundle bundle = new Bundle();
            bundle.putString("titulo",cursor.getString(cursor.getColumnIndex("titulo" + "")));
            bundle.putString("texto",cursor.getString(cursor.getColumnIndex("texto" + "")));

            intent.putExtras(bundle);

            startActivity(intent);
        });

        carregarListagem();
    }

    public void carregarListagem() {
        ArrayList<String> titulos = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            @SuppressLint("Range") String titulo = cursor.getString(cursor.getColumnIndex("titulo" +
                    ""));
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