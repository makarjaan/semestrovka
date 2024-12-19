package ru.kpfu.itis304.service;


import ru.kpfu.itis304.dto.FavoritesDto;

import java.util.List;

public interface FavoriteService {

    List<FavoritesDto> getFavoritesById(Integer id);

    boolean toggleFavorite(Integer userId, Integer apartmentId, String type);

    boolean isApartmentInFavorites(Integer userId, Integer apartmentId, String type);

}
