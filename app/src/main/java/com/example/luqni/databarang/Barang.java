package com.example.luqni.databarang;
import java.io.Serializable;

public class Barang implements Serializable {
    private String id=" ";
    private  String kode=" ";
    private String nama=" ";
    private  String harga="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
    @Override
    public String toString() {
        return "Barang{" +
                "id='" + id + '\'' +
                ", kode='" + kode + '\'' +
                ", nama='" + nama + '\'' +
                ", harga='" + harga + '\'' +
                '}';
    }
}
