package com.gi.demoapplication.ui.procenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProCenterViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProCenterViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pro Center fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}