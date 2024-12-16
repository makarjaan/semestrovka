package ru.kpfu.itis304.service.impl;

import ru.kpfu.itis304.dao.ApartmentRentDao;
import ru.kpfu.itis304.dao.ApartmentSaleDao;
import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRentDao apartmentRentDao;
    private final ApartmentSaleDao apartmentSaleDao;

    public ApartmentServiceImpl(ApartmentRentDao apartmentRentDao, ApartmentSaleDao apartmentSaleDao) {
        this.apartmentRentDao = apartmentRentDao;
        this.apartmentSaleDao = apartmentSaleDao;
    }


    @Override
    public void saveApaptRentInBd(ApartmentRentDto apartmentRentDto) {
        apartmentRentDao.save(new ApartmentRent(
                apartmentRentDto.getUserId(),
                apartmentRentDto.getTitle(),
                apartmentRentDto.getDescription(),
                apartmentRentDto.getType(),
                apartmentRentDto.getRoomsCount(),
                apartmentRentDto.getArea(),
                apartmentRentDto.getStatus(),
                apartmentRentDto.getAddress(),
                apartmentRentDto.getRentType(),
                apartmentRentDto.getPriceRent()
        ));
    }

    @Override
    public void saveApaptSaleInBd(ApartmentSaleDto apartmentSaleDto) {
        apartmentSaleDao.save(new ApartmentSale(
                apartmentSaleDto.getUserId(),
                apartmentSaleDto.getTitle(),
                apartmentSaleDto.getDescription(),
                apartmentSaleDto.getType(),
                apartmentSaleDto.getRoomsCount(),
                apartmentSaleDto.getArea(),
                apartmentSaleDto.getStatus(),
                apartmentSaleDto.getAddress(),
                apartmentSaleDto.getPriceSale()
        ));
    }

    @Override
    public List<ApartmentRentDto> getApartRentListById(Integer id) {
        List<ApartmentRent> rentLists = apartmentRentDao.getRentList(id);
        List<ApartmentRentDto> rentListsDto = new ArrayList<>();
        for (int i = 0; i < rentLists.size(); i++) {
            ApartmentRent apartmentRent = rentLists.get(i);
            rentListsDto.add(new ApartmentRentDto(apartmentRent.getUserId(), apartmentRent.getTitle(),
                    apartmentRent.getDescription(), apartmentRent.getType(), apartmentRent.getRoomsCount(),
                    apartmentRent.getArea(), ApartmentStatus.fromString(apartmentRent.getStatus()), apartmentRent.getAddress(),
                    apartmentRent.getRentType(), apartmentRent.getPriceRent()));
        }
        return rentListsDto;
    }

    @Override
    public List<ApartmentSaleDto> getApartSaleListById(Integer id) {
        List<ApartmentSale> saleLists = apartmentSaleDao.getSaleList(id);
        List<ApartmentSaleDto> saleListsDto = new ArrayList<>();
        for (int i = 0; i < saleLists.size(); i++) {
            ApartmentSale apartmentSale = saleLists.get(i);
            saleListsDto.add(new ApartmentSaleDto(apartmentSale.getUserId(), apartmentSale.getTitle(),
                    apartmentSale.getDescription(), apartmentSale.getType(), apartmentSale.getRoomsCount(),
                    apartmentSale.getArea(), ApartmentStatus.fromString(apartmentSale.getStatus()), apartmentSale.getAddress(),
                    apartmentSale.getPriceSale()));
        }
        return saleListsDto;
    }
}
