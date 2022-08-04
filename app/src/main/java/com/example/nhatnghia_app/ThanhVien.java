package com.example.nhatnghia_app;

import java.util.HashMap;
import java.util.Map;

public class ThanhVien {
    private String Id;
    private String Hoten;
    private String ngaysinh;
    private String sdt;

    public ThanhVien(String hoten) {
        Hoten = hoten;
    }

    public ThanhVien() {
    }

    public ThanhVien(String id, String hoten, String ngaysinh, String sdt) {
        Id = id;
        Hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }

    public ThanhVien(String id, String hoten) {
        Id = id;
        Hoten = hoten;
    }

    public ThanhVien(String id, String hoten, String ngaysinh) {
        Id = id;
        Hoten = hoten;
        this.ngaysinh = ngaysinh;
    }



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }


    @Override
    public String toString() {
        return "ThanhVien{" +
                "Id='" + Id + '\'' +
                ", Hoten='" + Hoten + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("Id",Id);
        result.put("Hoten",Hoten);
        result.put("ngaysinh",ngaysinh);
        result.put("sdt",sdt);
        return result;
    }
}
