package com.sinhvien.orderdrinkapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.BanAnChiTietDTO;
import com.sinhvien.orderdrinkapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class BanAnChiTietDAO {
    SQLiteDatabase database;
    public BanAnChiTietDAO(Context context){
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    //insert
    public boolean ThemBanAnChiTiet(String idMaBan, String idMaNV, String date){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_CHITIETDATBAN_MABAN, idMaBan);
        contentValues.put(CreateDatabase.TBL_CHITIETDATBAN_MANV, idMaNV);
        contentValues.put(CreateDatabase.TBL_CHITIETDATBAN_NGAYDAT, date);
        long ktra = database.insert(CreateDatabase.TBL_BANCHITIET, null, contentValues);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }

    //get all data from table
    public List<BanAnChiTietDTO> LayTatCaBanAnChiTiet(){
        List<BanAnChiTietDTO> banAnChiTietDTOList = new ArrayList<BanAnChiTietDTO>();
        String query = "SELECT * FROM " +CreateDatabase.TBL_BANCHITIET;
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            BanAnChiTietDTO banAnChiTietDTO = new BanAnChiTietDTO();
            banAnChiTietDTO.setId(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_CHITIETDATBAN_ID)));
            banAnChiTietDTO.setIdMaBan(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_CHITIETDATBAN_MABAN)));
            banAnChiTietDTO.setIdMaNV(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_CHITIETDATBAN_MANV)));
            banAnChiTietDTO.setDate(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_CHITIETDATBAN_NGAYDAT)));
            banAnChiTietDTOList.add(banAnChiTietDTO);
            cursor.moveToNext();
        }
        return banAnChiTietDTOList;
    }

    //delete Id
    public boolean XoaBanAnChiTietTheoId(int id){
        long ktra = database.delete(CreateDatabase.TBL_BANCHITIET, CreateDatabase.TBL_CHITIETDATBAN_ID + " = " + id, null);
        if(ktra != 0){
            return true;
        }else {
            return false;
        }
    }


}
