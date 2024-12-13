package ru.kpfu.itis304.dto;

import java.util.Date;

public class ApartmentDto {
    private int userId;  // Идентификатор пользователя (владельца квартиры)
    private String title;  // Заголовок объявления о квартире
    private String description;  // Описание квартиры
    private String city;  // Город
    private double pricePerDay;  // Цена за день аренды
    private double pricePerMonth;  // Цена за месяц аренды
    private String type;  // Тип квартиры (например, студия, 2-комнатная)
    private int roomsCount;  // Количество комнат
    private double area;  // Площадь квартиры в м²
    private Date availableFrom;  // Дата начала доступности квартиры
    private Date availableUntil;  // Дата окончания доступности квартиры
    private Date createdAt;  // Дата создания объявления
    private Date updatedAt;  // Дата последнего обновления объявления
    private int streetId;  // Идентификатор улицы
    private String houseNumber;  // Номер дома
    private String status; //
    private String address;  // Полный адрес квартиры

    public ApartmentDto(int userId, String title, String description,
                        String city, double pricePerDay, double pricePerMonth,
                        String type, int roomsCount, double area,
                        Date availableFrom, Date availableUntil,
                        Date createdAt, Date updatedAt, int streetId,
                        String houseNumber, String status, String address) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.city = city;
        this.pricePerDay = pricePerDay;
        this.pricePerMonth = pricePerMonth;
        this.type = type;
        this.roomsCount = roomsCount;
        this.area = area;
        this.availableFrom = availableFrom;
        this.availableUntil = availableUntil;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.streetId = streetId;
        this.houseNumber = houseNumber;
        this.status = status;
        this.address = address;
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

    public String getCity() {
        return city;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public String getType() {
        return type;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public double getArea() {
        return area;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public int getStreetId() {
        return streetId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }
}
