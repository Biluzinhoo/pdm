package com.example.app2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PlanetaController planetaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        planetaController = new PlanetaController();

        PlanetaAdapter adapter = new PlanetaAdapter(this,
                R.layout.item_lista,
                planetaController.getPlaneta());

        listView.setAdapter(adapter);
    }
}
