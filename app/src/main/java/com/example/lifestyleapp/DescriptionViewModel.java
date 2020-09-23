package com.example.lifestyleapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class DescriptionViewModel extends ViewModel {
    private DatabaseCustomFunctions db = new DatabaseCustomFunctions();

    private MutableLiveData<String> file = new MutableLiveData<String>();
    private MutableLiveData<String> mText;

    public void setFile(String file) {
        this.file.setValue(file);
    }

    public LiveData<String> getText() {
        if (mText == null) {
            mText = new MutableLiveData<>();
            mText.setValue(db.getRecipeDescription(file.toString()));
            return mText;
        } else {
            return mText;
        }
    }
}
