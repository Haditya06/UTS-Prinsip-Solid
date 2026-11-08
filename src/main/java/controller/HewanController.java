/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author
 */
import modul.HewanItem;
import service.HewanService;
import java.util.List;

public class HewanController {

    private final HewanService service = new HewanService();

    public List<HewanItem> getAllHewan() {
        return service.getAllHewan();
    }

    public List<HewanItem> searchHewan(String keyword) {
        return service.searchHewan(keyword);
    }

    public void addHewan(HewanItem item) {
        service.addHewan(item);
    }

    public void updateHewan(HewanItem item) {
        service.updateHewan(item);
    }

    public void deleteHewan(int id) {
        service.deleteHewan(id);
    }
}
