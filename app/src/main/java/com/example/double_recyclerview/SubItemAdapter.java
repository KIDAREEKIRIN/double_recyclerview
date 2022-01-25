package com.example.double_recyclerview;

import android.content.Context;
import android.content.Intent;
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

    List<RetroDataStep> retroDataStepList;
    List<RetroData> retroDataList;
    RetroData retroData;
    // 변수 선언.
    private String TAG = "여기까지";
    private int a = 6;
    private Context mContext;

    // 생성자.
    public SubItemAdapter(List<RetroDataStep> retroDataStepList) {
        this.retroDataStepList = retroDataStepList;

    }

    public class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;

        SubItemViewHolder(View itemView) {
            super(itemView);

            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);

            // 클릭리스너.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPos = getAdapterPosition(); // click position.
                    RetroDataStep retroDataStep = retroDataStepList.get(currentPos); // 현재 클릭된 포지션의 아이템을 가져옴.

                    Toast.makeText(mContext, retroDataStep.getStep() + retroDataStep.getStep1() + retroDataStep.getNumber(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_sub_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, int i) {
        // RetroDataStep의 데이터를 반영

        RetroDataStep retroDataStep = retroDataStepList.get(i);
        subItemViewHolder.tvSubItemTitle.setText(retroDataStep.getStep());

    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, String.valueOf(retroData2List));
        return retroDataStepList.size();
    }


}
