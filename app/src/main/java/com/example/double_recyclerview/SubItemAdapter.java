package com.example.double_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// 자식 어답터
public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    // 변수 선언.
    public List<RetroData2> retroData2List;
    public List<RetroData> retroDataList;
    public RetroDataStep retroDataStep;
    public List<RetroDataStep> retroDataStepList;
    public List<RetroDataStep> list1;
    public List<RetroDataStep> list2;
    private String TAG = "여기까지";
    private int a = 6;

    // 생성자.

    public SubItemAdapter(List<RetroDataStep> retroDataStepList, List<RetroDataStep> list1, List<RetroDataStep> list2) {
        this.retroDataStepList = retroDataStepList;
        this.list1 = retroDataStepList;
        this.list2 = retroDataStepList;

    }

    public SubItemAdapter(List<RetroDataStep> retroDataStepList) {
        this.retroDataStepList = list1;
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
        // RetroData2의 데이터를 반영
        RetroDataStep retroDataStep = retroDataStepList.get(i);
        subItemViewHolder.tvSubItemTitle.setText(retroDataStep.getStep());


        Log.d(TAG, "onBindViewHolder: " + retroDataStepList);



    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, String.valueOf(retroData2List));
        return list1.size();
    }

}
