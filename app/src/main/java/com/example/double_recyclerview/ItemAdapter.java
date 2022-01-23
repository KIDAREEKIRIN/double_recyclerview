package com.example.double_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 상위 어답터
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    //변수 선언.
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
     List<RetroData> retroDataList;
     List<RetroData2> retroData2List;


    public String TAG = "여기까지 돌아감.";


    // 생성자.
    ItemAdapter(List<RetroData> retroDataList) {
        this.retroDataList = retroDataList;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tvItemTitle;
         RecyclerView rvSubItem;

        ItemViewHolder(View itemView) {
            // 조상의 생성자에서 itemView 를 써줘야함.
            super(itemView);

            // 부모 타이틀
            tvItemTitle = itemView.findViewById(R.id.tv_item_title);

            // 자식아이템 영역 -> 뷰홀더에 자식 영역을 추가함.
            rvSubItem = itemView.findViewById(R.id.rv_sub_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {

                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        RetroData retroData = retroDataList.get(i);
        itemViewHolder.tvItemTitle.setText(retroData.getTitle());

//        // 속도 향상 -> 하면 좋고, 아님 말고.
//        layoutManager.setInitialPrefetchItemCount(retroData.getSubItemList().size());

        GetDataService getDataService2 = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroData2>> call2 = getDataService2.getAllDatas2();

        call2.enqueue(new Callback<List<RetroData2>>() {
            @Override
            public void onResponse(Call<List<RetroData2>> call, Response<List<RetroData2>> response) {
                if(response.isSuccessful() && response.body() != null) {

                    generateData2List(itemViewHolder,response.body());

                }
            }

            @Override
            public void onFailure(Call<List<RetroData2>> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, String.valueOf(retroDataList));
        return retroDataList.size();
    }


    public void generateData2List(@NonNull ItemViewHolder itemViewHolder, List<RetroData2> retroData2List) {
        // 자식 레이아웃 매니저 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                itemViewHolder.rvSubItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        // 자식 어답터 설정
        SubItemAdapter subItemAdapter = new SubItemAdapter(retroData2List);
        itemViewHolder.rvSubItem.setLayoutManager(layoutManager);
        itemViewHolder.rvSubItem.setAdapter(subItemAdapter);

        itemViewHolder.rvSubItem.setRecycledViewPool(viewPool);

    }

}
