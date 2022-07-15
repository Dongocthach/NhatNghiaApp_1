package com.example.nhatnghia_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nhatnghia_app.Fragment.FragmentSach;
import com.example.nhatnghia_app.Fragment.FragmentTest;
import com.google.android.material.navigation.NavigationView;

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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.menuSach: {
                        fragmentClass = FragmentSach.class;
                        Toast.makeText(MainActivity.this, "Sách", Toast.LENGTH_SHORT).show();
                        break;
                    }
//                    case R.id.menuSachmuon: {
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this, "Quan ly loai sach", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case R.id.menuPhieumuon: {
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this, "Quan ly sach", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case R.id.menuQuanLyThanhVien: {
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this, "Quan ly thanh vien", Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                    case R.id.menucapnhattaikhoan: {
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this, "top 10 sach muon nhieu nhat", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    }
//                    case R.id.menuDoimatkhau: {
//                        fragmentClass = FragmentTest.class;
//                        Toast.makeText(MainActivity.this, "Doanh thu", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    }
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