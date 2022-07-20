package com.example.nhatnghia_app;

import java.util.HashMap;
import java.util.Map;

public class Sach {
    private  String TenSach;
    private String tenTacGia;
    private String TheLoai;
    private String ID;
    private String imgsach;
    private int quantity;
    private int price;
    public Sach() {
    }

    public Sach(String ID, String imgsach, String tenSach, String tenTacGia, String TheLoai) {
        this.ID = ID;
        this.imgsach = imgsach;
        this.TenSach = tenSach;
        this.tenTacGia = tenTacGia;
        this.TheLoai = TheLoai;

    }

    public Sach(String tenSach, String tenTacGia, String theLoai, String ID, String imgsach, int quantity, int price) {
        TenSach = tenSach;
        this.tenTacGia = tenTacGia;
        TheLoai = theLoai;
        this.ID = ID;
        this.imgsach = imgsach;
        this.quantity = quantity;
        this.price = price;
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

    public void setTheLoai(String TheLoai) {
        this.TheLoai = TheLoai;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tenSach",TenSach);
        result.put("tenTacGia",tenTacGia);
        result.put("theLoai",TheLoai);
        result.put("imgsach",imgsach);
        result.put("quantity",quantity);
        result.put("price",price);
        return result;
    }

}
