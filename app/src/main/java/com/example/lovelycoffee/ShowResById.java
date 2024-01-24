package com.example.lovelycoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowResById extends AppCompatActivity {


    TextView textViewName;
    TextView textViewLocation;
    TextView textViewTime;
    TextView textViewDetail;

    String resId, resName, resLocation, resTime, resDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_res_by_id);

        textViewName = findViewById(R.id.textViewName);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewTime = findViewById(R.id.textViewTime);
        textViewDetail = findViewById(R.id.textViewDetail);

        //Receive data click to Show old data
        Intent intent = getIntent();
        resId = intent.getStringExtra("resId");
        resName = intent.getStringExtra("resName");
        resLocation = intent.getStringExtra("resLocation");
        resTime = intent.getStringExtra("resTime");
        resDetail = intent.getStringExtra("resDetail");

        textViewName.setText("ชื่อร้าน" + " " + ": " + resName);
        textViewLocation.setText("สถานที่ตั้ง" + " " + ": " + resLocation);
        textViewTime.setText("เวลาเปิด-ปิด" + " " + ": " + resTime);
        textViewDetail.setText("รายละเอียดเพิ่มเติม" + " " + ": " + resDetail);

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to the main activity
                Intent intent = new Intent(ShowResById.this, ShowRes.class); // Replace MainActivity.class with the actual main activity class
                startActivity(intent);
                finish(); // Optional: finish the current activity if you don't want to come back to it
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ShowRes.class);
        startActivity(intent);
        finish();
    }
}