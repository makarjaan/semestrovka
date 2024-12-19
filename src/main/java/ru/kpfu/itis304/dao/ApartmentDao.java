package ru.kpfu.itis304.dao;


import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;

import java.sql.Timestamp;
import java.util.List;

public interface ApartmentDao<T> {

    void save(T apartment);

    Integer getApartmentId(T apartment);

    List<T> getListByUserId(Integer user_id);

    Timestamp getCreatedTime(Integer apartment_id);

    void delete(Integer apartment_id);

    List<T> getAllList();

    T getApartmentById(Integer apartment_id);

    String getUserPhone(T apartment, Integer apartId);

    void updateStatus(Integer apartmentId, String status);

    List<ApartmentRent> getRent(String typeRent);

    List<ApartmentSale> filterApartmentsSale(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType);

    List<ApartmentRent> filterApartmentsRent(String type, Integer priceMin, Integer priceMax, String address, String rooms, String propertyType);

}
