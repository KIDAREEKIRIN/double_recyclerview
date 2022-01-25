package com.example.double_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<RetroData> retroDataList;
    SubItemAdapter subItemAdapter;
    public RetroData retroData;
    public RetroData2 retroData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GetDataService getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroData>> call = getDataService.getAllDatas();

        call.enqueue(new Callback<List<RetroData>>() {
            @Override
            public void onResponse(Call<List<RetroData>> call, Response<List<RetroData>> response) {
                if(response.isSuccessful() && response.body() != null) {
                    // 통신되면.
                    generateDataList(response.body());
                    Toast.makeText(getApplicationContext(), "잘했어.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<List<RetroData>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "도망쳐.", Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
//        getData();
    }

    //    // 상위아이템 큰박스 아이템을 10개 만듭니다.
//    private List<Item> buildItemList() {
//        List<Item> itemList = new ArrayList<>();
//        for (int i=0; i<10; i++) {
//            Item item = new Item("Item "+i, buildSubItemList());
//            itemList.add(item);
//        }
//        return itemList;
//    }
//    // 그안에 존재하는 하위 아이템 박스(3개씩 보이는 아이템들)
//    private List<SubItem> buildSubItemList() {
//        List<SubItem> subItemList = new ArrayList<>();
//        for (int i=0; i<3; i++) {
//            SubItem subItem = new SubItem("Sub Item "+i);
//            subItemList.add(subItem);
//        }
//        return subItemList;
//    }

//    private List<RetroData> buildItemList() {
//        List<RetroData> retroDataList = new ArrayList<>();
//        RetroData retroData = new RetroData(retroData2.getTitle(), retroData2.getStep1());
//        retroDataList.add(retroData);
//        return retroDataList;
//    }

//    private List<RetroData2> buildSubItemList() {
//        List<RetroData2> retroData2List = new ArrayList<>();
//        for (int i = 0; i < , i++)
//        RetroData2 retroData2 = new RetroData2(getTitle(),getStep1(),getStep2());
//    }


    public void generateDataList(List<RetroData> retroDataList) {
        // 상위 리사이클러뷰 설정
        RecyclerView rvItem = findViewById(R.id.rv_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        ItemAdapter itemAdapter = new ItemAdapter(retroDataList);
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);
    }


}