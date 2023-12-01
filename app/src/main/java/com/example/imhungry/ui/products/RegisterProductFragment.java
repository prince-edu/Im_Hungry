package com.example.imhungry.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imhungry.databinding.FragmentRegisterProductBinding;

public class RegisterProductFragment extends Fragment {
    private FragmentRegisterProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegisterProductViewModel productsViewModel =
                new ViewModelProvider(this).get(RegisterProductViewModel.class);

        binding = FragmentRegisterProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewCantidad;
        productsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
