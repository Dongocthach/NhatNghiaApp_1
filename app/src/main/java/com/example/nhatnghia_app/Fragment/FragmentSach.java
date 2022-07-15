package com.example.nhatnghia_app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nhatnghia_app.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSach extends Fragment {

    private RecyclerView rcvSach;
//    private SachAdapter SachAdapter;
//    private List<Sach> sachList;
    Button btnchuyensach2;
    ImageView img_sach;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_sach, container, false);

//        rcvSach = view.findViewById(R.id.rcv_sach);
//        SachAdapter = new SachAdapter(getContext());
//        btnchuyensach2 = view.findViewById(R.id.btnchuýenach2);
//        img_sach = view.findViewById(R.id.img_sach1);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
//        rcvSach.setLayoutManager(linearLayoutManager);
//
//        getListSach();
//
//        SachAdapter.setData(sachList);
//        rcvSach.setAdapter(SachAdapter);


        // set onclick

//        btnchuyensach2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "Chuyển  Sách", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), SachActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

//    private List<Sach> getListSach() {
//        List<Sach> sachList = new ArrayList<>();
//
//        sachList.add(new Sach("Cona thám tử lừng danh", "Aoyama Gōshō", "Trinh thám", "duchai0408@gmail.com","https://www.cgv.vn/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/c/o/conan_movie_2022-_vnese_poster_1_.jpg"));
//        sachList.add(new Sach("Cona thám tử lừng danh", "Aoyama Gōshō", "Trinh thám", "duchai0408@gmail.com","https://www.cgv.vn/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/c/o/conan_movie_2022-_vnese_poster_1_.jpg"));
//        list.add(new Sach("Cona thám tử lừng danh", "Aoyama Gōshō", "Trinh thám", "duchai0408@gmail.com", R.drawable.cona));
//
//        return sachList;
//    }
}