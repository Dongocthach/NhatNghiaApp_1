package com.example.nhatnghia_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookAdapter2 extends RecyclerView.Adapter<BookAdapter2.SachViewHodlder> implements Filterable {

    private Context mContext;
    private List<Sach> mSachList;
    private List<Sach> mSachListOld;
    private IClickListener mIClickListerner;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mSachList = mSachListOld;
                }else{
                    List<Sach> list = new ArrayList<>();
                    for(Sach sach : mSachListOld){
                        if(sach.getTenSach().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(sach);
                        }
                    }
                    mSachList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mSachList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mSachList = (List<Sach>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public  interface  IClickListener{
        void onClickUpdateItem(Sach book);
        void onClickDeleteItem(Sach book);
        void onClickIncreaseQuantity(Sach book);
        void onClickDecreaseQuantity(Sach book);
        void onClickMovetoListOnRent(Sach book);

    }

    public BookAdapter2(List<Sach> mSachList, IClickListener mIClickListerner) {
        this.mSachList = mSachList;
        this.mIClickListerner = mIClickListerner;
        this.mSachListOld = mSachList;
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
        holder.quantity.setText(String.valueOf(sach.getQuantity()));
        holder.price.setText(String.valueOf(sach.getPrice()));
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
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickIncreaseQuantity(sach);
            }
        });
        holder.btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickDecreaseQuantity(sach);
            }
        });
        holder.btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickMovetoListOnRent(sach);
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

        private TextView id,tensach,quantity,price;
        private Button btn1,btn2,btn3,btn4,btn5;


        public SachViewHodlder(@NonNull View itemView) {
            super(itemView);
            id =itemView.findViewById(R.id.tv1);
            tensach= itemView.findViewById(R.id.tv2);
            quantity= itemView.findViewById(R.id.tv_quantity);
            price = itemView.findViewById(R.id.tv_price);
            btn1 = itemView.findViewById(R.id.btn1);
            btn2 = itemView.findViewById(R.id.btn2);
            btn3 = itemView.findViewById(R.id.btn3);
            btn4 = itemView.findViewById(R.id.btn4);
            btn5 = itemView.findViewById(R.id.btn5);
        }
    }
}
