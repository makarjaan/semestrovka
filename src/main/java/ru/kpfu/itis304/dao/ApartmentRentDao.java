package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.ApartmentRent;

import java.util.List;


public interface ApartmentRentDao {

    void save(ApartmentRent apartmentRent);

    List<ApartmentRent> getRentList(Integer user_id);

}
