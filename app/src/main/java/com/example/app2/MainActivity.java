package com.example.app2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button b;

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
        db = openOrCreateDatabase("app_database", MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE notas (id integer PRIMARY KEY AUTOINCREMENT, " +
                "titulo varchar, texto text)");


        b = findViewById(R.id.button);
        b.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editTextText);
            String texto = editText.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("titulo","Nota do Usuario");
            cv.put("texto",texto);
            db.insert("notas", null,cv);
            Toast.makeText("Nota salva com sucesso!",Toast.LENGTH_SHORT).show();
        });
    }
}