package com.example.nhatnghia_app;

import static com.example.nhatnghia_app.Fragment.Fm_CapNhapTaiKhoan.MY_REQUEST_CODE;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    Toolbar toolbar;
    public  final  int MY_REQUEST_CODE = 10;
    private NavigationView navigationView;

    private DrawerLayout drawerLayout;
    private ImageView imgAvatar;
    private TextView tvname,tvemail;

    private final Fm_CapNhapTaiKhoan myProfileFragment = new Fm_CapNhapTaiKhoan();
    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                if(intent == null){
                    return;
                }
                Uri uri = intent.getData();
                Log.i("123", String.valueOf(uri));
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    myProfileFragment.setBitmapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
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

        imgAvatar = navigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvname = navigationView.getHeaderView(0).findViewById(R.id.txtUser);
        tvemail = navigationView.getHeaderView(0).findViewById(R.id.txtgamil);

        showUserInformation();

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
                        FirebaseAuth.getInstance().signOut();
                        Intent intent  = new Intent(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
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

    public void showUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        }

        String name = user.getDisplayName();
        String email = user.getEmail();
        Uri photoUrl = user.getPhotoUrl();
        if(name == null){
            tvname.setVisibility(View.GONE);
        }else {
            tvname.setVisibility(View.VISIBLE);
            tvname.setText(name);
        }
        tvname.setText(name);
        tvemail.setText(email);
        Glide.with(this).load(photoUrl).error(R.drawable.adminicon).into(imgAvatar);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }

        }
    }
    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select picture"));

    };
}