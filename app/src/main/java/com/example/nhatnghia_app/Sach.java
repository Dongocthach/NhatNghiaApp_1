package com.example.nhatnghia_app;

public class Sach {
    private  String TenSach;
    private String tenTacGia;
    private String TheLoai;
    private String ID;
    private String imgsach;


    public Sach(String tenSach, String tenTacGia, String theLoai, String ID, String imgsach) {
        TenSach = tenSach;
        this.tenTacGia = tenTacGia;
        TheLoai = theLoai;
        this.ID = ID;
        this.imgsach = imgsach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImgsach() {
        return imgsach;
    }

    public void setImgsach(String imgsach) {
        this.imgsach = imgsach;
    }
}
