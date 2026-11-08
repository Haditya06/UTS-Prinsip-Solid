package service;

import java.util.List;
import modul.UserItem;
import repository.UserRepository;
import validation.UserValidator;

/**
 * Menangani aturan bisnis pengguna dan bergantung pada abstraksi repository.
 */
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserValidator validator;

    public UserServiceImpl(UserRepository userRepository, UserValidator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<UserItem> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<UserItem> searchUser(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return getAllUser();
        return userRepository.search(keyword);
    }

    @Override
    public int getIdByUsername(String username) {
        return userRepository.findIdByUsername(username);
    }

    @Override
    public void addUser(UserItem user) {
        validator.validateNewUser(user);
        if (userRepository.existsByUsername(user.getUsername().trim()))
            throw new IllegalArgumentException("Username sudah digunakan!");
        userRepository.insert(user);
    }

    @Override
    public void updateUser(UserItem user, String newPassword) {
        validator.validateUpdate(user, newPassword);
        userRepository.update(user, newPassword);
    }

    @Override
    public void deleteUser(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID pengguna tidak valid.");
        userRepository.delete(id);
    }
}
