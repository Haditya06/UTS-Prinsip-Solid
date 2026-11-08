/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 
 */

import modul.HewanItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HewanService {

    private static final String URL  = "jdbc:mysql://localhost:3306/database hazzelnut";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<HewanItem> getAllHewan() {
        List<HewanItem> list = new ArrayList<>();

        String sql = """
            SELECT h.id_hewan,
                   h.nama_hewan,
                   h.id_customer,
                   u.nama
            FROM hewan h
            JOIN user u ON h.id_customer = u.id_user
            ORDER BY h.id_hewan DESC
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                list.add(new HewanItem(
                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),rs.getInt("id_customer"),rs.getString("nama")));
            }

        }catch(Exception e){
            throw new RuntimeException("Gagal load hewan : " + e.getMessage());
        }

        return list;
    }

    public List<HewanItem> searchHewan(String keyword){
        List<HewanItem> list = new ArrayList<>();

        String sql = """
            SELECT h.id_hewan,
                   h.nama_hewan,
                   h.id_customer,
                   u.nama
            FROM hewan h
            JOIN user u ON h.id_customer = u.id_user
            WHERE h.nama_hewan LIKE ?
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new HewanItem(
                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),
                        rs.getInt("id_customer"),
                        rs.getString("nama")
                ));
            }

        }catch(Exception e){
            throw new RuntimeException("Gagal cari hewan : " + e.getMessage());
        }

        return list;
    }

    public void addHewan(HewanItem item){

        String sql = """
            INSERT INTO hewan(nama_hewan,id_customer)
            VALUES(?,?)
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,item.getNamaHewan());
            ps.setInt(2,item.getIdCustomer());

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal tambah hewan : " + e.getMessage());
        }
    }

    public void updateHewan(HewanItem item){

        String sql = """
            UPDATE hewan
            SET nama_hewan=?,
                id_customer=?
            WHERE id_hewan=?
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,item.getNamaHewan());
            ps.setInt(2,item.getIdCustomer());
            ps.setInt(3,item.getId());

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal update hewan : " + e.getMessage());
        }
    }

    public void deleteHewan(int id){

        String sql = "DELETE FROM hewan WHERE id_hewan=?";

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,id);

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal hapus hewan : " + e.getMessage());
        }
    }
}