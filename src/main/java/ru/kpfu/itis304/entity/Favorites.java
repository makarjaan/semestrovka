package ru.kpfu.itis304.entity;

public class Favorites {
    private int id;
    private int user_id;
    private int apartment_rent_id;
    private int apartment_sale_id;

    public Favorites(int id, int user_id, int apartment_rent_id, int apartment_sale_id) {
        this.id = id;
        this.user_id = user_id;
        this.apartment_rent_id = apartment_rent_id;
        this.apartment_sale_id = apartment_sale_id;
    }

    public Favorites(int user_id, int apartment_rent_id, int apartment_sale_id) {
        this.user_id = user_id;
        this.apartment_rent_id = apartment_rent_id;
        this.apartment_sale_id = apartment_sale_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getApartment_rent_id() {
        return apartment_rent_id;
    }

    public void setApartment_rent_id(int apartment_rent_id) {
        this.apartment_rent_id = apartment_rent_id;
    }

    public int getApartment_sale_id() {
        return apartment_sale_id;
    }

    public void setApartment_sale_id(int apartment_sale_id) {
        this.apartment_sale_id = apartment_sale_id;
    }
}
