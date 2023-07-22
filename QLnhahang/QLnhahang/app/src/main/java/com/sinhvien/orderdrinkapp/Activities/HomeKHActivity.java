package com.sinhvien.orderdrinkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sinhvien.orderdrinkapp.R;

public class HomeKHActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_khactivity);
    }

    public void onClickDatBan(View view) {
        startActivity(new Intent(HomeKHActivity.this, DisplayTableActivity.class));
    }

    public void onClickXemDatBan(View view) {
        startActivity(new Intent(HomeKHActivity.this, NotifBookingActivity.class));
    }
}