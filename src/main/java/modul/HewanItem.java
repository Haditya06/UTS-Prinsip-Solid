/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modul;

/**
 *
 * @author 
 */

public class HewanItem {

    private int id;
    private String namaHewan;
    private int idCustomer;
    private String namaCustomer;

    public HewanItem(int id, String namaHewan,
            int idCustomer, String namaCustomer) {

        this.id = id;
        this.namaHewan = namaHewan;
        this.idCustomer = idCustomer;
        this.namaCustomer = namaCustomer;
    }

    public HewanItem(String namaHewan, int idCustomer) {
        this.namaHewan = namaHewan;
        this.idCustomer = idCustomer;
    }

    public int getId() {
        return id;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public String getNamaCustomer() {
        return namaCustomer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}