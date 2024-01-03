package com.example.imhungry.ui.products;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.imhungry.Domain.Producto;
import com.example.imhungry.R;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Producto> datos;

    // Constructor para recibir los datos
    public ProductsAdapter(List<Producto> datos) {
        this.datos = datos;
    }

    // Método para crear nuevas vistas (invocado por el LayoutManager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_products, parent, false);
        return new ViewHolder(view);
    }

    // Método para reemplazar el contenido de una vista (invocado por el LayoutManager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Producto elemento = datos.get(position);
        // Asignar datos a las vistas en ViewHolder
        holder.bind(elemento);
    }

    // Método para obtener la cantidad de elementos
    @Override
    public int getItemCount() {
        return datos.size();
    }

    // Clase ViewHolder para mantener las vistas
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Define las vistas que quieres mostrar en cada elemento del RecyclerView
        private TextView textViewNombre = null;
        private TextView textViewPrecio = null;
        private ImageView imagen = null;
        private String imagenBit = new String();

        public ViewHolder(View itemView) {
            super(itemView);
            // Referenciar las vistas desde el layout del item
            textViewNombre = itemView.findViewById(R.id.recycler_nombre);
            textViewPrecio = itemView.findViewById(R.id.recycler_precio);
            imagen = itemView.findViewById(R.id.recycler_foto);
        }

        // Método para asignar datos a las vistas
        public void bind(Producto elemento) {

            Bitmap bitmap = convertirStringABitmap(elemento.getFoto());
            if (bitmap != null) {
               imagen.setImageBitmap(bitmap);
            } else {
                // Manejar el caso en que el bitmap es nulo, podría establecer una imagen de fallback
                //imagen.setImageResource(R.drawable.im_hungry_icon);
            }
            //textViewNombre.setText(elemento.getNombre());
          //  textViewPrecio.setText(elemento.getPrecio().toString());
        }

        private Bitmap convertirStringABitmap(String fotoString) {
            try {
                byte[] encodeByte = Base64.decode(fotoString, Base64.DEFAULT);
                return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }
    }
}
