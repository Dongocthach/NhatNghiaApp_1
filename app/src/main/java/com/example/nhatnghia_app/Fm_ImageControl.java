package com.example.nhatnghia_app;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fm_ImageControl extends Fragment {
    ImageView imageView;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },100);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fm__image_control, container, false);


        Button btn1,btn2;

        imageView = v.findViewById(R.id.firebaseimage);
        btn1 = v.findViewById(R.id.selectImagebtn);
        btn2 = v.findViewById(R.id.uploadimagebtn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage(imageView);

            }
        });


        return v;
    }

    private void uploadImage(ImageView v) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();


//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.JAPAN);
//        Date now = new Date();
//        String fileName = formatter.format(now);
//        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);
//
//
//        storageReference.putFile(imageUri)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        v.setImageURI(null);
//                        Toast.makeText(getActivity(),"Successfully Uploaded",Toast.LENGTH_SHORT).show();
//                        if (progressDialog.isShowing())
//                            progressDialog.dismiss();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//
//                if (progressDialog.isShowing())
//                    progressDialog.dismiss();
//                Toast.makeText(getActivity(),"Failed to Upload",Toast.LENGTH_SHORT).show();
//
//
//            }
//        });


//      storageReference  = FirebaseStorage.getInstance().getReference("images/"+"2022_07_16_07_14_31"+".jpeg");
//      storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://nhatnghiaappteamwork.appspot.com/images/2022_07_16_07_14_31");
        storageReference = FirebaseStorage.getInstance().getReference().child("images/2022_07_16_07_14_31");

        try {
            File localfile = File.createTempFile("tempfile",".jpeg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            imageView.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Failed to retrieve", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


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
            imageView.setImageURI(imageUri);


        }
    }
}