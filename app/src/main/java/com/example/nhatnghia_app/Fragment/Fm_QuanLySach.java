package com.example.nhatnghia_app.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatnghia_app.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fm_QuanLySach extends Fragment {
    private MyViewPagerAdapter myViewPagerAdapter;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm__quan_ly_sach, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);

        myViewPagerAdapter = new MyViewPagerAdapter(this);
        mViewPager.setAdapter(myViewPagerAdapter);


        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {

            switch (position){
                case 0:
                    tab.setText("Add Book");
                    break;
                case 1:
                    tab.setText("Update Book");
                    break;
                case 2:
                    tab.setText("UpLoad Image");
                    break;


            }
        }).attach();

        return view;

    }
}