package ru.kpfu.itis304.dto;

public class FavoritesDto {
    private int user_id;
    private int apartment_rent_id;
    private int apartment_sale_id;

    public FavoritesDto(int user_id, int apartment_rent_id, int apartment_sale_id) {
        this.user_id = user_id;
        this.apartment_rent_id = apartment_rent_id;
        this.apartment_sale_id = apartment_sale_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getApartment_rent_id() {
        return apartment_rent_id;
    }

    public int getApartment_sale_id() {
        return apartment_sale_id;
    }
}
