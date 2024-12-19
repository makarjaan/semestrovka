package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.ApartPhoto;

import java.util.List;

public interface ApartPhotoDao {

    void addApartPhoto(ApartPhoto apartPhoto);

    void deleteByApartId(Integer apartId);

    String getMainUrlPhotoSale(Integer apartId);

    String getMainUrlPhotoRent(Integer apartId);

    List<String> getApartmentPhotosSale(Integer apartment_id);

    List<String> getApartmentPhotosRent(Integer apartment_id);

}
