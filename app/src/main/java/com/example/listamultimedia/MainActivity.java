package com.example.listamultimedia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private  Adaptador adaptador;
    private List<ModeloItem> listaItems;

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
        listView = (ListView) findViewById(R.id.listView);
        listaItems = new ArrayList<>();
        cargarDatos();

        adaptador = new Adaptador(this,listaItems);
        listView.setAdapter(adaptador);
    }
    private void cargarDatos(){
        listaItems.add(new ModeloItem("Instant Gaming", "Web de compra de juegos",0,"https://www.instant-gaming.com/es/"));
        listaItems.add(new ModeloItem("PC Componentes", "Web de compra de Informatica",0,"https://www.pccomponentes.com"));
        listaItems.add(new ModeloItem("Audio 1", "Sonido de reloj",1,"android.resource://com.example.listamultimedia/raw/audio1"));
        listaItems.add(new ModeloItem("Audio 2", "Mujer deletreando",1,"android.resource://com.example.listamultimedia/raw/audio2"));
        listaItems.add(new ModeloItem("Video 1", "Cambiando tv",2,"android.resource://com.example.listamultimedia/raw/video1"));
        listaItems.add(new ModeloItem("Video 2", "Luces de neon",2,"android.resource://com.example.listamultimedia/raw/video2"));

    }
}