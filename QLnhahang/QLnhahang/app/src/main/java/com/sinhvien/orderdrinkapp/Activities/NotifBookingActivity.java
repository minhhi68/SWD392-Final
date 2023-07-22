package com.sinhvien.orderdrinkapp.Activities;

import static com.sinhvien.orderdrinkapp.Activities.LoginActivity.manv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterBookingNotif2;
import com.sinhvien.orderdrinkapp.DAO.BanAnChiTietDAO;
import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DTO.BanAnDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotifBookingActivity extends AppCompatActivity {

    private BanAnDAO banAnDAO;
    private List<BanAnDTO> banAnDAOList = new ArrayList<>();
    private RecyclerView recyclerNotifBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif_booking);
        recyclerNotifBooking = (RecyclerView) findViewById(R.id.rv_notif_booking);
        banAnDAO = new BanAnDAO(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        banAnDAOList = banAnDAO.LayTatCaBanAnTheoMaNV(manv);
        AdapterBookingNotif2 adapterBookingNotif2 = new AdapterBookingNotif2(this, banAnDAOList);
        recyclerNotifBooking.setAdapter(adapterBookingNotif2);
        adapterBookingNotif2.notifyDataSetChanged();
        recyclerNotifBooking.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }
}