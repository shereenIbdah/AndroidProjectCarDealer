package com.example.androidproject.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FavViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public FavViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is favorites fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }
}