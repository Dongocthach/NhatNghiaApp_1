package com.example.nhatnghia_app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatnghia_app.BookAdapter2;
import com.example.nhatnghia_app.BooksAdapter1;
import com.example.nhatnghia_app.PhieuMuon;
import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Sach;
import com.example.nhatnghia_app.Sachs;
import com.example.nhatnghia_app.ThanhVien;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;


public class Fm_PhieuMuon extends Fragment {
    private Set<Integer> SetMatt = new HashSet<Integer>();
    private FloatingActionButton fab;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List<Sachs> mListBooks;
    private RecyclerView recyclerView;
    private BooksAdapter1 booksAdapter1;

    private String day1;
    private String day2;

    private Fm_bgroup_UpdateBook fm_bgroup_updateBook = new Fm_bgroup_UpdateBook();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fm__phieu_muon, container, false);
        fab = v.findViewById(R.id.pm_fab);
        for (int i = 1; i < 10; i++) {
            SetMatt.add(i);
        }
        mListBooks= new ArrayList<>();
        getListBookFromRealtimeDatabase();
        booksAdapter1 = new BooksAdapter1(mListBooks, new BooksAdapter1.IClickListener() {
            @Override
            public void onClickUpdateItemSachs(Sachs book) {
                updatePM(book);

            }

            @Override
            public void onClickDeleteItemSachs(Sachs book) {

            }
        });
        recyclerView = v.findViewById(R.id.recycle_view_phieumuon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(booksAdapter1);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAddPm();

            }
        });

        return v;
    }
    private void dialogAddPm(){
        String currentDate = new SimpleDateFormat("d-M-yyyy", Locale.getDefault()).format(new Date());
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.la_dialog_add_pm, null);
        builder.setView(v);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button btn1,btn2;
        EditText editText1 = v.findViewById(R.id.dialog_phieumuon_ed1);
        EditText editText2 = v.findViewById(R.id.dialog_phieumuon_ed2);
        EditText editText3 = v.findViewById(R.id.dialog_phieumuon_ed3);
        btn1 = v.findViewById(R.id.dialog_add_pm_btn1);
        btn2 = v.findViewById(R.id.dialog_add_pm_btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String str1 = editText1.getText().toString();
                String str2 = editText2.getText().toString();
                String str3 = editText3.getText().toString();
                int value1,value2,value3;

                String err ="";
                boolean check = false;

                if(str1.isEmpty()){
                    editText1.setError("chua dien");
                    return;
                }else{
                    value1 = Integer.valueOf(str1);
                    if(SetMatt.contains(value1)){
                        check =true;
                    }else{
                        err = " sach";
                        check=false;
                    }
                }

                if(str2.isEmpty()){
                    editText2.setError("chua dien");
                    return;
                }else{
                    value2 = Integer.valueOf(str2);
                    if(SetMatt.contains(value2)){
                        check =true;
                    }else{
                        err = "thu thu";
                        check=false;
                    }
                }

                if(str3.isEmpty()){
                    editText3.setError("chua dien");
                    return;
                }else{
                    value3 = Integer.valueOf(str3);
                    if(SetMatt.contains(value3)){
                        check =true;
                    }else{
                        err = "thanh vien";
                        check=false;
                    }
                }

                if(check ==true){
                    final boolean[] alreadyExecuted = {false};
                    DatabaseReference myRef1 = database.getReference("Books");
                    DatabaseReference myRef2 = database.getReference("Bookss");

                    myRef1.child(str1).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                             Sach sach = snapshot.getValue(Sach.class);
                             Sachs sachs = new Sachs(Integer.valueOf(sach.getID()+1),currentDate, "","", new Sach(sach.getTenSach(), sach.getTheLoai(), sach.getID(), sach.getQuantity(), sach.getPrice()),
                                    new ThanhVien("645", "thach"),
                                    new PhieuMuon("1"));
                            String pathObject = String.valueOf(sach.getID()+1);
                            myRef2.child(pathObject).setValue(sachs, new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                    if(!alreadyExecuted[0]) {
                                        fm_bgroup_updateBook.onClickDeCreaseQuantity(sach);
                                        alreadyExecuted[0] = true;
                                    }

                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    alertDialog.dismiss();
                }else{
                    Toast.makeText(getContext(),"sai ma" +err, Toast.LENGTH_SHORT).show();
                }

            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");

                alertDialog.dismiss();
            }
        });

    }
    private void getListBookFromRealtimeDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bookss");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(mListBooks != null){
//                    mListBooks.clear();
//                }
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Sachs bk = dataSnapshot.getValue(Sachs.class);
//                    mListBooks.add(bk);
//                }
//                booksAdapter1.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(),"load data failed",Toast.LENGTH_SHORT).show();
//
//            }
//        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("TAG", "onChildAdded:" + snapshot.getKey());
                Sachs bk = snapshot.getValue(Sachs.class);
                if (bk != null) {
                    mListBooks.add(bk);
                    booksAdapter1.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Sachs bk = snapshot.getValue(Sachs.class);
                if (mListBooks == null || mListBooks.isEmpty()) {
                    return;
                }
                for (int i = 0; i < mListBooks.size(); i++) {
                    if (bk.getId() == mListBooks.get(i).getId()) {
                        mListBooks.set(i, bk);
                        break;
                    }
                }
                booksAdapter1.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Sachs book = snapshot.getValue(Sachs.class);
                if (mListBooks == null || mListBooks.isEmpty()) {
                    return;
                }
                for (int i = 0; i < mListBooks.size(); i++) {
                    if (book.getId() == mListBooks.get(i).getId()) {
                        mListBooks.remove(mListBooks.get(i));
                        break;
                    }
                }
                booksAdapter1.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void updatePM(Sachs sachs){

        String ngaymuon= sachs.getNgaymuon();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View vi = inflater.inflate(R.layout.la_dialog_update_pm, null);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                getActivity(), android.R.layout.simple_spinner_item, tinhtrangsach);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        Spinner sItems = (Spinner) vi.findViewById(R.id.dialog_update_pm_status);
//        sItems.setAdapter(adapter);
//
//        Toast.makeText(getActivity(),"test spn: " + sItems.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();

        builder.setView(vi);
        AlertDialog alertDialog = builder.create();



        TextView tv0,tv1;
        Button btn1,btn2,btn3;
        tv0 = vi.findViewById(R.id.dialog_update_pm_tv0);
        tv1 = vi.findViewById(R.id.dialog_update_pm_tv1);
        btn1 = vi.findViewById(R.id.dialog_update_pm_1);
        btn2 = vi.findViewById(R.id.dialog_update_pm_2);
        btn3 = vi.findViewById(R.id.dialog_update_pm_ngaytra);

        tv0.setText(ngaymuon);
        alertDialog.show();
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar lich = Calendar.getInstance();
                DatePickerDialog datePickerDialog= new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                day1 = i2+"-"+i1+"-"+"-"+i;
                                tv1.setText(day1);


                            }
                        }, lich.get(Calendar.YEAR), lich.get(Calendar.MONTH)+1, lich.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String aiueo = String.valueOf(Integer.valueOf(day1.replace("-",""))-Integer.valueOf(sachs.getNgaymuon().replace("-","")));
                String aiue = aiueo.substring(0,aiueo.length()-5);
                String thanhtien= String.valueOf(Integer.valueOf(aiue)*(sachs.getSach().getPrice()));
                Log.i("soday:",aiueo.substring(0,aiueo.length()-5));
                DatabaseReference myRef2 = database.getReference("Bookss");
                myRef2.child(String.valueOf(sachs.getId())).child("ngaytra").setValue(day1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "loi" + e, Toast.LENGTH_SHORT).show();
                                // ...
                            }
                        });
                myRef2.child(String.valueOf(sachs.getId())).child("thanhtien").setValue(thanhtien);

                alertDialog.dismiss();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    };

}