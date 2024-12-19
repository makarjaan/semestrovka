package ru.kpfu.itis304.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class ApartmentSale {
    private int id;  // Уникальный идентификатор квартиры
    private int userId;  // Идентификатор пользователя (владельца квартиры)
    private String title;  // Заголовок объявления о квартире
    private String description;  // Описание квартиры
    private String type;  // Тип квартиры (например, студия, 2-комнатная)
    private String roomsCount;  // Количество комнат
    private int area;  // Площадь квартиры в м²
    private Date updatedAt;  // Дата последнего обновления объявления
    private String status; //в модерации, опубликован, забронирован, выключен или чё уже уф сколько ещё
    private String address;  // Полный адрес квартиры
    private int priceSale;  // Цена за квартиру


    public ApartmentSale(int id, int userId, String title, String description,
                         String type, String roomsCount, int area, String status, String address, int priceSale) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.roomsCount = roomsCount;
        this.area = area;
        this.status = status;
        this.address = address;
        this.priceSale = priceSale;
    }

    public ApartmentSale(int userId, String title, String description,
                         String type, String roomsCount, int area, String status, String address, int priceSale) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.roomsCount = roomsCount;
        this.area = area;
        this.status = status;
        this.address = address;
        this.priceSale = priceSale;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoomsCount() {
        return roomsCount;
    }

    public void setRoomsCount(String roomsCount) {
        this.roomsCount = roomsCount;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public int getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(int priceSale) {
        this.priceSale = priceSale;
    }
}
