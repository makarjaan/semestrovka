package ru.kpfu.itis304.service;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.enums.ApartmentStatus;

import java.sql.Timestamp;
import java.util.List;

public interface ApartmentService<T> {

    void saveApaptInBd(T apartmentDto);

    List<ApartmentRentDto> getApartmentRentListByUserId(Integer id);

    List<ApartmentSaleDto> getApartmentSaleListByUserId(Integer id);

    Integer getApartId(T apartmentDto);

    Timestamp getCreatedTimeRent(Integer apartmentId);

    Timestamp getCreatedTimeSale(Integer apartmentId);

    void deleteApartmentRentById(Integer apartmentId);

    void deleteApartmentSaleById(Integer apartmentId);

    List<ApartmentRentDto> getAllApartmentRent();

    List<ApartmentSaleDto> getAllApartmentSale();

    ApartmentRentDto getApartmentRentInfo(Integer apartmentId);

    ApartmentSaleDto getApartmentSaleInfo(Integer apartmentId);

    String getRoomCountDisplayValue(String roomCount);

    String getPropertyTypeDisplayValue(String propertyType);

    String getRentTypeValue(String rentType);

    String getUserPhone(T apartmentDto, Integer apartId, String type);

    void updateStatus(Integer apartmentId, String type, ApartmentStatus status);

    List<Object> getAllApartments();

    String getDate(Timestamp timestamp);

    List<ApartmentRentDto> getAllRentLongType();

    List<ApartmentSaleDto> getAllRentShortType();

    Integer convertRoomCountToInt(String roomCount);

    List<ApartmentSaleDto> filterApartmentsSale(Integer priceMin, Integer priceMax, String address,
                                                String rooms, String propertyType);

    List<ApartmentRentDto> filterApartmentsShortRent(Integer priceMin, Integer priceMax, String address,
                                                     String rooms, String propertyType);

    List<ApartmentRentDto> filterApartmentsLongRent(Integer priceMin, Integer priceMax, String address,
                                                     String rooms, String propertyType);

}
