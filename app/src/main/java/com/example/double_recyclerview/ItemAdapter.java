package com.example.double_recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 상위 어답터
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    //변수 선언.
    List<RetroData> retroDataList;
    List<RetroDataStep> retroDataStepList;

    private Context mContext;

    public String TAG = "여기까지 돌아감.";
    private int a =6;


    // 생성자.
    ItemAdapter(List<RetroData> retroDataList) {
        this.retroDataList = retroDataList;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

         TextView tvItemTitle;
         RecyclerView rvSubItem;
         ImageButton ib_addBtn, ib_editBtn;

        ItemViewHolder(View itemView) {
            // 조상의 생성자에서 itemView 를 써줘야함.
            super(itemView);

            // 부모 타이틀
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);

            // 추가 버튼
            ib_addBtn = itemView.findViewById(R.id.ib_addBtn);

            // 수정 버튼
            ib_editBtn = itemView.findViewById(R.id.ib_editBtn);

            // 자식아이템 영역 -> 뷰홀더에 자식 영역을 추가함.
            rvSubItem = itemView.findViewById(R.id.rv_sub_item);

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mContext = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, @SuppressLint("RecyclerView") int i) {
        // title 에 대한 retroData의 리스트를 get(i) 값으로 반영.
        RetroData retroData = retroDataList.get(i);
        itemViewHolder.tvItemTitle.setText(retroData.getTitle());

        // RetroDataStep에 대한 내용.
        GetDataService getDataService3 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroDataStep>> call3 = getDataService3.getAllDatas3();

        call3.enqueue(new Callback<List<RetroDataStep>>() {
            @Override
            public void onResponse(Call<List<RetroDataStep>> call, Response<List<RetroDataStep>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    retroDataStepList = response.body();
                    Log.d(TAG, "onResponse: 데이터 넘어오니?" + retroData.getTitle());

                    // 자식 레이아웃 매니저 설정
                    LinearLayoutManager layoutManager = new LinearLayoutManager(
                            itemViewHolder.rvSubItem.getContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                    );

                    // 시기별 업무 올리기.

//                     1. 시기 확인.
                    if(retroData.getNumber() == 1) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList1());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);

                        // 2. 야영수련활동 운영계획 수립.

                    } else if(retroData.getNumber() == 2) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList2());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                        // 3. 학운위 안건 심의
                    } else if(retroData.getNumber() == 3){
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList3());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                        // 4. 야영수련활동 운영계획 내부결재.
                    } else if (retroData.getNumber() == 4) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList4());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                        // 5. 교통편 예약/.
                    } else if(retroData.getNumber() == 5) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList5());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                        // 6. 사전답사.
                    } else if(retroData.getNumber() == 6) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList6());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                        // 나머지.
                    } else if(retroData.getNumber() == 7){
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList7());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                    } else if (retroData.getNumber() == 8) {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(getList8());
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                    } else {
                        SubItemAdapter subItemAdapter = new SubItemAdapter(retroDataStepList);
                        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
                        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<RetroDataStep>> call, Throwable t) {

            }
        });

        // 다이얼로그 창 띄우기.
            itemViewHolder.ib_addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addList_Dialog(itemViewHolder,i);

                }
            });

            // 수정 버튼 클릭시, 화면전환 시키기
        itemViewHolder.ib_editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goDetail = new Intent(mContext.getApplicationContext(),DetailActivity.class);
                goDetail.putExtra("dutyTitle",retroData.getTitle());
//                goDetail.putExtra("dutyStep")
                goDetail.putStringArrayListExtra("dutyStringStep",getStringList());
                goDetail.putParcelableArrayListExtra("dutyStep", (ArrayList<? extends Parcelable>) getList1());
                mContext.startActivity(goDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retroDataList.size();
    }

    public ArrayList<String> getStringList() {
        ArrayList<String> getString1 = new ArrayList<>();
        for (int i = 0; i < 6; i++){
            getString1.add(i,retroDataStepList.get(i).getStep());
        }
        return getString1;
    }

    public List<RetroDataStep> getList1() {
        List<RetroDataStep> list1 = new ArrayList<>();

        // duty_title이 1.시기확인인 것만 가져오기.
//        if(retroDataStep.getStep().equals("1. 시기 확인"))
//            for (int i = 0; i < retroDataStep.getStep().length(); i++){
//                list1.add(i,retroDataStepList.get(i));
//            }
        list1.add(0,retroDataStepList.get(0));
        list1.add(1,retroDataStepList.get(1));
        list1.add(2,retroDataStepList.get(2));
        list1.add(3,retroDataStepList.get(3));
        list1.add(4,retroDataStepList.get(4));
        list1.add(5,retroDataStepList.get(5));
        return list1;
    }

    private List<RetroDataStep> getList2() {
        List<RetroDataStep> list2 = new ArrayList<>();
//        list2
        list2.add(0,retroDataStepList.get(6));
        list2.add(1,retroDataStepList.get(7));
        list2.add(2,retroDataStepList.get(8));
        list2.add(3,retroDataStepList.get(9));
        list2.add(4,retroDataStepList.get(10));
        list2.add(5,retroDataStepList.get(11));
        list2.add(6,retroDataStepList.get(12));
        list2.add(7,retroDataStepList.get(13));
        list2.add(8,retroDataStepList.get(14));
        list2.add(9,retroDataStepList.get(15));
        return list2;
    }

    private List<RetroDataStep> getList3() {
        List<RetroDataStep> list3 = new ArrayList<>();
        list3.add(0,retroDataStepList.get(16));
        list3.add(1,retroDataStepList.get(17));
        list3.add(2,retroDataStepList.get(18));
        list3.add(3,retroDataStepList.get(19));
        list3.add(4,retroDataStepList.get(20));
        return list3;
    }

    private List<RetroDataStep> getList4() {
        List<RetroDataStep> list4 = new ArrayList<>();
        list4.add(0,retroDataStepList.get(21));
        list4.add(1,retroDataStepList.get(22));
        return list4;
    }

    private List<RetroDataStep> getList5() {
        List<RetroDataStep> list5 = new ArrayList<>();
        list5.add(0,retroDataStepList.get(23));
        list5.add(1,retroDataStepList.get(24));
        return list5;
    }

    private List<RetroDataStep> getList6() {
        List<RetroDataStep> list6 = new ArrayList<>();
        list6.add(0,retroDataStepList.get(25));
        list6.add(1,retroDataStepList.get(26));
        list6.add(2,retroDataStepList.get(27));
        list6.add(3,retroDataStepList.get(28));
        list6.add(4,retroDataStepList.get(29));
        list6.add(5,retroDataStepList.get(30));
        return list6;
    }

    private List<RetroDataStep> getList7() {
        List<RetroDataStep> list7 = new ArrayList<>();
        list7.add(0,retroDataStepList.get(0));
        return list7;
    }

    private List<RetroDataStep> getList8() {
        List<RetroDataStep> list8 = new ArrayList<>();
        list8.add(0,retroDataStepList.get(0));
        return list8;
    }

    // ArrayList 추가하는 다이얼로그 창.
    private void addList_Dialog(@NonNull ItemViewHolder itemViewHolder, int i) {
        RetroData retroData = retroDataList.get(i);
//        itemViewHolder.tvItemTitle.setText(retroData.getTitle());

        final EditText editText = new EditText(mContext); // 입력창.

        AlertDialog.Builder dig = new AlertDialog.Builder(mContext);
        dig.setTitle(retroData.getTitle()); // 제목을 가져온다.
        dig.setView(editText);
        dig.setIcon(R.drawable.ic_add);
        dig.setPositiveButton("입력", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String addStep = editText.getText().toString();
                Intent addStep1 = new Intent(mContext.getApplicationContext(),MainActivity.class);

                List<String> getList1 = new ArrayList<>();
                getList1.add(0,addStep);

                // 1. DB에 업로드한다.
                // 2. 업로드한 내용을 다시 가져온다.?
//                getList1().add((Parcelable)addStep);

                Toast.makeText(mContext, addStep, Toast.LENGTH_SHORT).show(); // 토스트 메세지 띄우기.
            }
        });
        dig.show();
    }
}
