package com.example.nhatnghia_app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nhatnghia_app.MainActivity;
import com.example.nhatnghia_app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.io.IOException;


public class Fm_CapNhapTaiKhoan extends Fragment {

    private View mView;
    private ImageView imgAvatar1;
    private EditText editText1,editText2;
    private Button button;
    private Uri imageUri;

    public static  final int MY_REQUEST_CODE = 10;
    private MainActivity mmainActivity ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mmainActivity = (MainActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_fm__cap_nhap_tai_khoan, container, false);
        imgAvatar1 = mView.findViewById(R.id.profile_img);
        editText1 = mView.findViewById(R.id.profile_fullname);
        editText2 = mView.findViewById(R.id.profile_email);
        button = mView.findViewById(R.id.profile_btn1);
        setUserInformation();
        initListener();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUpdateProfile();

            }
        });
        return mView;
    }
    private void setUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }
        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();

        editText1.setText(name);
        editText2.setText(email);
//        Glide.with(this).load(photoUrl).error(R.drawable.adminicon).into(imgAvatar1);
    }
    private void initListener(){
        imgAvatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReQuestPermission();
                selectImage();
            }
        });
    }

    private void onClickReQuestPermission() {
        if(mmainActivity == null){
            return;
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mmainActivity.openGallery();
            return;
        }

        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            mmainActivity.openGallery();
        }else{
            String [] permisstions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permisstions, MY_REQUEST_CODE);
        }
    }



    public void onClickUpdateProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }
        String strFullName = editText1.getText().toString().trim();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(imageUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),"User profile updated", Toast.LENGTH_SHORT).show();
                            mmainActivity.showUserInformation();
                        }
                    }
                });
    }
    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && data != null && data.getData() != null){

            imageUri = data.getData();

            imgAvatar1.setImageURI(imageUri);


        }
    }
}