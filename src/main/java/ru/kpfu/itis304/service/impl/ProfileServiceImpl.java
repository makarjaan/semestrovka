package ru.kpfu.itis304.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dao.impl.UserDaoImpl;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.entity.User;
import ru.kpfu.itis304.service.ProfileService;
import ru.kpfu.itis304.utils.CloudinaryUtil;
import ru.kpfu.itis304.utils.PasswordUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class ProfileServiceImpl implements ProfileService {

    private final UserDao userDao;
    private final static Logger LOG = Logger.getLogger(ProfileServiceImpl.class.getName());

    public ProfileServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void updatePhoto(UserDto userDto, String fileUrl) {
        User u = userDao.getByEmail(userDto.getEmail());
        u.setProfilePhotoUrl(fileUrl);
        userDao.updateProfilePhoto(u);
    }

    @Override
    public void updateUserName(UserDto userDto, String userName) {
        User u = userDao.getByEmail(userDto.getEmail());
        u.setName(userName);
        userDao.updateProfileUserName(u);
    }

    @Override
    public Boolean checkPassword(UserDto userDto, String password) {
        User u = userDao.getByEmail(userDto.getEmail());
        return userDao.checkCurrentPassword(u, PasswordUtil.encrypt(password));
    }

    @Override
    public void changePassword(UserDto userDto, String newPassword) {
        User u = userDao.getByEmail(userDto.getEmail());
        u.setPassword(PasswordUtil.encrypt(newPassword));
        userDao.changePassword(u, PasswordUtil.encrypt(newPassword));
    }
}
