package com.example.nhatnghia_app;

public class ThanhVien {
    private String Id;
    private String Hoten;
    private String ngaysinh;

    public ThanhVien() {
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

    @Override
    public String toString() {
        return "ThanhVien{" +
                "Id='" + Id + '\'' +
                ", Hoten='" + Hoten + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                '}';
    }
}
