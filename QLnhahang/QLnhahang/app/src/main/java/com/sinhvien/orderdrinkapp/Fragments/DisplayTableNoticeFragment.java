package com.sinhvien.orderdrinkapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterBookingNotif;
import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterDisplayTable;
import com.sinhvien.orderdrinkapp.DAO.BanAnChiTietDAO;
import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DTO.BanAnChiTietDTO;
import com.sinhvien.orderdrinkapp.DTO.BanAnDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.ArrayList;
import java.util.List;


public class DisplayTableNoticeFragment extends Fragment {

    private RecyclerView recyclerTableNotice;
    private BanAnChiTietDAO banAnDAO;
    private List<BanAnChiTietDTO> banAnDTOList = new ArrayList<>();
    private AdapterBookingNotif adapterBookingNotif;
    public DisplayTableNoticeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_table_notice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerTableNotice = view.findViewById(R.id.rvDisplayTableNotice);
        banAnDAO = new BanAnChiTietDAO(getActivity());
        HienThiDSBan();
    }


    @Override
    public void onResume() {
        super.onResume();
        adapterBookingNotif.notifyDataSetChanged();
    }

    private void HienThiDSBan() {
        banAnDTOList = banAnDAO.LayTatCaBanAnChiTiet();
        adapterBookingNotif = new AdapterBookingNotif(getActivity(), banAnDTOList);
        recyclerTableNotice.setAdapter(adapterBookingNotif);
        recyclerTableNotice.setHasFixedSize(true);
        recyclerTableNotice.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapterBookingNotif.notifyDataSetChanged();
    }
}