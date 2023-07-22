package com.sinhvien.orderdrinkapp.DTO;

public class BanAnDTO {

    int MaBan;
    String TenBan;
    boolean DuocChon;
    String IdMaNv;
    String date;
    public BanAnDTO() {
    }

    public BanAnDTO(int maBan, String tenBan, boolean duocChon, String idMaNv) {
        MaBan = maBan;
        TenBan = tenBan;
        DuocChon = duocChon;
        IdMaNv = idMaNv;
    }


    public BanAnDTO(int maBan, String tenBan, boolean duocChon, String idMaNv, String date) {
        MaBan = maBan;
        TenBan = tenBan;
        DuocChon = duocChon;
        IdMaNv = idMaNv;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdMaNv() {
        return IdMaNv;
    }

    public void setIdMaNv(String idMaNv) {
        IdMaNv = idMaNv;
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String tenBan) {
        TenBan = tenBan;
    }

    public boolean isDuocChon() {
        return DuocChon;
    }

    public void setDuocChon(boolean duocChon) {
        DuocChon = duocChon;
    }
}
