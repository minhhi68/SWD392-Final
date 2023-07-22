package com.sinhvien.orderdrinkapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterDisplayCategory;
import com.sinhvien.orderdrinkapp.DAO.LoaiMonDAO;
import com.sinhvien.orderdrinkapp.DTO.LoaiMonDTO;
import com.sinhvien.orderdrinkapp.Fragments.DisplayMenuFragment;
import com.sinhvien.orderdrinkapp.R;

import java.util.List;

public class DisplayCategoryActivity extends AppCompatActivity {
    GridView gvCategory;
    List<LoaiMonDTO> loaiMonDTOList;
    LoaiMonDAO loaiMonDAO;
    AdapterDisplayCategory adapter;
    FragmentManager fragmentManager;
    int maban;

    ActivityResultLauncher<Intent> resultLauncherCategory = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        boolean ktra = intent.getBooleanExtra("ktra", false);
                        String chucnang = intent.getStringExtra("chucnang");
                        if (chucnang.equals("themloai")) {
                            if (ktra) {
                                HienThiDSLoai();
                                Toast.makeText(DisplayCategoryActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DisplayCategoryActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (ktra) {
                                HienThiDSLoai();
                                Toast.makeText(DisplayCategoryActivity.this, "Sủa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DisplayCategoryActivity.this, "sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_category);

        gvCategory = (GridView) findViewById(R.id.gvCategory);

        fragmentManager = DisplayCategoryActivity.this.getSupportFragmentManager();

        loaiMonDAO = new LoaiMonDAO(DisplayCategoryActivity.this);
        HienThiDSLoai();

        maban = getIntent().getIntExtra("maban", 0);


        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int maloai = loaiMonDTOList.get(position).getMaLoai();
                String tenloai = loaiMonDTOList.get(position).getTenLoai();
                Intent intent = new Intent(DisplayCategoryActivity.this, DisplayMenuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("maloai", maloai);
                bundle.putString("tenloai", tenloai);
                bundle.putInt("maban", maban);
                intent.putExtra("dulieu", bundle);
                startActivity(intent);
            }
        });

        registerForContextMenu(gvCategory);
    }

    private void HienThiDSLoai() {
        loaiMonDTOList = loaiMonDAO.LayDSLoaiMon();
        adapter = new AdapterDisplayCategory(DisplayCategoryActivity.this, R.layout.custom_layout_displaycategory, loaiMonDTOList);
        gvCategory.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}