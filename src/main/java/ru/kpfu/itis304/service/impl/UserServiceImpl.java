package ru.kpfu.itis304.service.impl;

import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.entity.User;
import ru.kpfu.itis304.service.UserService;
import ru.kpfu.itis304.utils.PasswordUtil;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;


public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto getByEmailAndPassword(String email, String password) {
        User u = userDao.getByEmailAndPassword(email, PasswordUtil.encrypt(password));
        return new UserDto(u.getName(), u.getEmail(), u.getPhone(), null, u.getProfilePhotoUrl());
    }

    @Override
    public UserDto getByEmail(String email) {
        User u = userDao.getByEmail(email);
        return new UserDto(u.getName(), u.getEmail(), u.getPhone(), null, u.getProfilePhotoUrl());
    }

    @Override
    public Integer getId(UserDto userDto) {
        return userDao.getId(userDto.getEmail());
    }

    @Override
    public void registerUser(UserDto userDto) {
        userDao.save(new User(
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                PasswordUtil.encrypt(userDto.getPassword()),
                userDto.getProfilePhotoUrl()
        ));
    }

    @Override
    public void deleteUser(UserDto userDto, HttpServletRequest req) {
        User u = userDao.getByEmail(userDto.getEmail());
        userDao.deleteUser(u);
        HttpSession session = req.getSession(false);
        session.invalidate();
    }

    @Override
    public void authenticateUser(UserDto userDto, HttpServletRequest req, HttpServletResponse resp) {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user", userDto);
        httpSession.setMaxInactiveInterval(60 * 60);
    }

    @Override
    public boolean addDatabase(String email, String password) {
        return userDao.addDatabase(email, PasswordUtil.encrypt(password));
    }

}
