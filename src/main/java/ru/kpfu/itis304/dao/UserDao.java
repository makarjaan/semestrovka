package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.User;

public interface UserDao {

    User getByEmailAndPassword(String email, String password);

    void save(User user);

    boolean addDatabase(String email, String password);

    void updateProfilePhoto(User user);

    void updateProfileUserName(User user);

    User getByEmail(String email);

    Boolean checkCurrentPassword(User user, String curPassword);

    void changePassword(User user, String newPassword);

    void deleteUser(User user);

}
