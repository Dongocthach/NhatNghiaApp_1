package com.example.nhatnghia_app.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Sach;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fm_bgroup_AddBook extends Fragment {
    private Button btn1,btn2;
    private EditText ed1,ed2,ed3,ed4,ed5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fm_bgroupd__add_book, container, false);
        ed1 = view.findViewById(R.id.book_ed1);
        ed2 = view.findViewById(R.id.book_ed2);
        ed3 = view.findViewById(R.id.book_ed3);
        ed4 = view.findViewById(R.id.book_ed4);
        ed5 = view.findViewById(R.id.book_ed5);


        btn1 = view.findViewById(R.id.btn_pushdata);
        btn2 = view.findViewById(R.id.btn_clear);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensach = ed2.getText().toString().trim();
                String id = ed1.getText().toString().trim();
                String tentacgia = ed3.getText().toString().trim();
                String theloai = ed4.getText().toString().trim();
                String link = ed5.getText().toString().trim();
                Sach sach = new Sach(id,link,tensach,tentacgia,theloai);
                onClickAddUser(sach);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed1.setText("");ed2.setText("");ed3.setText("");ed4.setText("");ed5.setText("");
            }
        });
        return view;
    }
    private void onClickAddUser(Sach book){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books");

        String pathObject = String.valueOf(book.getID());
        myRef.child(pathObject).setValue(book, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(getActivity(),"add book success", Toast.LENGTH_SHORT).show();
            }
        });

    }
}