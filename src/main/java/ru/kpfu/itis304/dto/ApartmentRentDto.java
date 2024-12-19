package ru.kpfu.itis304.dto;

import ru.kpfu.itis304.enums.ApartmentStatus;

import java.time.LocalDateTime;
import java.util.Date;

public class ApartmentRentDto {
    private int userId;  // Идентификатор пользователя (владельца квартиры)
    private String title;  // Заголовок объявления о квартире
    private String description;  // Описание квартиры
    private String type;  // Тип квартиры (например, студия, 2-комнатная)
    private String roomsCount;  // Количество комнат
    private int area;  // Площадь квартиры в м²
    private String status; //в модерации, опубликован, забронирован, выключен или чё уже уф сколько ещё
    private String address;  // Полный адрес квартиры
    private String rentType; //вид аренды
    private int priceRent;  // Цена за аренду


    public ApartmentRentDto(int userId, String title, String description,
                            String type, String roomsCount, int area,
                            ApartmentStatus status, String address, String rentType, int priceRent) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.roomsCount = roomsCount;
        this.area = area;
        this.status = status.getApartStatus();
        this.address = address;
        this.rentType = rentType;
        this.priceRent = priceRent;
    }


    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getRoomsCount() {
        return roomsCount;
    }

    public int getArea() {
        return area;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public String getRentType() {
        return rentType;
    }

    public int getPriceRent() {
        return priceRent;
    }
}
