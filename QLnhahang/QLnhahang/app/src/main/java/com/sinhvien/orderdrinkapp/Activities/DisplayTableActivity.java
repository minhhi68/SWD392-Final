package com.sinhvien.orderdrinkapp.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterDisPlayTable2;
import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterDisplayTable;
import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DTO.BanAnDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.List;

public class DisplayTableActivity extends AppCompatActivity  {
    GridView GVDisplayTable;
    List<BanAnDTO> banAnDTOList;
    BanAnDAO banAnDAO;
    AdapterDisPlayTable2 adapterDisplayTable;

    FloatingActionButton fabAddTable;

    //Dùng activity result (activityforresult ko hổ trợ nữa) để nhận data gửi từ activity addtable
    ActivityResultLauncher<Intent> resultLauncherAdd = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        boolean ktra = intent.getBooleanExtra("ketquathem", false);
                        if (ktra) {
                            HienThiDSBan();
                            Toast.makeText(DisplayTableActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DisplayTableActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table);

//        setHasOptionsMenu(true);
        GVDisplayTable = (GridView) findViewById(R.id.gvDisplayTable);

        banAnDAO = new BanAnDAO(DisplayTableActivity.this);

        HienThiDSBan();

        registerForContextMenu(GVDisplayTable);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterDisplayTable.notifyDataSetChanged();
    }

    private void HienThiDSBan() {
        banAnDTOList = banAnDAO.LayTatCaBanAn();
        adapterDisplayTable = new AdapterDisPlayTable2(DisplayTableActivity.this, R.layout.custom_layout_displaytable, banAnDTOList);
        GVDisplayTable.setAdapter(adapterDisplayTable);
        adapterDisplayTable.notifyDataSetChanged();
    }

}