package com.example.nhatnghia_app.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

//import com.example.myapplication.Adapter.Sach2Adapter;
//import com.example.myapplication.Adapter.SachAdapter;
//import com.example.myapplication.Model.Sach;
//import com.example.myapplication.Model.Sach2;
//
//import com.example.myapplication.SachActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.nhatnghia_app.R;

public class FragmentSach2 extends Fragment {

    private RecyclerView rcvSach2;
//    private Sach2Adapter sach2Adapter;
    Button btnchuyendang;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_sach2, container, false);


//        rcvSach2 = view.findViewById(R.id.rcv_sach2);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
//        rcvSach2.setLayoutManager(gridLayoutManager);
//
//
//        sach2Adapter.setData(getListSach2());
//        rcvSach2.setAdapter(sach2Adapter);
//
//        btnchuyendang = view.findViewById(R.id.btnchuýenach);
//        btnchuyendang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),FragmentSach.class);
//                startActivity(intent);
//                Toast.makeText(getActivity(),"chuyển sang danh sách",Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

//    private List<Sach2> getListSach2() {
//        List<Sach2> list = new ArrayList<>();
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//        list.add(new Sach2(R.drawable.cona, "Conan", "Aoyama Gōshō"));
//
//        return list;
//    }
}