package com.example.imhungry.ui.logout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.imhungry.R;
import com.example.imhungry.databinding.FragmentHomeBinding;
import com.example.imhungry.databinding.FragmentLogOutBinding;
import com.example.imhungry.gui.LogInActivity;
import com.example.imhungry.ui.home.HomeViewModel;

public class LogOutFragment extends Fragment {

    private FragmentLogOutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LogOutViewModel logOutViewModel =
                new ViewModelProvider(this).get(LogOutViewModel.class);

        binding = FragmentLogOutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mostrarAlertaOpciones();

        final TextView textView = binding.textViewLogout;
        logOutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void mostrarAlertaOpciones(){

        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Confirmar")
                .setMessage("¿Deseas cerrar la sesión actual?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                getFragmentManager().popBackStack();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}