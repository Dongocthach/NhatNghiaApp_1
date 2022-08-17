package com.example.nhatnghia_app.Fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MyViewPagerAdapter extends FragmentStateAdapter {


    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Fm_bgroup_AddBook();
            case 1:
                return new Fm_bgroup_UpdateBook();
            case 2:
                return new Fm_bgroup_ImageControl();

            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
