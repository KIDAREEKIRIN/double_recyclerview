package com.example.double_recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

// 자식 어답터
public class SubItemAdapter extends RecyclerView.Adapter<SubItemAdapter.SubItemViewHolder> {

    List<RetroDataStep> retroDataStepList;

    // 변수 선언.
    private String TAG = "여기까지";
    private Context mContext;

    // 생성자.
    public SubItemAdapter(List<RetroDataStep> retroDataStepList) {
        this.retroDataStepList = retroDataStepList;

    }

    public class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvSubItemTitle;
        // 추가.
        ImageButton iv_tips;

        SubItemViewHolder(View itemView) {
            super(itemView);

            tvSubItemTitle = itemView.findViewById(R.id.tv_sub_item_title);
            iv_tips = itemView.findViewById(R.id.iv_tips);


            // 클릭리스너. -> 본문 클릭시,
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPos = getAdapterPosition(); // click position.
                    RetroDataStep retroDataStep = retroDataStepList.get(currentPos); // 현재 클릭된 포지션의 아이템을 가져옴.
                    // 토스트 메세지 띄우기.
                    Toast.makeText(mContext, retroDataStep.getStep()
                            + retroDataStep.getNumber(), Toast.LENGTH_SHORT).show();

                    // 버튼 클릭하면.
                    switch(retroDataStep.getNumber()){
                        // 수련활동 기안계획서.
                        case  16:
                            openPdf("http://3.37.249.79/dutylist/sooryun/sooryun1.pdf");
                        break;

                      default:
                        break;
                    }
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
        subItemViewHolder.iv_tips.setTag(retroDataStep.getTips());
        Log.d(TAG, "onBindViewHolder: "+ retroDataStep.getTips());
        Log.d(TAG, "tips_contentAlertDialog: " + retroDataStep.getStep());
        Log.d(TAG, "onBindViewHolder: " + retroDataStep.getTips_content());


        // Tips가 있으면 보이고, 없으면 안보이기. (DB 연동)
        if(retroDataStep.getTips() == 1) {
            subItemViewHolder.iv_tips.setVisibility(View.VISIBLE);

            // iv_tips가 보인다면 iv_tips를 클릭했을 때,
            subItemViewHolder.iv_tips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tips_content에 내용이 있으면,
                    if(retroDataStep.getTips_content() != null ) {
                        // 다이얼로그 창 띄우기
                        tips_contentAlertDialog(subItemViewHolder, i);
                    }
                }
            });

        } else {
            subItemViewHolder.iv_tips.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, String.valueOf(retroData2List));
        return retroDataStepList.size();
    }

    // Tips 열기 (커스텀 다이얼로그)
    public void tips_contentAlertDialog(@NonNull SubItemViewHolder subItemViewHolder, int i) {

        RetroDataStep retroDataStep = retroDataStepList.get(i);
        subItemViewHolder.tvSubItemTitle.setText(retroDataStep.getStep());
        subItemViewHolder.iv_tips.setTag(retroDataStep.getTips());

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(retroDataStep.getStep()).setMessage(retroDataStep.getTips_content());
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    // Pdf 파일 열기.
    public void openPdf(String fileName) {

        /** PDF reader code **/

        Uri uri = Uri.parse(fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
