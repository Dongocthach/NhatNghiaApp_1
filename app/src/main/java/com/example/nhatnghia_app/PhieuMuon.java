package com.example.nhatnghia_app;

public class PhieuMuon {
    private String id;

    public PhieuMuon(String id) {
        this.id = id;
    }

    public PhieuMuon() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" +
                "id='" + id + '\'' +
                '}';
    }
}
