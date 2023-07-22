package com.sinhvien.orderdrinkapp.DTO;

public class BanAnChiTietDTO {

    private int id;
    private String idMaBan;
    private String idMaNV;
    private String date;

    public BanAnChiTietDTO(String idMaBan, String idMaNV, String date) {
        this.idMaBan = idMaBan;
        this.idMaNV = idMaNV;
        this.date = date;
    }

    public BanAnChiTietDTO(int id, String idMaBan, String idMaNV, String date) {
        this.id = id;
        this.idMaBan = idMaBan;
        this.idMaNV = idMaNV;
        this.date = date;
    }

    public BanAnChiTietDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdMaBan() {
        return idMaBan;
    }

    public void setIdMaBan(String idMaBan) {
        this.idMaBan = idMaBan;
    }

    public String getIdMaNV() {
        return idMaNV;
    }

    public void setIdMaNV(String idMaNV) {
        this.idMaNV = idMaNV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
