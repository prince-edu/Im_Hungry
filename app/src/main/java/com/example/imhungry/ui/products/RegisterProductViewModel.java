package com.example.imhungry.ui.products;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterProductViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RegisterProductViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Register product fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
