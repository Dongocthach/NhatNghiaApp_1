package com.example.nhatnghia_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatnghia_app.BookAdapter1;
import com.example.nhatnghia_app.R;
import com.example.nhatnghia_app.Sach;

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
        mlist.add(new Sach("a","b","c","d","2022_07_16_07_14_31"));
        mlist.add(new Sach("a","b","c","d","2022_07_16_07_14_31"));
        mlist.add(new Sach("a","b","c","d","2022_07_16_07_14_31"));
        mlist.add(new Sach("a","b","c","d","2022_07_16_07_14_31"));
        mbookAdapter = new BookAdapter1(getActivity(),mlist);
        recyclerView.setAdapter(mbookAdapter);

        return v;


    }
}