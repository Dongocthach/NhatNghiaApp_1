package com.example.nhatnghia_app;

import android.content.Context;
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

public class BooksAdapter1 extends RecyclerView.Adapter<BooksAdapter1.SachViewHodlder> {

    private Context mContext;
    private List<Sachs> mSachList;
    private List<Sachs> mSachListOld;
    private IClickListener mIClickListerner;

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence charSequence) {
//                String strSearch = charSequence.toString();
//                if (strSearch.isEmpty()) {
//                    mSachList = mSachListOld;
//                } else {
//                    List<Sachs> list = new ArrayList<>();
//                    for (Sachs sach : mSachListOld) {
//                        if (sach.getPhieuMuon().getId().toLowerCase().contains(strSearch.toLowerCase())) {
//                            list.add(sach);
//                        }
//                    }
//                    mSachList = list;
//                }
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = mSachList;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
//                mSachList = (List<Sachs>) filterResults.values;
//                notifyDataSetChanged();
//            }
//        };
//    }


    public BooksAdapter1(Context mContext) {
        this.mContext = mContext;
    }

    public interface IClickListener {
        void onClickUpdateItemSachs(Sachs book);

        void onClickDeleteItemSachs(Sachs book);


    }

    public BooksAdapter1(List<Sachs> mSachList, IClickListener mIClickListerner) {
        this.mSachList = mSachList;
        this.mIClickListerner = mIClickListerner;
        this.mSachListOld = mSachList;
    }


    @NonNull
    @Override
    public SachViewHodlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sachs1, parent, false);
        return new SachViewHodlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHodlder holder, int position) {
        Sachs sach = mSachList.get(position);
        if (sach == null) {
            return;
        }

        holder.tv1.setText(sach.getPhieuMuon().getId());
        holder.tv2.setText(sach.getThanhVien().getHoten());
        holder.tv3.setText(sach.getSach().getTenSach());
        holder.tv4.setText(sach.getNgaymuon());
        holder.tv5.setText(sach.getNgaytra());
        holder.tv7.setText(sach.getThanhtien());

        holder.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickUpdateItemSachs(sach);

            }
        });
        holder.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIClickListerner.onClickDeleteItemSachs(sach);

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

        private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7;
        private Button btn1, btn2;

        public SachViewHodlder(@NonNull View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.isachs_tv1);
            tv2 = itemView.findViewById(R.id.isachs_tv2);
            tv3 = itemView.findViewById(R.id.isachs_tv3);
            tv4 = itemView.findViewById(R.id.isachs_tv4);
            tv5 = itemView.findViewById(R.id.isachs_tv5);
            tv6 = itemView.findViewById(R.id.isachs_tv6);
            tv7 = itemView.findViewById(R.id.isachs_tv7);
            btn1 = itemView.findViewById(R.id.isachs_btn1);
            btn2 = itemView.findViewById(R.id.isachs_btn2);

        }
    }
}
