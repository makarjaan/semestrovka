package ru.kpfu.itis304.service.impl;

import ru.kpfu.itis304.dao.FavoritesDao;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dto.FavoritesDto;
import ru.kpfu.itis304.entity.Favorites;
import ru.kpfu.itis304.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private final FavoritesDao favoritesDao;

    public FavoriteServiceImpl(FavoritesDao favoritesDao) {
        this.favoritesDao = favoritesDao;
    }

    @Override
    public List<FavoritesDto> getFavoritesById(Integer id) {
        List<FavoritesDto> favoritesDtoList = new ArrayList<>();
        List<Favorites> favoritesList = favoritesDao.findByUserId(id);
        for (Favorites favorites : favoritesList) {
            favoritesDtoList.add(new FavoritesDto(favorites.getUser_id(), favorites.getApartment_sale_id(),
                    favorites.getApartment_rent_id()));
        }
        return favoritesDtoList;
    }

    @Override
    public boolean toggleFavorite(Integer userId, Integer apartmentId, String type) {
        boolean success = false;
        if ("sale".equals(type)) {
            success = favoritesDao.toggleFavoriteSale(userId, apartmentId);
        } else if ("rent".equals(type)) {
            success = favoritesDao.toggleFavoriteRent(userId, apartmentId);
        }
        return success;
    }

    @Override
    public boolean isApartmentInFavorites(Integer userId, Integer apartmentId, String type) {
        if ("sale".equals(type)) {
            return favoritesDao.isFavoriteSale(userId, apartmentId);
        } else if ("rent".equals(type)) {
            return favoritesDao.isFavoriteRent(userId, apartmentId);
        }
        return false;
    }
}
