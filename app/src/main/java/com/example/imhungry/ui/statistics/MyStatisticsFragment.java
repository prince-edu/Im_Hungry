package com.example.imhungry.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.imhungry.databinding.FragmentMyStatisticsBinding;
import com.example.imhungry.ui.statistics.StatisticsViewModel;

public class MyStatisticsFragment extends Fragment {
    private FragmentMyStatisticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatisticsViewModel statisticsViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);

        binding = FragmentMyStatisticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStatisctics;
        statisticsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
