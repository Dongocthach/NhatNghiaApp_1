package com.example.nhatnghia_app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Models.ThanhVien;
import com.example.nhatnghia_app.Adapter.ThanhVienAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class Fm_QuanLyThanhVien extends Fragment {
    private RecyclerView recyclerView;
    private ThanhVienAdapter thanhVienAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    private androidx.appcompat.widget.SearchView searchView;
    private List<ThanhVien> mListThanhVien;

    private FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fm__quan_ly_thanh_vien, container, false);

        recyclerView = v.findViewById(R.id.recycle_view_thanhvien);
        mListThanhVien = new ArrayList<>();
        getListBookFromRealtimeDatabase();
        thanhVienAdapter = new ThanhVienAdapter(mListThanhVien, new ThanhVienAdapter.IClickListener() {
            @Override
            public void onClickUpdateItemTV(ThanhVien tv) {
                onClickUpdateThanhVien(tv);

            }

            @Override
            public void onClickDeleteItem(ThanhVien tv) {
                onClickDeleteData(tv);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(thanhVienAdapter);

        floatingActionButton = v.findViewById(R.id.tv_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAddThanhVien();
            }
        });

        SearchManager searchManager = (SearchManager) this.getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = v.findViewById(R.id.thanhvien_searchview);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                thanhVienAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                thanhVienAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return v;
    }
    private void onClickAddThanhVien(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View vi = inflater.inflate(R.layout.la_dialog_add_thanhvien, null);
        builder.setView(vi);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        EditText ed1,ed2,ed3,ed4;
        Button btn1,btn2;

        ed1 = vi.findViewById(R.id.dialog_thanhvien_ed1);
        ed2 = vi.findViewById(R.id.dialog_thanhvien_ed2);
        ed3 = vi.findViewById(R.id.dialog_thanhvien_ed3);
        ed4 = vi.findViewById(R.id.dialog_thanhvien_ed4);

        btn1 = vi.findViewById(R.id.dialog_thanhvien_btn1);
        btn2 = vi.findViewById(R.id.dialog_thanhvien_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1= ed1.getText().toString().trim();
                String str2= ed2.getText().toString().trim();
                String str3= ed3.getText().toString().trim();
                String str4= ed4.getText().toString().trim();
                ThanhVien thanhVien1  = new ThanhVien(str1,str2,str3,str4);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("MemBers");

                        String pathObject = String.valueOf(thanhVien1.getId());
                        myRef.child(pathObject).setValue(thanhVien1, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(),"Them Thanh Vien Moi Thanh Cong !", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });



    }

    private void onClickUpdateThanhVien(ThanhVien tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View vi = inflater.inflate(R.layout.la_dialog_add_thanhvien, null);
        builder.setView(vi);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView textView;
        EditText ed1,ed2,ed3,ed4;
        Button btn1,btn2;

        textView = vi.findViewById(R.id.dialog_thanhvien_edit_title);
        textView.setText("CAP NHAP THANH VIEN");
        ed1 = vi.findViewById(R.id.dialog_thanhvien_ed1);
        ed2 = vi.findViewById(R.id.dialog_thanhvien_ed2);
        ed3 = vi.findViewById(R.id.dialog_thanhvien_ed3);
        ed4 = vi.findViewById(R.id.dialog_thanhvien_ed4);

        ed1.setText(tv.getId());
        ed2.setText(tv.getHoten());
        ed3.setText(tv.getNgaysinh());
        ed4.setText(tv.getSdt());

        btn1 = vi.findViewById(R.id.dialog_thanhvien_btn1);
        btn2 = vi.findViewById(R.id.dialog_thanhvien_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1= ed1.getText().toString().trim();
                String str2= ed2.getText().toString().trim();
                String str3= ed3.getText().toString().trim();
                String str4= ed4.getText().toString().trim();
                ThanhVien thanhVien1  = new ThanhVien(str1,str2,str3,str4);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("MemBers");

                        String pathObject = String.valueOf(thanhVien1.getId());
                        myRef.child(String.valueOf(tv.getId())).updateChildren(tv.toMap(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(), "Cap Nhap Thanh VIen Thanh COng", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });



    }

    private void onClickDeleteData(ThanhVien tv) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.app_name))
                .setMessage("chac chan xoa Thanh Vien")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("MemBers");

                        myRef.child(String.valueOf(tv.getId())).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(getActivity(), "delete success", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }
    private void getListBookFromRealtimeDatabase() {
        DatabaseReference myRef = database.getReference("MemBers");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ThanhVien tv = snapshot.getValue(ThanhVien.class);
                if (tv != null) {
                    mListThanhVien.add(tv);
                    thanhVienAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ThanhVien tv = snapshot.getValue(ThanhVien.class);
                if (mListThanhVien == null || mListThanhVien.isEmpty()) {
                    return;
                }
                for (int i = 0; i < mListThanhVien.size(); i++) {
                    if (tv.getId() == mListThanhVien.get(i).getId()) {
                        mListThanhVien.set(i, tv);
                        break;
                    }
                }
                thanhVienAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                ThanhVien tv = snapshot.getValue(ThanhVien.class);
                if (mListThanhVien == null || mListThanhVien.isEmpty()) {
                    return;
                }
                for (int i = 0; i < mListThanhVien.size(); i++) {
                    if (tv.getId() == mListThanhVien.get(i).getId()) {
                        mListThanhVien.remove(mListThanhVien.get(i));
                        break;
                    }
                }
                thanhVienAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}