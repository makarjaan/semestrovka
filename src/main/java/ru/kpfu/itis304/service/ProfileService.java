package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.UserDto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ProfileService {

    void updatePhoto(UserDto userDto, String fileUrl);

    void updateUserName(UserDto userDto, String userName);

    Boolean checkPassword(UserDto userDto, String password);

    void changePassword(UserDto userDto, String newPassword);

}
