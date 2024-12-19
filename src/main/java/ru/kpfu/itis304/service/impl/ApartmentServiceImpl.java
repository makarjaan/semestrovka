package ru.kpfu.itis304.service.impl;

import ru.kpfu.itis304.dao.ApartmentDao;
import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentDao<ApartmentRent> apartmentRentDao;
    private final ApartmentDao<ApartmentSale> apartmentSaleDao;
    private final static Logger LOG = Logger.getLogger(ApartmentServiceImpl.class.getName());

    public ApartmentServiceImpl(ApartmentDao<ApartmentRent> apartmentRentDao, ApartmentDao<ApartmentSale> apartmentSaleDao) {
        this.apartmentRentDao = apartmentRentDao;
        this.apartmentSaleDao = apartmentSaleDao;
    }

    @Override
    public void saveApaptInBd(Object apartmentDto) {
        if (apartmentDto instanceof ApartmentRentDto) {
            ApartmentRentDto apartmentRentDto = (ApartmentRentDto) apartmentDto;
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
        } else if (apartmentDto instanceof ApartmentSaleDto) {
            ApartmentSaleDto apartmentSaleDto = (ApartmentSaleDto) apartmentDto;
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
    }

    @Override
    public List<ApartmentRentDto> getApartmentRentListByUserId(Integer id) {
        List<ApartmentRent> rentLists = apartmentRentDao.getListByUserId(id);
        List<ApartmentRentDto> rentListsDto = new ArrayList<>();
        for (ApartmentRent apartmentRent : rentLists) {
            rentListsDto.add(new ApartmentRentDto(
                    apartmentRent.getUserId(),
                    apartmentRent.getTitle(),
                    apartmentRent.getDescription(),
                    apartmentRent.getType(),
                    apartmentRent.getRoomsCount(),
                    apartmentRent.getArea(),
                    ApartmentStatus.fromString(apartmentRent.getStatus()),
                    apartmentRent.getAddress(),
                    apartmentRent.getRentType(),
                    apartmentRent.getPriceRent()
            ));
        }
        return rentListsDto;
    }

    @Override
    public List<ApartmentSaleDto> getApartmentSaleListByUserId(Integer id) {
        List<ApartmentSale> saleLists = apartmentSaleDao.getListByUserId(id);
        List<ApartmentSaleDto> saleListsDto = new ArrayList<>();
        for (ApartmentSale apartmentSale : saleLists) {
            saleListsDto.add(new ApartmentSaleDto(
                    apartmentSale.getUserId(),
                    apartmentSale.getTitle(),
                    apartmentSale.getDescription(),
                    apartmentSale.getType(),
                    apartmentSale.getRoomsCount(),
                    apartmentSale.getArea(),
                    ApartmentStatus.fromString(apartmentSale.getStatus()),
                    apartmentSale.getAddress(),
                    apartmentSale.getPriceSale()
            ));
        }
        return saleListsDto;
    }

    @Override
    public Integer getApartId(Object apartmentDto) {
        if (apartmentDto instanceof ApartmentRentDto) {
            ApartmentRentDto apartmentRentDto = (ApartmentRentDto) apartmentDto;
            ApartmentRent apartmentRent = new ApartmentRent(
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
            );
            return apartmentRentDao.getApartmentId(apartmentRent);
        } else if (apartmentDto instanceof ApartmentSaleDto) {
            ApartmentSaleDto apartmentSaleDto = (ApartmentSaleDto) apartmentDto;
            ApartmentSale apartmentSale = new ApartmentSale(
                    apartmentSaleDto.getUserId(),
                    apartmentSaleDto.getTitle(),
                    apartmentSaleDto.getDescription(),
                    apartmentSaleDto.getType(),
                    apartmentSaleDto.getRoomsCount(),
                    apartmentSaleDto.getArea(),
                    apartmentSaleDto.getStatus(),
                    apartmentSaleDto.getAddress(),
                    apartmentSaleDto.getPriceSale()
            );
            return apartmentSaleDao.getApartmentId(apartmentSale);
        }
        return null;
    }

    @Override
    public Timestamp getCreatedTimeRent(Integer apartId) {
        return apartmentRentDao.getCreatedTime(apartId);
    }

    @Override
    public Timestamp getCreatedTimeSale(Integer apartId) {
        return apartmentSaleDao.getCreatedTime(apartId);
    }

    @Override
    public String getDate(Timestamp timestamp) {
        if (timestamp != null) {
            return timestamp.toLocalDateTime().toLocalDate().toString();
        }
        return null;
    }

    @Override
    public void deleteApartmentSaleById(Integer apartmentId) {
        apartmentSaleDao.delete(apartmentId);
    }

    @Override
    public void deleteApartmentRentById(Integer apartmentId) {
        apartmentRentDao.delete(apartmentId);
    }

    @Override
    public List<ApartmentRentDto> getAllApartmentRent() {
        List<ApartmentRent> list = apartmentRentDao.getAllList();
        List<ApartmentRentDto> rentListsDto = new ArrayList<>();
        for (ApartmentRent apartmentRent : list) {
            rentListsDto.add(new ApartmentRentDto(
                    apartmentRent.getUserId(),
                    apartmentRent.getTitle(),
                    apartmentRent.getDescription(),
                    apartmentRent.getType(),
                    apartmentRent.getRoomsCount(),
                    apartmentRent.getArea(),
                    ApartmentStatus.fromString(apartmentRent.getStatus()),
                    apartmentRent.getAddress(),
                    apartmentRent.getRentType(),
                    apartmentRent.getPriceRent())
            );
        }
        return rentListsDto;
    }

    @Override
    public List<ApartmentSaleDto> getAllApartmentSale() {
        List<ApartmentSale> list = apartmentSaleDao.getAllList();
        List<ApartmentSaleDto> rentListsDto = new ArrayList<>();
        for (ApartmentSale apartmentSale : list) {
            rentListsDto.add(new ApartmentSaleDto(
                    apartmentSale.getUserId(),
                    apartmentSale.getTitle(),
                    apartmentSale.getDescription(),
                    apartmentSale.getType(),
                    apartmentSale.getRoomsCount(),
                    apartmentSale.getArea(),
                    ApartmentStatus.fromString(apartmentSale.getStatus()),
                    apartmentSale.getAddress(),
                    apartmentSale.getPriceSale())
            );
        }
        return rentListsDto;
    }

    @Override
    public ApartmentRentDto getApartmentRentInfo(Integer apartmentId) {
        ApartmentRent apartment = apartmentRentDao.getApartmentById(apartmentId);
        return new ApartmentRentDto(apartment.getUserId(), apartment.getTitle(), apartment.getDescription(),
                apartment.getType(), apartment.getRoomsCount(), apartment.getArea(),
                ApartmentStatus.fromString(apartment.getStatus()),
                apartment.getAddress(), apartment.getRentType(), apartment.getPriceRent());
    }

    @Override
    public ApartmentSaleDto getApartmentSaleInfo(Integer apartmentId) {
        ApartmentSale apartment = apartmentSaleDao.getApartmentById(apartmentId);
        return new ApartmentSaleDto(apartment.getUserId(), apartment.getTitle(), apartment.getDescription(),
                apartment.getType(), apartment.getRoomsCount(), apartment.getArea(),
                ApartmentStatus.fromString(apartment.getStatus()),
                apartment.getAddress(), apartment.getPriceSale());
    }

    @Override
    public String getUserPhone(Object apartmentDto, Integer apartId, String type) {
        if (type.equals("sale")) {
            ApartmentSaleDto apartmentSaleDto = (ApartmentSaleDto) apartmentDto;
            ApartmentSale apartmentSale = new ApartmentSale(
                    apartmentSaleDto.getUserId(),
                    apartmentSaleDto.getTitle(),
                    apartmentSaleDto.getDescription(),
                    apartmentSaleDto.getType(),
                    apartmentSaleDto.getRoomsCount(),
                    apartmentSaleDto.getArea(),
                    apartmentSaleDto.getStatus(),
                    apartmentSaleDto.getAddress(),
                    apartmentSaleDto.getPriceSale());
            return apartmentSaleDao.getUserPhone(apartmentSale, apartId);
        } else if (type.equals("rent")) {
            ApartmentRentDto apartmentRentDto = (ApartmentRentDto) apartmentDto;
            ApartmentRent apartmentRent = new ApartmentRent(
                    apartmentRentDto.getUserId(),
                    apartmentRentDto.getTitle(),
                    apartmentRentDto.getDescription(),
                    apartmentRentDto.getType(),
                    apartmentRentDto.getRoomsCount(),
                    apartmentRentDto.getArea(),
                    apartmentRentDto.getStatus(),
                    apartmentRentDto.getAddress(),
                    apartmentRentDto.getRentType(),
                    apartmentRentDto.getPriceRent());
            return apartmentRentDao.getUserPhone(apartmentRent, apartId);
        }
        return null;
    }

    @Override
    public void updateStatus(Integer apartmentId, String type, ApartmentStatus status) {
        if (type.equals("sale")) {
            apartmentSaleDao.updateStatus(apartmentId, status.getApartStatus());
        } else if (type.equals("rent")) {
            apartmentRentDao.updateStatus(apartmentId, status.getApartStatus());
        }
    }

    @Override
    public List<Object> getAllApartments() {
        List<ApartmentSale> listSale = apartmentSaleDao.getAllList();
        List<ApartmentRent> listRent = apartmentRentDao.getAllList();
        List<Object> list = new ArrayList<>();
        for (ApartmentSale sale : listSale) {
            list.add(sale);
        }
        for (ApartmentRent rent : listRent) {
            list.add(rent);
        }
        list.sort((o1, o2) -> {
            Timestamp time1 = (o1 instanceof ApartmentSale)
                    ? getCreatedTimeSale(((ApartmentSale) o1).getId())
                    : getCreatedTimeRent(((ApartmentRent) o1).getId());

            Timestamp time2 = (o2 instanceof ApartmentSale)
                    ? getCreatedTimeSale(((ApartmentSale) o2).getId())
                    : getCreatedTimeRent(((ApartmentRent) o2).getId());
            return time2.compareTo(time1);
        });
        return list;
    }

    @Override
    public List<ApartmentRentDto> getAllRentLongType() {
        List<ApartmentRent> listRent = apartmentRentDao.getAllList();
        listRent = apartmentRentDao.getRent("Долгосрочно");
        List<ApartmentRentDto> rentListsDto = new ArrayList<>();
        for (ApartmentRent apartmentRent : listRent) {
            rentListsDto.add(new ApartmentRentDto(
                    apartmentRent.getUserId(),
                    apartmentRent.getTitle(),
                    apartmentRent.getDescription(),
                    apartmentRent.getType(),
                    apartmentRent.getRoomsCount(),
                    apartmentRent.getArea(),
                    ApartmentStatus.fromString(apartmentRent.getStatus()),
                    apartmentRent.getAddress(),
                    apartmentRent.getRentType(),
                    apartmentRent.getPriceRent())
            );
        }
        return rentListsDto;
    }


    @Override
    public List<ApartmentRentDto> getAllRentShortType() {
        List<ApartmentRent> listRent = apartmentRentDao.getAllList();
        listRent = apartmentRentDao.getRent("Посуточно");
        List<ApartmentRentDto> rentListsDto = new ArrayList<>();
        for (ApartmentRent apartmentRent : listRent) {
            rentListsDto.add(new ApartmentRentDto(
                    apartmentRent.getUserId(),
                    apartmentRent.getTitle(),
                    apartmentRent.getDescription(),
                    apartmentRent.getType(),
                    apartmentRent.getRoomsCount(),
                    apartmentRent.getArea(),
                    ApartmentStatus.fromString(apartmentRent.getStatus()),
                    apartmentRent.getAddress(),
                    apartmentRent.getRentType(),
                    apartmentRent.getPriceRent())
            );
        }
        return rentListsDto;
    }

    @Override
    public String getRoomCountDisplayValue(String roomCount) {
        switch (roomCount) {
            case "studio": return "Студия";
            case "one-bedroom": return "1-комнатная";
            case "two-bedroom": return "2-комнатная";
            case "three-bedroom": return "3-комнатная";
            case "four-bedroom": return "4-комнатная";
            case "five-plus-bedroom": return "5+ комнат";
            default: return "Неизвестно";
        }
    }

    @Override
    public String getPropertyTypeDisplayValue(String propertyType) {
        switch (propertyType) {
            case "apartmentSale": return "Квартира";
            case "house": return "Дом";
            case "room": return "Комната";
            default: return "Неизвестно";
        }
    }

    @Override
    public String getRentTypeValue(String rentType) {
        switch (rentType) {
            case "short-term": return "Посуточно";
            case "long-term": return "Долгосрочно";
            default: return "Неизвестно";
        }
    }

    @Override
    public Integer convertRoomCountToInt(String roomCount) {
        switch (roomCount) {
            case "Студия":
                return 0;
            case "1-комнатная":
                return 1;
            case "2-комнатная":
                return 2;
            case "3-комнатная":
                return 3;
            case "4-комнатная":
                return 4;
            case "5+ комнат":
                return 5;
            default:
                return null;
        }
    }


    @Override
    public List<ApartmentSaleDto> filterApartmentsSale(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        List<ApartmentSale> apartmentSale = apartmentSaleDao.filterApartmentsSale(priceMin, priceMax, address, rooms, propertyType);
        List<ApartmentSaleDto> apartmentSaleDtos = new ArrayList<>();
        for (ApartmentSale apartment : apartmentSale) {
            apartmentSaleDtos.add(new ApartmentSaleDto(
                    apartment.getUserId(),
                    apartment.getTitle(),
                    apartment.getDescription(),
                    apartment.getType(),
                    apartment.getRoomsCount(),
                    apartment.getArea(),
                    ApartmentStatus.fromString(apartment.getStatus()),
                    apartment.getAddress(),
                    apartment.getPriceSale())
            );
        }
        return apartmentSaleDtos;
    }


    @Override
    public List<ApartmentRentDto> filterApartmentsShortRent(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        List<ApartmentRent> apartmentRents = apartmentRentDao.filterApartmentsRent("Посуточно", priceMin, priceMax, address, rooms, propertyType);
        List<ApartmentRentDto> apartmentRentDtos = new ArrayList<>();
        for (ApartmentRent apartment : apartmentRents) {
            apartmentRentDtos.add(new ApartmentRentDto(
                    apartment.getUserId(),
                    apartment.getTitle(),
                    apartment.getDescription(),
                    apartment.getType(),
                    apartment.getRoomsCount(),
                    apartment.getArea(),
                    ApartmentStatus.fromString(apartment.getStatus()),
                    apartment.getAddress(),
                    apartment.getRentType(),
                    apartment.getPriceRent()
            ));
        }
        return apartmentRentDtos;
    }

    @Override
    public List<ApartmentRentDto> filterApartmentsLongRent(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        List<ApartmentRent> apartmentRents = apartmentRentDao.filterApartmentsRent("Долгосрочно", priceMin, priceMax, address, rooms, propertyType);
        for (int i = 0; i < apartmentRents.size(); i++) {
            LOG.info(apartmentRents.get(i).toString());
        }

        List<ApartmentRentDto> apartmentRentDtos = new ArrayList<>();
        for (ApartmentRent apartment : apartmentRents) {
            apartmentRentDtos.add(new ApartmentRentDto(
                    apartment.getUserId(),
                    apartment.getTitle(),
                    apartment.getDescription(),
                    apartment.getType(),
                    apartment.getRoomsCount(),
                    apartment.getArea(),
                    ApartmentStatus.fromString(apartment.getStatus()),
                    apartment.getAddress(),
                    apartment.getRentType(),
                    apartment.getPriceRent()
            ));
        }
        return apartmentRentDtos;
    }
}
