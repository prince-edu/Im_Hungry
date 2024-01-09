package com.example.imhungry.ui.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imhungry.Domain.Estudiante;
import com.example.imhungry.Domain.EstudianteResponse;
import com.example.imhungry.HttpRequest.API;
import com.example.imhungry.HttpRequest.ApiService;
import com.example.imhungry.R;
import com.example.imhungry.databinding.FragmentMyProfileBinding;
import com.example.imhungry.gui.LogInActivity;
import com.example.imhungry.gui.MainMenuVendedorActivity;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MyProfileFragment extends Fragment {

    private FragmentMyProfileBinding binding;
    Retrofit retrofit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        String matricula = LogInActivity.estudianteLogin.getMatricula();
        buscarEstudiante(matricula);

        final TextView textView = binding.textView2;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void buscarEstudiante(String matricula){

        retrofit = new Retrofit.Builder()
                .baseUrl(API.getUrl())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<EstudianteResponse> call = apiService.estudiantesGetById(matricula);
        call.enqueue(new Callback<EstudianteResponse>() {
            @Override
            public void onResponse(Call<EstudianteResponse> call, Response<EstudianteResponse> response) {
                assert response.body() != null;
                if((response.body().getEstudiante() == null)){
                    mostrarToast("Ha ocurrido un error, inténtalo de nuevo.");
                }else {
                    mostrarDatos(response.body().getEstudiante());
                }
            }
            @Override
            public void onFailure(Call<EstudianteResponse> call, Throwable t) {
                mostrarToast("Ha ocurrido un error");
            }
        });
    }
    public void mostrarToast(String mensaje){
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    public void mostrarDatos(Estudiante estudiante){
        TextView textViewMatricula = binding.perfilMatricula;
        TextView textViewNombre = binding.perfilNombre;
        TextView textViewCorreo = binding.perfilCorreoInstitucional;
        TextView textViewTipoPerfil = binding.perfilTipoPerfil;
        ImageView imageViewPerfil = binding.imageViewPerfil;
        ImageView imageViewCredencial = binding.imageViewCredencial;

        textViewMatricula.setText(estudiante.getMatricula());
        textViewNombre.setText(estudiante.getNombre() + " " + estudiante.getApellidoPaterno() +" " + estudiante.getApellidoMaterno());
        textViewCorreo.setText(estudiante.getCorreoInstitucional());
        textViewTipoPerfil.setText("Vendedor: " + estudiante.getTipoVendedor() + " / Comprador: " + estudiante.getTipoComprador());

        byte[] decodedString = Base64.decode(estudiante.getFotoCredencial(), Base64.DEFAULT);
        Bitmap bitmapCredencial = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageViewCredencial.setImageBitmap(bitmapCredencial);

        byte[] byteArray = Base64.decode(estudiante.getFotoPerfil(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageViewPerfil.setImageBitmap(bitmap);
    }


}
