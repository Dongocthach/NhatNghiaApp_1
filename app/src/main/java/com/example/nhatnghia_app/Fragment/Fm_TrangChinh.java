package com.example.nhatnghia_app.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nhatnghia_app.BookAdapter1;
import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Sach;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Fm_TrangChinh extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter1 mbookAdapter;
    private List<Sach> mlist;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fm__trang_chinh, container, false);
        recyclerView = v.findViewById(R.id.trangchinh_rcv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        mlist = new ArrayList<>();
        mlist.add(new Sach("1","narutotap5","naruto","mashamoto","anime"));
        mlist.add(new Sach("2","narutotap5","naruto","mashamoto","anime"));
        getListBookFromRealtimeDatabase();
        mbookAdapter = new BookAdapter1(getActivity(),mlist);
        recyclerView.setAdapter(mbookAdapter);

        return v;


    }
    private void getListBookFromRealtimeDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Books");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(mlist != null){
                    mlist.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Sach bk = dataSnapshot.getValue(Sach.class);
                    mlist.add(bk);
                }
                mbookAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"load data failed",Toast.LENGTH_SHORT).show();

            }
        });

    }
}