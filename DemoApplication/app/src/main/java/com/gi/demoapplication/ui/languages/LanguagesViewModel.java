package com.gi.demoapplication.ui.languages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LanguagesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LanguagesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Languages fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}