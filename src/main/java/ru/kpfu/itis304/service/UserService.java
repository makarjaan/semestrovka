package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    UserDto getByEmailAndPassword(String email, String password);

    void registerUser(UserDto userDto);

    void authenticateUser(UserDto userDto, HttpServletRequest req, HttpServletResponse resp);

    boolean addDatabase(String email, String password);

    UserDto getByEmail(String email);

}

