package com.example.nhatnghia_app;

import java.util.HashMap;
import java.util.Map;

public class Sach {
    private  String TenSach;
    private String tenTacGia;
    private String TheLoai;
    private String ID;
    private String imgsach;

    public Sach() {
    }

    public Sach(String ID, String imgsach, String tenSach, String tenTacGia, String theLoai) {
        this.ID = ID;
        this.imgsach = imgsach;
        this.TenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.TheLoai = theLoai;

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
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tenSach",TenSach);
        result.put("tenTacGia",tenTacGia);
        result.put("theLoai",TheLoai);
        result.put("imgsach",imgsach);
        return result;
    }

}
