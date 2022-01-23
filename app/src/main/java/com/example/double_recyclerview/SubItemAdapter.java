package com.example.double_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// 자식 어답터
public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    // 변수 선언.
    public List<RetroData2> retroData2List;
    public List<RetroData> retroDataList;
    private String TAG = "여기까지";

    // 생성자.

    public SubItemAdapter(List<RetroData2> retroData2List) {
        this.retroData2List = retroData2List;
    }

    class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;

        SubItemViewHolder(View itemView) {
            super(itemView);
            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
        }
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {
        RetroData2 retroData2 = retroData2List.get(i);


        if(retroData2.getTitle().equals("시기 확인")) {
            subItemViewHolder.tvSubItemTitle.setText(retroData2.getStep());
        } else if(retroData2.getTitle().equals("2.야영수련활동 운영계획 수립")){
            subItemViewHolder.tvSubItemTitle.setText("");
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, String.valueOf(retroData2List));
        return retroData2List.size();
    }
}
