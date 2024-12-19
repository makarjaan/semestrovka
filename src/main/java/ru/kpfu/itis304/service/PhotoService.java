package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.ApartPhotoDto;
import ru.kpfu.itis304.entity.ApartPhoto;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PhotoService<T> {

    String uploadPhoto(File file, String filename) throws IOException;

    File makeFile(Part part, String filename) throws IOException;

    void addApartPhoto(ApartPhotoDto apartPhotoDto);

    void deleteApartPhoto(Integer apartId);

    String getMainPhoto(Integer apartId, String type);

    List<String> getPhotosByApartmentId(Integer apartId, String type);
}
