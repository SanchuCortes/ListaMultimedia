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

    // Array con los íconos para cada tipo de elemento
    private static final int[] ICONOS = {
            R.drawable.icono_url,   // Ícono para enlaces web
            R.drawable.icono_musica,  // Ícono para audio
            R.drawable.icono_video   // Ícono para videos
    };

    // Constructor del adaptador
    public Adaptador(Context context, List<ModeloItem> listaModelo) {
        super(context, 0, listaModelo);
    }

    @Override
    public View getView(int posicion, View convertirView, ViewGroup parent) {
        // Si no hay una vista reutilizable, inflamos una nueva
        if (convertirView == null) {
            convertirView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Obtenemos el objeto de la lista en la posición actual
        ModeloItem item = getItem(posicion);
        TextView titulo = convertirView.findViewById(R.id.tituloMedia);
        TextView descripcion = convertirView.findViewById(R.id.detalleMedia);
        ImageView icono = convertirView.findViewById(R.id.icono);

        // Asignamos los valores de título y descripción
        titulo.setText(item.getTitulo());
        descripcion.setText(item.getDescripcion());

        // Seleccionamos el ícono según el tipo de elemento
        if (item.getTipo() >= 0 && item.getTipo() < ICONOS.length) {
            icono.setImageResource(ICONOS[item.getTipo()]);
        }

        // Cuando el usuario toca un elemento, abre la actividad de detalles
        convertirView.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), DetalleMedia.class);
            intent.putExtra("itemMedia", item); // Pasamos el objeto seleccionado
            getContext().startActivity(intent);
        });

        return convertirView;
    }
}
