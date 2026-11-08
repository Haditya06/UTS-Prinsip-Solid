/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author 
 */
import modul.SessionUser;
import modul.UserItem;
import java.sql.*;

public class AuthService {

    private static final String URL = "jdbc:mysql://localhost:3306/database hazzelnut";

    private static final String USER = "root";
    private static final String PASS = "";

    private Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public UserItem login(String username, String password) {
        validateInput(username, password);

        String sql = """
            SELECT
                id_user,
                nama,
                username,
                password,
                role
            FROM user
            WHERE username = ?
            """;

        try (
            Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new RuntimeException("Username tidak ditemukan!");
            }

            String passwordDb = rs.getString("password");
            if (!password.equals(passwordDb)) {
                throw new RuntimeException("Password salah!");
            }

            UserItem user = new UserItem(rs.getInt("id_user"),rs.getString("nama"),rs.getString("username"),rs.getString("role"));

            SessionUser.id = user.getId();
            SessionUser.nama = user.getNama();
            SessionUser.username = user.getUsername();
            SessionUser.role = user.getRole();

            return user;

        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Gagal koneksi database : "+ e.getMessage(),e);
        }
    }

    private void validateInput(
            String username,
            String password
    ) {

        if (username == null || username.trim().isEmpty()) {
            throw new RuntimeException("Username tidak boleh kosong!");
        }

        if (password == null || password.isEmpty()) {
            throw new RuntimeException("Password tidak boleh kosong!");
        }
    }
}
