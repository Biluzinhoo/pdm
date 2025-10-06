package com.example.app2;

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


    ListView listView;
    Button buttonSalvar;
    EditText editText;
    ArrayList<String> nomes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        buttonSalvar=findViewById(R.id.button);
        editText = findViewById(R.id.editTextText3);
        nomes = new ArrayList<String>();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, nomes);
                listView.setAdapter(adapter);
        buttonSalvar.setOnClickListener(v -> {
            nomes.add(editText.getText().toString());
            adapter.notifyDataSetChanged();
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            nomes.remove(position);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}