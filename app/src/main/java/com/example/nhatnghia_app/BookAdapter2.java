package com.example.nhatnghia_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.SachViewHodlder> {

    ImageView imageView;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    private Context mContext;
    private List<Sach> mSachList;
    private IClickListener mIClickListerner;

    public  interface  IClickListener{
        void onClickUpdateItem(Sach book);
        void onClickDeleteItem(Sach book);

    }
    public BookAdapter2(Context mContext) {
        this.mContext = mContext;
    }

    public BookAdapter2(Context mContext, List<Sach> mSachList) {
        this.mContext = mContext;
        this.mSachList = mSachList;
    }

    public void setData(List<Sach>list){
        this.mSachList = list;
        notifyDataSetChanged();
    }

    public BookAdapter2(List<Sach> mSachList, IClickListener mIClickListerner) {
        this.mSachList = mSachList;
        this.mIClickListerner = mIClickListerner;
    }

    @NonNull
    @Override
    public SachViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach2,parent , false);
        return new SachViewHodlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHodlder holder, int position) {
        Sach sach = mSachList.get(position);
        if (sach == null){
            return;
        }

        holder.id.setText(sach.getTenSach());
        holder.tensach.setText(sach.getID());
        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickUpdateItem(sach);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickDeleteItem(sach);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mSachList != null)
            return mSachList.size();
        return 0;
    }

    public class SachViewHodlder extends RecyclerView.ViewHolder {

        private TextView id,tensach;
        private Button btn1,btn2;


        public SachViewHodlder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.tv1);
            tensach= itemView.findViewById(R.id.tv2);
            btn1 = itemView.findViewById(R.id.btn1);
            btn2 = itemView.findViewById(R.id.btn2);
        }
    }
}
