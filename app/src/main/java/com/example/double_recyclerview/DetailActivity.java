package com.example.double_recyclerview;

import androidx.appcompat.app.AlertDialog;
//import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView tv_detail_item_title;

    RecyclerView rv_detail_sub_item;
    Detail_Adapter detail_adapter;
    LinearLayoutManager linearLayoutManager;

    List<RetroDataStep> retroDataStepList;

    // 첫번째 녀석.
    ItemAdapter itemAdapter;
    // 추가 버튼.
    ImageButton ib_add;

    ArrayList<String> step1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_detail_item_title = findViewById(R.id.tv_detail_item_title); // Detail 액티비티 제목.
        rv_detail_sub_item = findViewById(R.id.rv_detail_sub_item); // Duty Step 내용.
        ib_add = findViewById(R.id.ib_add); // 추가 이미지 버튼.

        // 제목 값 받아오기.
        Intent getTitleData = getIntent();
        getTitleData.getStringExtra("dutyTitle"); // 넘어온 duty_Title
        getTitleData.getParcelableArrayListExtra("dutyStep"); // 넘어온 stepList 데이터 값

        getTitleData.getStringExtra("dutyStringStep");

        Log.d("넘어온 리스트는?", String.valueOf(getTitleData.getParcelableArrayListExtra("dutyStep")));

        // 제목 값 붙이기.
        tv_detail_item_title.setText(getTitleData.getStringExtra("dutyTitle"));
        rv_detail_sub_item.setHasFixedSize(true); // 크기 자동 조절.
        step1 = getTitleData.getStringArrayListExtra("dutyStringStep");
        detail_adapter = new Detail_Adapter(stepList1());

        Log.d("생성한 리스트는?", String.valueOf(getList1()));
        rv_detail_sub_item.setAdapter(detail_adapter);
        linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        rv_detail_sub_item.setLayoutManager(linearLayoutManager);

        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = new EditText(DetailActivity.this);

                AlertDialog.Builder dig = new AlertDialog.Builder(DetailActivity.this);
                dig.setTitle(getTitleData.getStringExtra("dutyTitle")); // 제목을 가져온다.
                dig.setView(editText);
                dig.setIcon(R.drawable.ic_add);
                dig.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 입력을 클릭 했을 때,
                    }

                });
                dig.show();
            }
        });
        detail_adapter.notifyDataSetChanged();


    }

    public ArrayList<String> stepList1() {
        ArrayList<String> steplist = new ArrayList<>();

        for (int i = 0; i < step1.size(); i++){
            steplist.add(i,step1.get(i));
        }
        return steplist;
    }

    public void add_Step() {

    }

    // "1. 시기 확인"에서 넘어온 데이터 값.
    private ArrayList<RetroDataStep> getList1(){
        Intent getTitleData = getIntent();
        ArrayList<RetroDataStep> stepList = getTitleData.getParcelableArrayListExtra("dutyStep");
        ArrayList<RetroDataStep> list1 = new ArrayList<>();
        for (int i = 0; i < stepList.size(); i++){
            list1.add(i,stepList.get(i));
        }
//        list1.add(0,stepList.get(0))
        return list1;
    }



}