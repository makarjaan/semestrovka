package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.ApartmentSale;

import java.util.List;

public interface ApartmentSaleDao {

    void save(ApartmentSale apartmentSale);

    List<ApartmentSale> getSaleList(Integer user_id);
}
