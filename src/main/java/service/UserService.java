package service;

import java.util.List;
import modul.UserItem;

/**
 * Kontrak operasi bisnis untuk pengelolaan pengguna.
 */
public interface UserService {
    List<UserItem> getAllUser();
    List<UserItem> searchUser(String keyword);
    int getIdByUsername(String username);
    void addUser(UserItem user);
    void updateUser(UserItem user, String newPassword);
    void deleteUser(int id);
}
