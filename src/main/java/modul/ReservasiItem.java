/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

/**
 *
 * @author 
 */

public class ReservasiItem {

    private int id;

    private int idCustomer;
    private String namaCustomer;

    private int idHewan;
    private String namaHewan;

    private int idDokter;
    private String namaDokter;

    private int idLayanan;
    private String namaLayanan;

    private double totalHarga;

    public ReservasiItem(int id,int idCustomer,String namaCustomer,int idHewan,String namaHewan,
            int idDokter,String namaDokter,int idLayanan,String namaLayanan,double totalHarga) {

        this.id = id;
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
        this.idHewan = idHewan;
        this.namaHewan = namaHewan;
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.idLayanan = idLayanan;
        this.namaLayanan = namaLayanan;
        this.totalHarga = totalHarga;
    }

    public ReservasiItem(int idCustomer,int idHewan,int idDokter,int idLayanan,double totalHarga) {
        this.idCustomer = idCustomer;
        this.idHewan = idHewan;
        this.idDokter = idDokter;
        this.idLayanan = idLayanan;
        this.totalHarga = totalHarga;
    }

    public int getId() {
        return id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public int getIdHewan() {
        return idHewan;
    }

    public int getIdDokter() {
        return idDokter;
    }

    public int getIdLayanan() {
        return idLayanan;
    }

    public double getTotalHarga() {
        return totalHarga;
    }
}
