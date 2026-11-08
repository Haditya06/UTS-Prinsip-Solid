/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.klinikhewan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author attau
 */
public class KlinikHewan {

    public static void main(String[] args) {
        System.out.println("Hello World!");
         String url = "jdbc:mysql://localhost:3306/database hazzelnut";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(
                url, user, password
            );

            System.out.println("Koneksi database berhasil!");

            conn.close();

        } catch (SQLException e) {
            System.out.println("Koneksi database gagal!");
            e.printStackTrace();
        }
    }
}
