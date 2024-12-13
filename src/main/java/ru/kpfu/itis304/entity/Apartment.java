package ru.kpfu.itis304.entity;

import java.util.Date;

public class Apartment {
    private int id;  // Уникальный идентификатор квартиры
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
    private String address;  // Полный адрес квартиры (например, "Москва, ул. Ленина, 15")

    public Apartment(int id, int userId, String title, String description,
                     String city, double pricePerDay, double pricePerMonth,
                     String type, int roomsCount, double area, Date availableFrom,
                     Date availableUntil, Date createdAt, Date updatedAt,
                     int streetId, String houseNumber, String status, String address) {
        this.id = id;
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

    public Apartment(int userId, String title, String description,
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
