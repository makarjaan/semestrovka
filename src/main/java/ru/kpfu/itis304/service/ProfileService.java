package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.UserDto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ProfileService {

    String uploadProfilePhoto(File file, String filename) throws IOException;

    void updatePhoto(UserDto userDto, String fileUrl);

    void updateUserName(UserDto userDto, String userName);

    Boolean checkPassword(UserDto userDto, String password);

    void changePassword(UserDto userDto, String newPassword);

}
