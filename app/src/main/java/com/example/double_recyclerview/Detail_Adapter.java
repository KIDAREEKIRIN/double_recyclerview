package com.example.double_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Detail_Adapter extends RecyclerView.Adapter<Detail_Adapter.detailViewHolder> {

//    List<RetroDataStep> retroDataStepList;

    List<String> retroDataStepList;

    private Context mContext;

    // 변수선언.

//    public Detail_Adapter(List<RetroDataStep> retroDataStepList) {
//        this.retroDataStepList = retroDataStepList;
//    }


    public Detail_Adapter(List<String> retroDataStepList) {
        this.retroDataStepList = retroDataStepList;
    }

    @NonNull
    @Override
    public Detail_Adapter.detailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_detail_item,parent,false);
        return new detailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Detail_Adapter.detailViewHolder holder, int position) {
        holder.tv_detail_sub_item_title.setText(retroDataStepList.get(position));
        holder.iv_detail_tips.setTag(retroDataStepList.get(position));
//        holder.ib_add.setTag(retroDataStepList.get(position));

    }

    @Override
    public int getItemCount() {
        return retroDataStepList.size();
    }

    public class detailViewHolder extends RecyclerView.ViewHolder {

        ImageButton iv_detail_tips, ib_add;
        TextView tv_detail_sub_item_title;


        public detailViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_detail_tips = itemView.findViewById(R.id.iv_detail_tips);
            ib_add = itemView.findViewById(R.id.ib_add);
            tv_detail_sub_item_title = itemView.findViewById(R.id.tv_detail_sub_item_title);
        }
    }
}
