package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;

import java.util.List;

public interface ApartmentService {

    void saveApaptRentInBd(ApartmentRentDto apartmentRentDto);

    void saveApaptSaleInBd(ApartmentSaleDto apartmentSaleDto);

    List<ApartmentRentDto> getApartRentListById(Integer id);

    List<ApartmentSaleDto> getApartSaleListById(Integer id);

}
