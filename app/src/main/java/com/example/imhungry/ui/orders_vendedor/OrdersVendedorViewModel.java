package com.example.imhungry.ui.orders_vendedor;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrdersVendedorViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OrdersVendedorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is orders vendedor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
