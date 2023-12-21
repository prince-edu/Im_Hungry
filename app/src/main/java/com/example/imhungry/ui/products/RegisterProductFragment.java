package com.example.imhungry.ui.products;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.FragmentRegisterProductBinding;

import java.io.IOException;
import java.time.LocalTime;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RegisterProductFragment extends Fragment {
    private FragmentRegisterProductBinding binding;
    private Retrofit retrofit;
    private String fotoProducto;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterProductViewModel productsViewModel =
                new ViewModelProvider(this).get(RegisterProductViewModel.class);

        binding = FragmentRegisterProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textViewTitulo2;
        productsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        Button buttonRegistrar = binding.buttonRegisterProduct;
        Button buttonFotoProducto = binding.buttonProductPicture;
        CheckBox checkBoxFei = binding.checkFei;
        CheckBox checkBoxEconomia = binding.checkEconomia;

        buttonRegistrar.setOnClickListener(v -> {

            EditText editTextId = binding.editTextIdProducto;
            EditText editTextNombre = binding.editTextNombreProducto;
            EditText editTextDescripcion = binding.editTextDescripcion;
            EditText editTextCantidad = binding.editTextCantidad;
            EditText editTextHoraInicial = binding.editTextHoraInicial;
            EditText editTextHoraFinal = binding.editTextHoraFinal;
            EditText editTextPrecio = binding.editTextPrecio;

            String nombre = editTextNombre.getText().toString();
            String descripcion = editTextDescripcion.getText().toString();
            String cantidad = editTextCantidad.getText().toString();
            String horaInicial = editTextHoraInicial.getText().toString();
            String horaFinal = editTextHoraFinal.getText().toString();
            String precio = editTextPrecio.getText().toString();
            String id = editTextId.getText().toString();
            String puntoEncuentro = new String();

            if(checkBoxEconomia.isChecked() && checkBoxFei.isChecked()){
                puntoEncuentro = "Escaleras de Economía\n" + "Escaleras FEI";
            }else if(checkBoxFei.isChecked()){
                puntoEncuentro = "Escaleras FEI";
            }else{
                puntoEncuentro = "Escaleras Economia";
            }

            if(!validarNumeroEntero(id)){
                mostrarToast("No has ingresado un ID o contiene caracteres inválidos");
            }else if(!validarNombre(nombre)){
                mostrarToast("No has ingresado un nombre o contiene caracteres inválidos");
            }else if(!validarVacio(descripcion)){
                mostrarToast("No has ingresado una descripción");
            }else if(!validarNumeroEntero(cantidad)){
                mostrarToast("No has ingresado la cantidad o contiene caracteres inválidos");
            }else if(!validarHora(horaInicial)){
                mostrarToast("No has ingresado una hora inicial o no tiene el formato correcto");
            }else if(!validarHora(horaFinal)){
                mostrarToast("No has ingresado una hora final o no tiene el formato correcto");
            }else if(!validarNumeroDecimal(precio)){
                mostrarToast("No has agregado un precio");
            }else if(TextUtils.isEmpty(fotoProducto)){
                mostrarToast("No has ingresado una foto de tu producto");
            }else if(!checkBoxEconomia.isChecked() && !checkBoxFei.isChecked()){
                mostrarToast("Debes elegir al menos un punto de encuentro");
            }else{
                int cantidadConvetida = Integer.parseInt(cantidad);
                int ID = Integer.parseInt(id);
                Double precioConvertido = Double.parseDouble(precio);
                Producto producto = new Producto(ID, nombre, descripcion, cantidadConvetida, horaInicial, horaFinal,
                        puntoEncuentro, precioConvertido, "Disponible", fotoProducto);
                registrarProducto(producto);
            }

        });

        ActivityResultLauncher<Intent> launcherFotoProducto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri imagenSeleccionadaUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), imagenSeleccionadaUri);
                                fotoProducto = bitmap.toString();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

        buttonFotoProducto.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcherFotoProducto.launch(intent);

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void registrarProducto (Producto producto){

        ApiService apiService = retrofit.create(ApiService.class);
        Call<Producto> call = apiService.productoCreate(producto);
        call.enqueue(new Callback<Producto>() {
            @Override
            public void onResponse(Call<Producto> call, Response<Producto> response) {
                if(response.isSuccessful()){
                    mostrarToast("Registro éxitoso");
                    getFragmentManager().popBackStack();
                }else{
                    mostrarToast("Ha ocurrido un error con la DB, inténtelo de nuevo más tarde");
                }
            }

            @Override
            public void onFailure(Call<Producto> call, Throwable t) {
                mostrarToast("Ha ocurrido un error, inténtelo de nuevo más tarde");
            }
        });
    }

    public void mostrarToast(String mensaje){
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
    public boolean validarNombre(String nombre) {
        return nombre != null && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$");
    }
    public boolean validarVacio(String cadena) {
        return cadena != null;
    }
    public boolean validarNumeroDecimal(String numero) {
        return numero != null && numero.matches("^\\d*\\.?\\d+$");
    }
    public boolean validarNumeroEntero(String numero) {
        return numero != null && numero.matches("\\d+");
    }
    public boolean validarHora(String hora){
        String regex = "^([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$";
        return (hora != null && hora.matches(regex));
    }
    public boolean validarID(String id) {
        return id != null && id.matches("[a-zA-Z0-9]+");
    }



}
