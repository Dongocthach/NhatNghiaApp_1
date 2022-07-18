package com.example.nhatnghia_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nhatnghia_app.Fragment.Fm_CapNhapTaiKhoan;
import com.example.nhatnghia_app.Fragment.Fm_DiaChi;
import com.example.nhatnghia_app.Fragment.Fm_DoiMatKhau;
import com.example.nhatnghia_app.Fragment.Fm_GioiThieu;
import com.example.nhatnghia_app.Fragment.Fm_bgroup_ImageControl;
import com.example.nhatnghia_app.Fragment.Fm_PhieuMuon;
import com.example.nhatnghia_app.Fragment.Fm_QuanLySach;
import com.example.nhatnghia_app.Fragment.Fm_QuanLyThanhVien;
import com.example.nhatnghia_app.Fragment.Fm_TrangChinh;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar= findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_open_24);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,new Fm_TrangChinh()).commit();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                Class fragmentClass = Fm_TrangChinh.class;
                switch (item.getItemId()){
                    case R.id.mn1: {
                        fragmentClass = Fm_TrangChinh.class;
                        break;
                    }
                    case R.id.mn2: {
                        fragmentClass = Fm_PhieuMuon.class;
                        break;
                    }
                    case R.id.mn3: {
                        fragmentClass = Fm_QuanLySach.class;
                        break;
                    }
                    case R.id.mn4: {
                        fragmentClass = Fm_QuanLyThanhVien.class;
                        break;
                    }
                    case R.id.mn5: {
                        fragmentClass = Fm_CapNhapTaiKhoan.class;
                        break;
                    }
                    case R.id.mn6: {
                        fragmentClass = Fm_DoiMatKhau.class;
                        break;
                    }
                    case R.id.mn7: {
                        System.exit(0);
                        break;
                    }
                    case R.id.mn8: {
                        fragmentClass = Fm_GioiThieu.class;
                        break;
                    }
                    case R.id.mn9: {
                        fragmentClass = Fm_DiaChi.class;
                        break;
                    }

                    default:
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this,"Exit",Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//                        startActivity(intent);
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit();

                    setTitle(item.getTitle());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }catch (Exception e){

                }
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }


}