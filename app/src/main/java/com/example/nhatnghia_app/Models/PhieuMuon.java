package com.example.nhatnghia_app.Models;

public class PhieuMuon {
    private String id;
    private  String tenquantrivien;

    public PhieuMuon(String id) {
        this.id = id;
    }

    public PhieuMuon(String id, String tenquantrivien) {
        this.id = id;
        this.tenquantrivien = tenquantrivien;
    }

    public PhieuMuon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenquantrivien() {
        return tenquantrivien;
    }

    public void setTenquantrivien(String tenquantrivien) {
        this.tenquantrivien = tenquantrivien;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "id='" + id + '\'' +
                '}';
    }
}
