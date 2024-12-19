package ru.kpfu.itis304.service.impl;


import com.cloudinary.Cloudinary;
import ru.kpfu.itis304.dao.ApartPhotoDao;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dao.impl.ApartPhotoDaoImpl;
import ru.kpfu.itis304.dto.ApartPhotoDto;
import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.entity.ApartPhoto;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.PhotoService;
import ru.kpfu.itis304.utils.CloudinaryUtil;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class PhotoServiceImpl implements PhotoService {

    private final Cloudinary cloudinary;
    private final ApartPhotoDao apartPhotoDao;

    public PhotoServiceImpl(ApartPhotoDao apartPhotoDao) {
        this.cloudinary = CloudinaryUtil.getInstance();
        this.apartPhotoDao = apartPhotoDao;
    }


    @Override
    public String uploadPhoto(File file, String filename) throws IOException {
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


    @Override
    public void addApartPhoto(ApartPhotoDto apartPhotoDto) {
        ApartPhoto apartPhoto = new ApartPhoto(apartPhotoDto.getApartId(), apartPhotoDto.getPhotoUrl(), apartPhotoDto.getTypeApart());
        apartPhotoDao.addApartPhoto(apartPhoto);
    }

    @Override
    public void deleteApartPhoto(Integer apartId) {
        apartPhotoDao.deleteByApartId(apartId);
    }

    @Override
    public String getMainPhoto(Integer apartId, String type) {
        if (type.equals("sale")) {
            return apartPhotoDao.getMainUrlPhotoSale(apartId);
        } else if (type.equals("rent")) {
            return apartPhotoDao.getMainUrlPhotoRent(apartId);
        }
        return null;
    }

    @Override
    public List<String> getPhotosByApartmentId(Integer apartId, String type) {
        if (type.equals("sale")) {
            return apartPhotoDao.getApartmentPhotosSale(apartId);
        } else if (type.equals("rent")) {
            return apartPhotoDao.getApartmentPhotosRent(apartId);
        }
        return null;
    }
}
