package com.example.nhatnghia_app.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatnghia_app.BookAdapter2;
import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Sach;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Fm_bgroup_UpdateBook extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter2 bookAdapter2;

    private Button btn1;
    private EditText ed1;
    private List<Sach> mListBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fm_bgroup__update_book, container, false);
        btn1 = v.findViewById(R.id.book_btnsearch);
        ed1 = v.findViewById(R.id.search_edit);


        recyclerView = v.findViewById(R.id.update_book_rcv);
        mListBook = new ArrayList<>();
        getListBookFromRealtimeDatabase();
        bookAdapter2 = new BookAdapter2(mListBook, new BookAdapter2.IClickListener() {
            @Override
            public void onClickUpdateItem(Sach book) { openDialogUpdate(book); }

            @Override
            public void onClickDeleteItem(Sach book) {onClickDeleteData(book); }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(bookAdapter2);

        return v;
    }
    private void getListBookFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sach bk = snapshot.getValue(Sach.class);
                if(bk != null){
                    mListBook.add(bk);
                    bookAdapter2.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sach bk = snapshot.getValue(Sach.class);
                if(mListBook == null || mListBook.isEmpty()){
                    return;
                }
                for(int i = 0; i < mListBook.size();i++){
                    if(bk.getID() == mListBook.get(i).getID()){
                        mListBook.set(i,bk);
                        break;
                    }
                }
                bookAdapter2.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Sach book = snapshot.getValue(Sach.class);
                if(mListBook == null || mListBook.isEmpty()){
                    return;
                }
                for(int i = 0; i < mListBook.size();i++){
                    if(book.getID() == mListBook.get(i).getID()){
                        mListBook.remove(mListBook.get(i));
                        break;
                    }
                }
                bookAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void openDialogUpdate(Sach bk){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View vi = inflater.inflate(R.layout.book_update_dialog, null);
        builder.setView(vi);
        AlertDialog alertDialog = builder.create();
//        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_add_bg);
        alertDialog.show();

        Button dia_btn1,dia_btn2,dia_btn3;
        TextView dia_ed1;
        EditText dia_ed2,dia_ed3,dia_ed4,dia_ed5;
        ImageView imageView;
        imageView= vi.findViewById(R.id.image_dialog);
        dia_ed1 = vi.findViewById(R.id.book_ed1);
        dia_ed2 = vi.findViewById(R.id.book_ed2);
        dia_ed3 = vi.findViewById(R.id.book_ed3);
        dia_ed4 = vi.findViewById(R.id.book_ed4);
        dia_ed5 = vi.findViewById(R.id.book_ed5);

        dia_btn1 = vi.findViewById(R.id.update_btn);
        dia_btn2 = vi.findViewById(R.id.return_btn);
        dia_btn3 = vi.findViewById(R.id.dialog_fetchimage_btn);

        dia_ed1.setText("ID: "+bk.getID());
        dia_ed2.setText(bk.getTenSach());
        dia_ed3.setText(bk.getTenTacGia());
        dia_ed4.setText(bk.getTheLoai());
        dia_ed5.setText(bk.getImgsach());
        dia_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Books");
                String newname = dia_ed2.getText().toString().trim();
                String newtacgia = dia_ed3.getText().toString().trim();
                String newtheloai = dia_ed4.getText().toString().trim();
                String newimglink = dia_ed5.getText().toString().trim();

                bk.setTenSach(newname);
                bk.setTenTacGia(newtacgia);
                bk.setTheLoai(newtheloai);
                bk.setImgsach(newimglink);

                myRef.child(String.valueOf(bk.getID())).updateChildren(bk.toMap(), new DatabaseReference.CompletionListener(){
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Toast.makeText(getActivity(),"update success", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        dia_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dia_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fm_bgroup_ImageControl fm_bgroup_imageControl=new Fm_bgroup_ImageControl();
                fm_bgroup_imageControl.onClickFetchImage(dia_ed5.getText().toString(),imageView);
            }
        });

    }
    private void onClickDeleteData(Sach book){
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.app_name))
                .setMessage("chac chan xoa sach nay")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Books");

                        myRef.child(String.valueOf(book.getID())).removeValue(new DatabaseReference.CompletionListener()  {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(),"delete success", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

}