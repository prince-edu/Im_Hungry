package com.example.imhungry.ui.orders_vendedor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imhungry.databinding.FragmentMyOrdersVendedorBinding;

public class MyOrdersVendedorFragment extends Fragment {
    private FragmentMyOrdersVendedorBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrdersVendedorViewModel ordersVendedorViewModel =
                new ViewModelProvider(this).get(OrdersVendedorViewModel.class);

        binding = FragmentMyOrdersVendedorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textOrdersVendedor;
        ordersVendedorViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

