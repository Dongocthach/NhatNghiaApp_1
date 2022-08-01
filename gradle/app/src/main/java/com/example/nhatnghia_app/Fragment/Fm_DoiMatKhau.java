package com.example.nhatnghia_app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Fm_DoiMatKhau extends Fragment {

    private EditText ed1,ed2;
    private Button btn;
    private ProgressDialog progressDialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fm__doi_mat_khau, container, false);
        ed1 = v.findViewById(R.id.changepassword_edit_1);
        ed2 = v.findViewById(R.id.changepassword_edit_2);

        btn = v.findViewById(R.id.changepassword_btn);
        progressDialog = new ProgressDialog(getActivity());

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(ed1.getText().toString().equals(ed2.getText().toString())){
                    String newPassword = ed1.getText().toString();
                    progressDialog.show();
                    user.updatePassword(newPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getActivity(),"cap nhap thanh cong",Toast.LENGTH_SHORT).show();
                                        ed1.setText("");
                                        ed2.setText("");
                                        progressDialog.dismiss();
                                    }
                                }
                            });

                }else{
                    Toast.makeText(getActivity(),"nhap khong giong",Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
        return v;
    }
}