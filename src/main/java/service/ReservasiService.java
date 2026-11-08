/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author attau
 */
import modul.ReservasiItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasiService {

    private static final String URL  = "jdbc:mysql://localhost:3306/database hazzelnut";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public List<ReservasiItem> getAllTransaksi() {

        List<ReservasiItem> list = new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,

                c.id_user AS id_customer,
                c.nama AS customer,

                h.id_hewan,
                h.nama_hewan,

                d.id_user AS id_dokter,
                d.nama AS dokter,

                l.id_layanan,
                l.nama_layanan,

                t.total_harga

            FROM transaksi t
            JOIN user c ON t.id_customer = c.id_user
            JOIN user d ON t.id_dokter = d.id_user
            JOIN hewan h ON t.id_hewan = h.id_hewan
            JOIN layanan l ON t.id_layanan = l.id_layanan

            ORDER BY t.id_transaksi DESC
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                list.add(new ReservasiItem(
                        rs.getInt("id_transaksi"),

                        rs.getInt("id_customer"),
                        rs.getString("customer"),

                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),

                        rs.getInt("id_dokter"),
                        rs.getString("dokter"),

                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),

                        rs.getDouble("total_harga")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal load transaksi : " + e.getMessage(), e);
        }

        return list;
    }

    public List<ReservasiItem> searchTransaksi(String keyword) {

        if (keyword == null || keyword.trim().isEmpty())
            return getAllTransaksi();

        List<ReservasiItem> list = new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,

                c.id_user AS id_customer,
                c.nama AS customer,

                h.id_hewan,
                h.nama_hewan,

                d.id_user AS id_dokter,
                d.nama AS dokter,

                l.id_layanan,
                l.nama_layanan,

                t.total_harga

            FROM transaksi t
            JOIN user c ON t.id_customer = c.id_user
            JOIN user d ON t.id_dokter = d.id_user
            JOIN hewan h ON t.id_hewan = h.id_hewan
            JOIN layanan l ON t.id_layanan = l.id_layanan

            WHERE c.nama LIKE ?
               OR h.nama_hewan LIKE ?
               OR d.nama LIKE ?
               OR l.nama_layanan LIKE ?
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            String key = "%" + keyword.trim() + "%";

            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new ReservasiItem(
                        rs.getInt("id_transaksi"),

                        rs.getInt("id_customer"),
                        rs.getString("customer"),

                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),

                        rs.getInt("id_dokter"),
                        rs.getString("dokter"),

                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),

                        rs.getDouble("total_harga")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal search transaksi : " + e.getMessage(), e);
        }

        return list;
    }

    public List<ReservasiItem> getByDokter(int idDokter) {

        List<ReservasiItem> list = new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,

                c.id_user AS id_customer,
                c.nama AS customer,

                h.id_hewan,
                h.nama_hewan,

                d.id_user AS id_dokter,
                d.nama AS dokter,

                l.id_layanan,
                l.nama_layanan,

                t.total_harga

            FROM transaksi t
            JOIN user c ON t.id_customer = c.id_user
            JOIN user d ON t.id_dokter = d.id_user
            JOIN hewan h ON t.id_hewan = h.id_hewan
            JOIN layanan l ON t.id_layanan = l.id_layanan

            WHERE d.id_user = ?
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idDokter);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new ReservasiItem(
                        rs.getInt("id_transaksi"),

                        rs.getInt("id_customer"),
                        rs.getString("customer"),

                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),

                        rs.getInt("id_dokter"),
                        rs.getString("dokter"),

                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),

                        rs.getDouble("total_harga")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal load transaksi dokter : " + e.getMessage(), e);
        }

        return list;
    }

    public List<ReservasiItem> getByCustomer(int idCustomer) {

        List<ReservasiItem> list = new ArrayList<>();

        String sql = """
            SELECT
                t.id_transaksi,

                c.id_user AS id_customer,
                c.nama AS customer,

                h.id_hewan,
                h.nama_hewan,

                d.id_user AS id_dokter,
                d.nama AS dokter,

                l.id_layanan,
                l.nama_layanan,

                t.total_harga

            FROM transaksi t
            JOIN user c ON t.id_customer = c.id_user
            JOIN user d ON t.id_dokter = d.id_user
            JOIN hewan h ON t.id_hewan = h.id_hewan
            JOIN layanan l ON t.id_layanan = l.id_layanan

            WHERE c.id_user = ?
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, idCustomer);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new ReservasiItem(
                        rs.getInt("id_transaksi"),

                        rs.getInt("id_customer"),
                        rs.getString("customer"),

                        rs.getInt("id_hewan"),
                        rs.getString("nama_hewan"),

                        rs.getInt("id_dokter"),
                        rs.getString("dokter"),

                        rs.getInt("id_layanan"),
                        rs.getString("nama_layanan"),

                        rs.getDouble("total_harga")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal load transaksi customer : " + e.getMessage(), e);
        }

        return list;
    }

    public void addTransaksi(ReservasiItem item) {

        String sql = """
            INSERT INTO transaksi
            (
                id_customer,
                id_hewan,
                id_dokter,
                id_layanan,
                total_harga
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, item.getIdCustomer());
            ps.setInt(2, item.getIdHewan());
            ps.setInt(3, item.getIdDokter());
            ps.setInt(4, item.getIdLayanan());
            ps.setDouble(5, item.getTotalHarga());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal tambah transaksi : " + e.getMessage(), e);
        }
    }

    public void updateTransaksi(ReservasiItem item) {

        String sql = """
            UPDATE transaksi
            SET
                id_customer=?,
                id_hewan=?,
                id_dokter=?,
                id_layanan=?,
                total_harga=?
            WHERE id_transaksi=?
        """;

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, item.getIdCustomer());
            ps.setInt(2, item.getIdHewan());
            ps.setInt(3, item.getIdDokter());
            ps.setInt(4, item.getIdLayanan());
            ps.setDouble(5, item.getTotalHarga());
            ps.setInt(6, item.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal update transaksi : " + e.getMessage(), e);
        }
    }

    public void deleteTransaksi(int id) {

        String sql = "DELETE FROM transaksi WHERE id_transaksi=?";

        try (
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Gagal hapus transaksi : " + e.getMessage(), e);
        }
    }
}