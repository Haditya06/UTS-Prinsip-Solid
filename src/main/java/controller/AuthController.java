/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import modul.UserItem;
import service.AuthService;

/**
 *
 * @author 
 */

public class AuthController {

    private final AuthService service = new AuthService();

    public UserItem login(String username, String password) {
        return service.login(username, password);
    }
}