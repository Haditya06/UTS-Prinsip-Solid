/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 
 */

import modul.LayananItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LayananService {

    private static final String URL  = "jdbc:mysql://localhost:3306/database hazzelnut";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<LayananItem> getAllLayanan(){

        List<LayananItem> list = new ArrayList<>();

        String sql = "SELECT * FROM layanan";

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){

                list.add(new LayananItem(
                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),
                        rs.getDouble("harga"),
                        rs.getString("deskripsi")
                ));
            }

        }catch(Exception e){
            throw new RuntimeException("Gagal load layanan : " + e.getMessage());
        }

        return list;
    }

    public List<LayananItem> searchLayanan(String keyword){

        List<LayananItem> list = new ArrayList<>();

        String sql = """
            SELECT *
            FROM layanan
            WHERE nama_layanan LIKE ?
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,"%"+keyword+"%");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                list.add(new LayananItem(
                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),
                        rs.getDouble("harga"),
                        rs.getString("deskripsi")
                ));
            }

        }catch(Exception e){
            throw new RuntimeException("Gagal cari layanan : " + e.getMessage());
        }

        return list;
    }

    public void addLayanan(LayananItem item){

        String sql = """
            INSERT INTO layanan(
            nama_layanan,
            harga,
            deskripsi)
            VALUES(?,?,?)
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,item.getNamaLayanan());
            ps.setDouble(2,item.getHarga());
            ps.setString(3,item.getDeskripsi());

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal tambah layanan : " + e.getMessage());
        }
    }

    public void updateLayanan(LayananItem item){

        String sql = """
            UPDATE layanan
            SET nama_layanan=?,
                harga=?,
                deskripsi=?
            WHERE id_layanan=?
            """;

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1,item.getNamaLayanan());
            ps.setDouble(2,item.getHarga());
            ps.setString(3,item.getDeskripsi());
            ps.setInt(4,item.getId());

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal update layanan : " + e.getMessage());
        }
    }

    public void deleteLayanan(int id){

        String sql = "DELETE FROM layanan WHERE id_layanan=?";

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1,id);

            ps.executeUpdate();

        }catch(Exception e){
            throw new RuntimeException("Gagal hapus layanan : " + e.getMessage());
        }
    }
}
