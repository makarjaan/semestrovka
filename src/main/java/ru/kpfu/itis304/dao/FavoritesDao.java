package ru.kpfu.itis304.dao;

import ru.kpfu.itis304.entity.Favorites;

import java.util.List;

public interface FavoritesDao {

    List<Favorites> findByUserId(Integer userId);

    boolean toggleFavoriteSale(Integer userId, Integer apartmentSaleId);

    boolean toggleFavoriteRent(Integer userId, Integer apartmentRentId);

    boolean isFavoriteRent(Integer userId, Integer apartmentRentId);

    boolean isFavoriteSale(Integer userId, Integer apartmentRentId);
}
