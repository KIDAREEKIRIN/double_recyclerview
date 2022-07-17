package com.example.double_recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

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
        ImageButton iv_tips, ib_files;
        CheckedTextView ct_check;
//        CardView cv_subItem;


        // 뷰홀더에서 동작은 하나, 한 번 더 클릭해야함. -> BindViewHolder에서 동작하게 바꾸기.
        SubItemViewHolder(View itemView) {
            super(itemView);
//            cv_subItem = itemView.findViewById(R.id.cv_subItem); // 카드뷰 클릭.
            iv_tips = itemView.findViewById(R.id.iv_tips); // Tips 이미지.
            ib_files = itemView.findViewById(R.id.ib_files); // File 이미지.
            ct_check = itemView.findViewById(R.id.ct_check); // checkedTextView 체크박스

            // 클릭리스너. -> 본문 클릭시,
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int currentPos = getAdapterPosition(); // click position.
//                    RetroDataStep retroDataStep = retroDataStepList.get(currentPos); // 현재 클릭된 포지션의 아이템을 가져옴.
//                    // 토스트 메세지 띄우기.
////                    Toast.makeText(mContext, retroDataStep.getStep()
////                            + retroDataStep.getNumber() + retroDataStep.getFilepath(), Toast.LENGTH_SHORT).show();
//
////
//                    // 체크박스 클릭시.
////                    ct_check.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////                            ct_check.toggle(); // 선택(체크 선택, 체크 해제)
////                            if(ct_check.isChecked()){
////                                ct_check.setPaintFlags(ct_check.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
////                            } else {
////                                ct_check.setPaintFlags(0);
////                            }
////                        }
////                    });
//
////
////                    // 버튼 클릭하면 파일열기 (pdf Viewer 연결)
////                    switch(retroDataStep.getNumber()){
////                        // 수련활동 기안계획서.
////                        case 16:
////                        case 31:
////                            // number 31에 맞는 filepath 열리기.
////                            // number 16에 맞는 filepath 열리기.
////                            openPdf(retroDataStep.getFilepath());
////                            break;
////                        default:
////                            break;
////                    }
//
//
//                }
//            });
        }
    }

    @NonNull
    @Override
    public SubItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //클릭리스너를 위한 mContext.
        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_checkbox_item, viewGroup, false);
        return new SubItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubItemViewHolder subItemViewHolder, @SuppressLint("RecyclerView") int i) {
        // RetroDataStep의 데이터를 반영
        RetroDataStep retroDataStep = retroDataStepList.get(i);
        subItemViewHolder.iv_tips.setTag(retroDataStep.getTips());  // Tips 이미지.
        subItemViewHolder.ib_files.setTag(retroDataStep.getTips()); // files 이미지.
        subItemViewHolder.ct_check.setText(retroDataStep.getStep()); // 체크박스 문장.

        Log.d(TAG, "onBindViewHolder: "+ retroDataStep.getTips());
        Log.d(TAG, "tips_contentAlertDialog: " + retroDataStep.getStep());
        Log.d(TAG, "onBindViewHolder: " + retroDataStep.getTips_content());


//        subItemViewHolder.cv_subItem.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        // 체크박스 클릭시.
        if(retroDataStep.getStep() != null) {
            subItemViewHolder.ct_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subItemViewHolder.ct_check.toggle(); // 체크 선택 / 해제 가능하게 하기.
                    if(subItemViewHolder.ct_check.isChecked()) {
                        // 선택하면 체크박스 선택되고, 가운데 줄긋기.
                        subItemViewHolder.ct_check.setPaintFlags(subItemViewHolder.ct_check.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        // 선택하면 체크해제 및 원래대로 돌아오기.
                        subItemViewHolder.ct_check.setPaintFlags(0);
                    }
                }
            });
        }

        // Tips가 있으면 보이고, 없으면 안보이기. (DB 연동)
        if(retroDataStep.getTips() == 1) {
            subItemViewHolder.iv_tips.setVisibility(View.VISIBLE); // Tips 보이기.
            subItemViewHolder.ib_files.setVisibility(View.INVISIBLE); // files 안보이기.

            // iv_tips가 보인다면 iv_tips를 클릭했을 때,
            subItemViewHolder.iv_tips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tips_content에 내용이 있으면,
                    if(retroDataStep.getTips_content() != null ) {
                        // 다이얼로그 창 띄우기 -> Tips_content.
                        tips_contentAlertDialog(subItemViewHolder, i);
                    }
                }
            });

        } else if (retroDataStep.getTips() == 2) {
            subItemViewHolder.iv_tips.setVisibility(View.INVISIBLE); //tips 안보이기.
            subItemViewHolder.ib_files.setVisibility(View.VISIBLE); // files 보이기.

            subItemViewHolder.ib_files.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(retroDataStep.getFilepath() != null) {
                        openPdf(retroDataStep.getFilepath());
                    }
                }
            });
        } else {
            subItemViewHolder.iv_tips.setVisibility(View.INVISIBLE); //tips 안보이기.
            subItemViewHolder.ib_files.setVisibility(View.INVISIBLE); // files 안보이기.
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
        subItemViewHolder.iv_tips.setTag(retroDataStep.getTips()); // tips 이미지.
        subItemViewHolder.ct_check.setText(retroDataStep.getStep()); // 체크박스

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
    public void openPdf(String filename) {

        /** PDF reader code **/
        Uri uri = Uri.parse(filename);
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
