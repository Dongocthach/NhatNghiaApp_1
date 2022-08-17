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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Locale;

public class BookAdapter1 extends RecyclerView.Adapter<BookAdapter1.SachViewHodlder> {

    ImageView imageView;
    Uri imageUri;
    StorageReference storageReference;
    ProgressDialog progressDialog;

    private Context mContext;
    private List<Sach> mSachList;

    public BookAdapter1(Context mContext) {
        this.mContext = mContext;
    }

    public BookAdapter1(Context mContext, List<Sach> mSachList) {
        this.mContext = mContext;
        this.mSachList = mSachList;
    }

    public void setData(List<Sach>list){
        this.mSachList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SachViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach1,parent , false);
        return new SachViewHodlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHodlder holder, int position) {
        Sach sach = mSachList.get(position);
        if (sach == null){
            return;
        }


        String idimage = sach.getImgsach().trim();
        storageReference = FirebaseStorage.getInstance().getReference().child("images/"+idimage);

        try {
            File localfile = File.createTempFile("tempfile",".jpeg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            holder.imgsSach.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("Fire storage Image","Failed to Retrieve image");
                    holder.imgsSach.setImageResource(R.drawable.adminicon);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        holder.tvTenSach.setText(sach.getTenSach());
        holder.tvTacGia.setText(sach.getTenTacGia());
        holder.tvTheLoai.setText(sach.getTheLoai());
        holder.tvID.setText(sach.getID());
    }

    @Override
    public int getItemCount() {
        if (mSachList != null)
            return mSachList.size();
        return 0;
    }

    public class SachViewHodlder extends RecyclerView.ViewHolder {

        private ImageView imgsSach;
        private TextView tvTenSach;
        private TextView tvTacGia;
        private TextView tvTheLoai;
        private TextView tvID;

        public SachViewHodlder(@NonNull View itemView) {
            super(itemView);

            imgsSach = itemView.findViewById(R.id.img_sach1);
            tvTenSach = itemView.findViewById(R.id.tvName);
            tvTacGia =  itemView.findViewById(R.id.tvtacgia);
            tvTheLoai = itemView.findViewById(R.id.tvtheloai);
            tvID = itemView.findViewById(R.id.tvID);
        }
    }
}
