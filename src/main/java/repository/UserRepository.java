package repository;

import java.util.List;
import modul.UserItem;

/**
 * Abstraksi akses data pengguna. Service tidak perlu mengetahui SQL/JDBC.
 */
public interface UserRepository {
    List<UserItem> findAll();
    List<UserItem> search(String keyword);
    int findIdByUsername(String username);
    boolean existsByUsername(String username);
    void insert(UserItem user);
    void update(UserItem user, String newPassword);
    void delete(int id);
}
