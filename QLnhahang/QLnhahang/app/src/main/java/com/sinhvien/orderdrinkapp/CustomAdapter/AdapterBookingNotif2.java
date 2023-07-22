package com.sinhvien.orderdrinkapp.CustomAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinhvien.orderdrinkapp.DAO.BanAnChiTietDAO;
import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DAO.NhanVienDAO;
import com.sinhvien.orderdrinkapp.DTO.BanAnChiTietDTO;
import com.sinhvien.orderdrinkapp.DTO.BanAnDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.List;

public class AdapterBookingNotif2  extends RecyclerView.Adapter<AdapterBookingNotif2.ViewHolder> {

    private Context context;
    private List<BanAnDTO> banAnDTOList;
    private NhanVienDAO nhanVienDAO;
    private BanAnDAO banAnDAO;
    private BanAnChiTietDAO banAnChiTietDAO;

    public AdapterBookingNotif2(Context context, List<BanAnDTO> banAnDTOList){
        this.context = context;
        this.banAnDTOList = banAnDTOList;
        nhanVienDAO = new NhanVienDAO(context);
        banAnDAO = new BanAnDAO(context);
        banAnChiTietDAO = new BanAnChiTietDAO(context);
    }

    @NonNull
    @Override
    public AdapterBookingNotif2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticebook2, parent, false);
        return new AdapterBookingNotif2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookingNotif2.ViewHolder holder, int position) {
        BanAnDTO banAnDTO = banAnDTOList.get(position);
        holder.txtBan.setText("Bàn : "+banAnDTO.getTenBan());
        holder.txtNgayDat.setText("Ngày đặt : "+banAnDTO.getDate());
        holder.txtTrangThai.setText("Trạng thái : Bàn đã được đặt");
    }

    @Override
    public int getItemCount() {
        return banAnDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtBan, txtTrangThai, txtNgayDat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBan = itemView.findViewById(R.id.txtBan);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);

        }
    }
}
