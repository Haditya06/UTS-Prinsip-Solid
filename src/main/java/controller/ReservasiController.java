/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author 
 */
import modul.ReservasiItem;
import service.ReservasiService;
import java.util.List;

public class ReservasiController {

    private final ReservasiService service = new ReservasiService();

    public List<ReservasiItem> getAllTransaksi() {
        return service.getAllTransaksi();
    }

    public List<ReservasiItem> searchTransaksi(String keyword) {
        return service.searchTransaksi(keyword);
    }

    public List<ReservasiItem> getByDokter(int idDokter) {
        return service.getByDokter(idDokter);
    }

    public List<ReservasiItem> getByCustomer(int idCustomer) {
        return service.getByCustomer(idCustomer);
    }

    public void addTransaksi(ReservasiItem item) {
        service.addTransaksi(item);
    }

    public void updateTransaksi(ReservasiItem item) {
        service.updateTransaksi(item);
    }

    public void deleteTransaksi(int id) {
        service.deleteTransaksi(id);
    }
}