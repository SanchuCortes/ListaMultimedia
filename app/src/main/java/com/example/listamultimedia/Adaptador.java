package com.example.listamultimedia;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class Adaptador extends ArrayAdapter<ModeloItem> {

    private static final int[] ICONOS = {
            R.drawable.icono_url,
            R.drawable.icono_musica,
            R.drawable.icono_video
    };

    public Adaptador(Context context, List<ModeloItem> listaModelo){
        super(context,0,listaModelo);
    }

    @Override
    public View getView(int posicion, View convertirView, ViewGroup parent){
        if(convertirView == null){
            convertirView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        ModeloItem item = getItem(posicion);
        TextView titulo = convertirView.findViewById(R.id.tituloMedia);
        TextView descripcion = convertirView.findViewById(R.id.detalleMedia);
        ImageView icono = convertirView.findViewById(R.id.icono);

        titulo.setText(item.getTitulo());
        descripcion.setText(item.getDescripcion());


        if (item.getTipo() >= 0 && item.getTipo() < ICONOS.length) {
            icono.setImageResource(ICONOS[item.getTipo()]);
        }


        convertirView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetalleMedia.class);
            intent.putExtra("itemMedia", item);
            getContext().startActivity(intent);
        });

        return convertirView;
    }
}
