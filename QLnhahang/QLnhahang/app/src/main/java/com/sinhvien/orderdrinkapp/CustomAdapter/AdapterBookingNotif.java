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

import org.w3c.dom.Text;

import java.util.List;

public class AdapterBookingNotif extends RecyclerView.Adapter<AdapterBookingNotif.ViewHolder> {

    private Context context;
    private List<BanAnChiTietDTO>  banAnDTOList;
    private NhanVienDAO nhanVienDAO;
    private BanAnDAO banAnDAO;
    private BanAnChiTietDAO banAnChiTietDAO;

    public AdapterBookingNotif(Context context, List<BanAnChiTietDTO> banAnDTOList){
        this.context = context;
        this.banAnDTOList = banAnDTOList;
        nhanVienDAO = new NhanVienDAO(context);
        banAnDAO = new BanAnDAO(context);
        banAnChiTietDAO = new BanAnChiTietDAO(context);
    }

    @NonNull
    @Override
    public AdapterBookingNotif.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_noticebook, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBookingNotif.ViewHolder holder, int position) {
        BanAnChiTietDTO banAnDTO = banAnDTOList.get(position);
        String tenban = banAnDAO.LayTenBanTheoMa(Integer.parseInt(banAnDTO.getIdMaBan()));
        holder.txtBan.setText("Bàn : "+tenban);
        String hoten = nhanVienDAO.LayTenNVTheoMa(Integer.parseInt(banAnDTO.getIdMaNV()));
        holder.txtNguoiDat.setText("Họ tên : "+hoten);
        holder.txtNgayDat.setText("Ngày đặt : "+banAnDTO.getDate());

        holder.btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn xác nhận đơn đặt bàn này không ?")
                        .setPositiveButton("Có", (dialogInterface, i) -> {
                            String tinhtrang = banAnDAO.LayTinhTrangBanTheoMa(Integer.parseInt(banAnDTO.getIdMaBan()));
                            banAnDAO.CapNhatTinhTrangBan(Integer.parseInt(banAnDTO.getIdMaBan()),tinhtrang.equals("false") ? "true" : "false");
                            banAnDAO.CapNhatMaNV(Integer.parseInt(banAnDTO.getIdMaBan()), Integer.parseInt(banAnDTO.getIdMaNV()),banAnDTO.getDate());
                            banAnChiTietDAO.XoaBanAnChiTietTheoId(banAnDTO.getId());
                            banAnDTOList.remove(position);
                            if(banAnDTOList.size() == 0){
                                banAnDTOList.clear();
                            }
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("Không", (dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return banAnDTOList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtBan,txtNguoiDat,txtNgayDat;
        private Button btnXacnhan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtBan = itemView.findViewById(R.id.txtBan);
            txtNguoiDat = itemView.findViewById(R.id.txtNguoiDat);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);
            btnXacnhan = itemView.findViewById(R.id.btnXacNhan);

        }
    }
}
