package com.example.androidproject.firebase;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class ImageHelper {

    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageRef = storage.getReference();

    public static Uri uri;

    public static void uploadImage() {
        if (uri != null) {
            StorageReference riversRef = storageRef.child("images/" + Objects.requireNonNull(uri.getLastPathSegment()));
            riversRef.putFile(uri);
        }
    }
    //get the image from firebase
    public static void downloadImage() {
        if(uri == null)
            return;
        StorageReference riversRef = storageRef.child("images/" + Objects.requireNonNull(uri.getLastPathSegment()));
        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }



}
