package com.example.listamultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;


public class DetalleMedia extends AppCompatActivity {
    private TextView titulo, descripcion;
    private WebView webView;
    private VideoView videoView;
    private Button botonAudio, botonVolver;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles_media);

        // Enlazamos las vistas con sus respectivos IDs
        titulo = findViewById(R.id.tituloDetalle);
        descripcion = findViewById(R.id.descripcionDetalle);
        webView = findViewById(R.id.webView);
        videoView = findViewById(R.id.videoView);
        botonAudio = findViewById(R.id.botonAudio);
        botonVolver = findViewById(R.id.botonVolver);

        // Recibimos el objeto que se envió desde la lista
        ModeloItem item = (ModeloItem) getIntent().getSerializableExtra("itemMedia");

        if (item != null) {
            titulo.setText(item.getTitulo());
            descripcion.setText(item.getDescripcion());

            // Ocultamos todos los elementos por defecto
            webView.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            botonAudio.setVisibility(View.GONE);

            // Dependiendo del tipo de contenido, mostramos lo que corresponde
            if (item.getTipo() == 0) {
                // Si es un enlace, lo mostramos en un WebView
                webView.setVisibility(View.VISIBLE);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl(item.getUrl());
            } else if (item.getTipo() == 1) {
                // Si es audio, preparamos un MediaPlayer y mostramos el botón de reproducir
                mediaPlayer = MediaPlayer.create(this, Uri.parse(item.getUrl()));
                botonAudio.setVisibility(View.VISIBLE);
                botonAudio.setOnClickListener(v -> mediaPlayer.start());
            } else if (item.getTipo() == 2) {
                // Si es video, lo cargamos en un VideoView y lo reproducimos
                videoView.setVideoURI(Uri.parse(item.getUrl()));
                MediaController mediaController = new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.setVisibility(View.VISIBLE);
                videoView.start();
            }
        }

        // Botón para volver a la pantalla anterior
        botonVolver.setOnClickListener(v -> finish());
    }

    // Liberamos el reproductor de audio cuando la actividad se destruye
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
