package repository;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modul.UserItem;

/**
 * Implementasi repository menggunakan JDBC dan MySQL.
 */
public class JdbcUserRepository implements UserRepository {

    @Override
    public List<UserItem> findAll() {
        String sql = "SELECT id_user, nama, username, role FROM user ORDER BY id_user ASC";
        List<UserItem> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) users.add(mapUser(rs));
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Gagal memuat data pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserItem> search(String keyword) {
        String sql = "SELECT id_user, nama, username, role FROM user "
                + "WHERE nama LIKE ? OR username LIKE ? OR role LIKE ? ORDER BY id_user ASC";
        List<UserItem> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + keyword.trim() + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) users.add(mapUser(rs));
            }
            return users;
        } catch (Exception e) {
            throw new RuntimeException("Gagal mencari pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT id_user FROM user WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("id_user") : -1;
            }
        } catch (Exception e) {
            throw new RuntimeException("Gagal mengambil ID pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM user WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException("Gagal memeriksa username: " + e.getMessage(), e);
        }
    }

    @Override
    public void insert(UserItem user) {
        String sql = "INSERT INTO user (nama, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getNama());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Gagal menambahkan pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(UserItem user, String newPassword) {
        boolean changePassword = newPassword != null && !newPassword.isEmpty();
        String sql = changePassword
                ? "UPDATE user SET nama=?, username=?, password=?, role=? WHERE id_user=?"
                : "UPDATE user SET nama=?, username=?, role=? WHERE id_user=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getNama());
            ps.setString(2, user.getUsername());
            if (changePassword) {
                ps.setString(3, newPassword);
                ps.setString(4, user.getRole());
                ps.setInt(5, user.getId());
            } else {
                ps.setString(3, user.getRole());
                ps.setInt(4, user.getId());
            }
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Gagal memperbarui pengguna: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM user WHERE id_user = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Gagal menghapus pengguna: " + e.getMessage(), e);
        }
    }

    private UserItem mapUser(ResultSet rs) throws Exception {
        return new UserItem(rs.getInt("id_user"), rs.getString("nama"),
                rs.getString("username"), rs.getString("role"));
    }
}
