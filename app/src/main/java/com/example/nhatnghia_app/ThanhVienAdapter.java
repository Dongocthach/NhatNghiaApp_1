package com.example.nhatnghia_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHodlder>implements Filterable {
    private List<ThanhVien> mThanhVienList;
    private List<ThanhVien> mThanhVienListOld;
    private ThanhVienAdapter.IClickListener mIClickListerner;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    mThanhVienList = mThanhVienListOld;
                }else{
                    List<ThanhVien> list = new ArrayList<>();
                    for(ThanhVien thanhVien : mThanhVienListOld){
                        if(thanhVien.getId().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(thanhVien);
                        }
                    }
                    mThanhVienList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mThanhVienList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mThanhVienList = (List<ThanhVien>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public  interface  IClickListener{
        void onClickUpdateItemTV(ThanhVien tv);
        void onClickDeleteItem(ThanhVien tv);

    }

    public ThanhVienAdapter(List<ThanhVien> mThanhVienList, IClickListener mIClickListerner) {
        this.mThanhVienList = mThanhVienList;
        this.mIClickListerner = mIClickListerner;
        this.mThanhVienListOld = mThanhVienList;
    }

    @NonNull
    @Override
    public ThanhVienViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sathanhvien,parent , false);
        return new ThanhVienViewHodlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHodlder holder, int position) {
        ThanhVien tv = mThanhVienList.get(position);
        if (tv == null){
            return;
        }
        holder.id.setText(tv.getId());
        holder.hoten.setText(tv.getHoten());
        holder.ngaysinh.setText(tv.getNgaysinh());
        holder.sdt.setText(tv.getSdt());

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickUpdateItemTV(tv);
            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickDeleteItem(tv);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mThanhVienList != null)
            return mThanhVienList.size();
        return 0;
    }

    public class ThanhVienViewHodlder extends RecyclerView.ViewHolder {

        private TextView id,hoten,ngaysinh,sdt;
        private Button btn1,btn2;

        public ThanhVienViewHodlder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.ithanhvien_tv1);
            hoten = itemView.findViewById(R.id.ithanhvien_tv2);
            ngaysinh = itemView.findViewById(R.id.ithanhvien_tv3);
            sdt = itemView.findViewById(R.id.ithanhvien_tv4);
            btn1 = itemView.findViewById(R.id.ithanhvien_btn1);
            btn2 = itemView.findViewById(R.id.ithanhvien_btn2);

        }
    }
}
