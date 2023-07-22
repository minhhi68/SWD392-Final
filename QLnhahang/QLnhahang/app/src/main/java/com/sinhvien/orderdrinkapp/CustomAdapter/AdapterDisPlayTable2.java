package com.sinhvien.orderdrinkapp.CustomAdapter;

import static com.sinhvien.orderdrinkapp.Activities.LoginActivity.manv;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.sinhvien.orderdrinkapp.Activities.DisplayCategoryActivity;
import com.sinhvien.orderdrinkapp.Activities.DisplayTableActivity;
import com.sinhvien.orderdrinkapp.Activities.DisplayTableActivity;
import com.sinhvien.orderdrinkapp.Activities.HomeKHActivity;
import com.sinhvien.orderdrinkapp.Activities.LoginActivity;
import com.sinhvien.orderdrinkapp.Activities.PaymentActivity;
import com.sinhvien.orderdrinkapp.DAO.BanAnChiTietDAO;
import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DAO.DonDatDAO;
import com.sinhvien.orderdrinkapp.DTO.BanAnDTO;
import com.sinhvien.orderdrinkapp.DTO.DonDatDTO;
import com.sinhvien.orderdrinkapp.Fragments.DisplayCategoryFragment;
import com.sinhvien.orderdrinkapp.Fragments.DisplayMenuFragment;
import com.sinhvien.orderdrinkapp.Fragments.DisplayTableFragment;
import com.sinhvien.orderdrinkapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AdapterDisPlayTable2 extends BaseAdapter implements View.OnClickListener{

    Context context;
    int layout;
    List<BanAnDTO> banAnDTOList;
    ViewHolder viewHolder;
    BanAnDAO banAnDAO;
    DonDatDAO donDatDAO;
    BanAnChiTietDAO banAnChiTietDAO;
    FragmentManager fragmentManager;

    public AdapterDisPlayTable2(Context context, int layout, List<BanAnDTO> banAnDTOList){
        this.context = context;
        this.layout = layout;
        this.banAnDTOList = banAnDTOList;
        banAnDAO = new BanAnDAO(context);
        donDatDAO = new DonDatDAO(context);
        banAnChiTietDAO = new BanAnChiTietDAO(context);
        fragmentManager = ((DisplayTableActivity)context).getSupportFragmentManager();
    }

    @Override
    public int getCount() {
        return banAnDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return banAnDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return banAnDTOList.get(position).getMaBan();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewHolder = new ViewHolder();
            view = inflater.inflate(layout,parent,false);

            viewHolder.imgBanAn = (ImageView) view.findViewById(R.id.img_customtable_BanAn);
            viewHolder.imgGoiMon = (ImageView) view.findViewById(R.id.img_customtable_GoiMon);
            viewHolder.imgThanhToan = (ImageView) view.findViewById(R.id.img_customtable_ThanhToan);
            viewHolder.imgAnNut = (ImageView) view.findViewById(R.id.img_customtable_AnNut);
            viewHolder.txtTenBanAn = (TextView)view.findViewById(R.id.txt_customtable_TenBanAn);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if(banAnDTOList.get(position).isDuocChon()){
            HienThiButton();
        }else {
            AnButton();
        }

        BanAnDTO banAnDTO = banAnDTOList.get(position);

        String kttinhtrang = banAnDAO.LayTinhTrangBanTheoMa(banAnDTO.getMaBan());
        if(kttinhtrang.equals("true") && banAnDTO.getIdMaNv().equals(String.valueOf(manv))){
            viewHolder.imgBanAn.setImageResource(R.drawable.ic_baseline_airline_seat_legroom_normal_40);
            //sự kiện click
            viewHolder.imgBanAn.setOnClickListener(this);
            viewHolder.imgGoiMon.setOnClickListener(this);
            viewHolder.imgThanhToan.setOnClickListener(this);
            viewHolder.imgAnNut.setOnClickListener(this);
        }else {
            viewHolder.imgBanAn.setImageResource(R.drawable.ic_baseline_event_seat_40);
            viewHolder.imgBanAn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_datban);
                    //chính kích thước dialog
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(dialog.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setAttributes(layoutParams);

                    TextView tvNgatDatBan = (TextView) dialog.findViewById(R.id.tvNgayDat);
                    TextView tvGioDatBan = (TextView) dialog.findViewById(R.id.tvGioDat);
                    Button btnDatBan = (Button) dialog.findViewById(R.id.btnDatBan);
                    tvNgatDatBan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
                            builder.setTitleText("Chọn ngày");
                            MaterialDatePicker materialDatePicker = builder.build();
                            materialDatePicker.show(fragmentManager,"Ngày đặt");
                            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                                String format = "dd/MM/yyyy";
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                                tvNgatDatBan.setText(simpleDateFormat.format(materialDatePicker.getSelection()));
                            });
                        }
                    });

                    tvGioDatBan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()//chọn giờ
//                                    .setTimeFormat(TimeFormat.CLOCK_24H)//định dạng 24h cho time picker
//                                    .setTitleText("Chọn giờ")
//                                    .build();
//                            materialTimePicker.show(fragmentManager, "TIME_PICKER");//hiển thị time picker
//                            materialTimePicker.addOnPositiveButtonClickListener(v1 -> {
//                                String hour = materialTimePicker.getHour() < 10 ? "0" + materialTimePicker.getHour() : materialTimePicker.getHour() + "";//nếu giờ nhỏ hơn 10 thì thêm số 0 vào trước giờ
//                                String minute = materialTimePicker.getMinute() < 10 ? "0" + materialTimePicker.getMinute() : materialTimePicker.getMinute() + "";//nếu phút nhỏ hơn 10 thì thêm số 0 vào trước phút
//                                tvGioDatBan.setText(hour + ":" + minute);
//                            });
                            Calendar calendar = Calendar.getInstance();
                            int gio = calendar.get(Calendar.HOUR_OF_DAY);
                            int phut = calendar.get(Calendar.MINUTE);
                            TimePickerDialog timePickerDialog = new TimePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                    String hour = i < 10 ? "0" + i : i + "";//nếu giờ nhỏ hơn 10 thì thêm số 0 vào trước giờ
                                    String minute = i1 < 10 ? "0" + i1 : i1 + "";//nếu phút nhỏ hơn 10 thì thêm số 0 vào trước phút
                                    tvGioDatBan.setText(hour + ":" + minute);
                                }
                            },gio,phut,true);
                            timePickerDialog.show();
                        }
                    });

                    btnDatBan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String ngaydat = tvNgatDatBan.getText().toString();
                            String giodat = tvGioDatBan.getText().toString();
                            if(ngaydat.equals("") || giodat.equals("")){
                                Toast.makeText(context, "Vui lòng chọn ngày giờ đặt bàn", Toast.LENGTH_SHORT).show();
                            }else {
                                String date = ngaydat+" "+giodat;
                                banAnDAO.CapNhatMaNV(banAnDTO.getMaBan(),-1,date);
                                banAnChiTietDAO.ThemBanAnChiTiet(String.valueOf(banAnDTO.getMaBan()),String.valueOf(manv),date);
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                    return false;
                }
            });
        }

        viewHolder.txtTenBanAn.setText(banAnDTO.getTenBan());
        viewHolder.imgBanAn.setTag(position);



        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        viewHolder = (ViewHolder) ((View) v.getParent()).getTag();

        int vitri1 = (int) viewHolder.imgBanAn.getTag();

        int maban = banAnDTOList.get(vitri1).getMaBan();
        String tenban = banAnDTOList.get(vitri1).getTenBan();
        long ngaydat1 = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String ngaydat=  dateFormat.format(ngaydat1);

        switch (id){
            case R.id.img_customtable_BanAn:
                int vitri = (int)v.getTag();
                banAnDTOList.get(vitri).setDuocChon(true);
                HienThiButton();
                break;

            case R.id.img_customtable_AnNut:
                AnButton();
                break;

            case R.id.img_customtable_GoiMon:
                Intent getIHome = ((DisplayTableActivity)context).getIntent();
                int manv = LoginActivity.manv;
                String tinhtrang = banAnDAO.LayTinhTrangBanTheoMa(maban);

                DonDatDTO donDatDTO = new DonDatDTO();
                donDatDTO.setMaBan(maban);
                donDatDTO.setMaNV(manv);
                donDatDTO.setNgayDat(ngaydat);
                donDatDTO.setTinhTrang("false");
                donDatDTO.setTongTien("0");

                long ktra = donDatDAO.ThemDonDat(donDatDTO);
                banAnDAO.CapNhatTinhTrangBan(maban,"true");
                if(ktra == 0){ Toast.makeText(context,context.getResources().getString(R.string.add_failed),Toast.LENGTH_SHORT).show(); }
                //chuyển qua trang category
                Intent iCategory = new Intent(context, DisplayCategoryActivity.class);
                iCategory.putExtra("maban",maban);
                context.startActivity(iCategory);
                break;

            case R.id.img_customtable_ThanhToan:
                //chuyển dữ liệu qua trang thanh toán
                Intent iThanhToan = new Intent(context, PaymentActivity.class);
                iThanhToan.putExtra("maban",maban);
                iThanhToan.putExtra("tenban",tenban);
                iThanhToan.putExtra("ngaydat",ngaydat);
                context.startActivity(iThanhToan);
                break;
        }
    }

    private void HienThiButton(){
        viewHolder.imgGoiMon.setVisibility(View.VISIBLE);
        viewHolder.imgThanhToan.setVisibility(View.VISIBLE);
        viewHolder.imgAnNut.setVisibility(View.VISIBLE);
    }
    private void AnButton(){
        viewHolder.imgGoiMon.setVisibility(View.INVISIBLE);
        viewHolder.imgThanhToan.setVisibility(View.INVISIBLE);
        viewHolder.imgAnNut.setVisibility(View.INVISIBLE);
    }

    public class ViewHolder{
        ImageView imgBanAn, imgGoiMon, imgThanhToan, imgAnNut;
        TextView txtTenBanAn;
    }
}
