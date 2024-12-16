package ru.kpfu.itis304.service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public interface PhotoService {

    String uploadProfilePhoto(File file, String filename) throws IOException;

    File makeFile(Part part, String filename) throws IOException;
}
