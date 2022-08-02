package com.example.nhatnghia_app;

import java.util.HashMap;
import java.util.Map;

public class Sachs {
    private int id;
    private String ngaymuon;
    private String ngaytra;
    private String thanhtien;
    private Sach sach;
    private ThanhVien thanhVien;
    private PhieuMuon phieuMuon;

    public Sachs() {
    }

    public Sachs(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public Sachs(int id, String ngaymuon, String ngaytra, String thanhtien, Sach sach, ThanhVien thanhVien, PhieuMuon phieuMuon) {
        this.id = id;
        this.ngaymuon = ngaymuon;
        this.ngaytra = ngaytra;
        this.thanhtien = thanhtien;
        this.sach = sach;
        this.thanhVien = thanhVien;
        this.phieuMuon = phieuMuon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public PhieuMuon getPhieuMuon() {
        return phieuMuon;
    }

    public void setPhieuMuon(PhieuMuon phieuMuon) {
        this.phieuMuon = phieuMuon;
    }

    public String getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(String thanhtien) {
        this.thanhtien = thanhtien;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id",id);
        result.put("ngaymuon",ngaymuon);
        result.put("ngaytra",ngaytra);
        result.put("sach",sach);
        result.put("thanhtien",thanhtien);
        result.put("thanhVien",thanhVien);
        result.put("phieuMuon",phieuMuon);
        return result;
    }
    @Override
    public String toString() {
        return "Sachs{" +
                "id=" + id +
                ", ngaymuon='" + ngaymuon + '\'' +
                ", ngaytra='" + ngaytra + '\'' +
                ", sach=" + sach +
                ", thanhVien=" + thanhVien +
                ", phieuMuon=" + phieuMuon +
                '}';
    }
}
