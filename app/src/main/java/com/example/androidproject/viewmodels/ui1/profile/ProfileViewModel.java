package com.example.androidproject.viewmodels.ui1.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public ProfileViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is profile fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }

}
