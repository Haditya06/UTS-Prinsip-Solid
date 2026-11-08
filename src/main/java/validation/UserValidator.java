package validation;

import modul.UserItem;

/**
 * Bertanggung jawab khusus pada validasi data pengguna.
 */
public class UserValidator {
    public void validateNewUser(UserItem user) {
        validateName(user.getNama());
        validateUsername(user.getUsername());
        validatePassword(user.getPassword());
        validateRole(user.getRole());
    }

    public void validateUpdate(UserItem user, String newPassword) {
        validateName(user.getNama());
        validateUsername(user.getUsername());
        validateRole(user.getRole());
        if (newPassword != null && !newPassword.isEmpty()) validatePassword(newPassword);
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
    }

    private void validateUsername(String username) {
        if (username == null || username.trim().isEmpty())
            throw new IllegalArgumentException("Username tidak boleh kosong.");
        if (username.trim().length() < 3)
            throw new IllegalArgumentException("Username minimal 3 karakter.");
    }

    private void validatePassword(String password) {
        if (password == null || password.isEmpty())
            throw new IllegalArgumentException("Password tidak boleh kosong.");
    }

    private void validateRole(String role) {
        if (role == null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role harus dipilih.");
    }
}
