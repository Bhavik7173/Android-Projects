package com.gi.millionapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {

    public QuestionsRepository mRepository;

    public LiveData<List<Questions>> mAllQuestions;

    public QuestionsViewModel(Application application) {
        super(application);

        mRepository = new QuestionsRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();
    }

    LiveData<List<Questions>> getAllQuestions(){
        return mAllQuestions;
    }

}
