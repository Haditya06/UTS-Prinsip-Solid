/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

/**
 *
 * @author 
 */

public class LayananItem {

    private int id;
    private String namaLayanan;
    private double harga;
    private String deskripsi;

    public LayananItem(int id,String namaLayanan,double harga,String deskripsi) {
        this.id = id;
        this.namaLayanan = namaLayanan;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public LayananItem(String namaLayanan,double harga,String deskripsi) {
        this.namaLayanan = namaLayanan;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public int getId() {
        return id;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public double getHarga() {
        return harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
}
