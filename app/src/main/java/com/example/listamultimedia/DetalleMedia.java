package com.example.listamultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.webkit.WebSettings;
import android.webkit.WebView;
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


        titulo = findViewById(R.id.tituloDetalle);
        descripcion = findViewById(R.id.descripcionDetalle);
        webView = findViewById(R.id.webView);
        videoView = findViewById(R.id.videoView);
        botonAudio = findViewById(R.id.botonAudio);
        botonVolver = findViewById(R.id.botonVolver);


        ModeloItem item = (ModeloItem) getIntent().getSerializableExtra("itemMedia");

        if (item != null) {
            titulo.setText(item.getTitulo());
            descripcion.setText(item.getDescripcion());


            webView.setVisibility(View.GONE);
            videoView.setVisibility(View.GONE);
            botonAudio.setVisibility(View.GONE);

            if (item.getTipo() == 0) {
                webView.setVisibility(View.VISIBLE);
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);
                webView.loadUrl(item.getUrl());
            } else if (item.getTipo() == 1) {
                mediaPlayer = MediaPlayer.create(this, Uri.parse(item.getUrl()));
                botonAudio.setVisibility(View.VISIBLE);
                botonAudio.setOnClickListener(v -> mediaPlayer.start());
            } else if (item.getTipo() == 2) {
                videoView.setVideoURI(Uri.parse(item.getUrl()));
                videoView.setVisibility(View.VISIBLE);
                videoView.start();
            }
        }


        botonVolver.setOnClickListener(v -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}
