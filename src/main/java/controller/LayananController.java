/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author 
 */
import modul.LayananItem;
import service.LayananService;
import java.util.List;

public class LayananController {

    private final LayananService service = new LayananService();

    public List<LayananItem> getAllLayanan() {
        return service.getAllLayanan();
    }

    public List<LayananItem> searchLayanan(String keyword) {
        return service.searchLayanan(keyword);
    }

    public void addLayanan(LayananItem item) {
        service.addLayanan(item);
    }

    public void updateLayanan(LayananItem item) {
        service.updateLayanan(item);
    }

    public void deleteLayanan(int id) {
        service.deleteLayanan(id);
    }
}
