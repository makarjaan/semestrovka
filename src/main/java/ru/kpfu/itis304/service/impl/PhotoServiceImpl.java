package ru.kpfu.itis304.service.impl;


import com.cloudinary.Cloudinary;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.service.PhotoService;
import ru.kpfu.itis304.utils.CloudinaryUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Map;

@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class PhotoServiceImpl implements PhotoService {

    private final Cloudinary cloudinary;

    public PhotoServiceImpl() {
        this.cloudinary = CloudinaryUtil.getInstance();
    }


    @Override
    public String uploadProfilePhoto(File file, String filename) throws IOException {
        Map<String, Object> uploadParams = Map.of("public_id", filename);
        Map uploadResult = cloudinary.uploader().upload(file, uploadParams);
        return (String) uploadResult.get("secure_url");
    }

    @Override
    public File makeFile(Part part, String filename) throws IOException {
        File tempFile = File.createTempFile("profile_", filename);

        try (InputStream content = part.getInputStream();
             OutputStream out = Files.newOutputStream(tempFile.toPath())) {
            byte[] buffer = new byte[1024];
            int count;
            while ((count = content.read(buffer)) != -1) {
                out.write(buffer, 0, count);
            }
        }
        return tempFile;
    }


}
