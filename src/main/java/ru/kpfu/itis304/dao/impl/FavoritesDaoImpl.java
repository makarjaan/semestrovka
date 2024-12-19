package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.FavoritesDao;
import ru.kpfu.itis304.entity.Favorites;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoritesDaoImpl implements FavoritesDao {

    private final Connection connection;

    public FavoritesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Favorites> findByUserId(Integer userId) {
        try {
            String sql = "SELECT * FROM favorites WHERE user_id = ?";
            List<Favorites> favoritesList = new ArrayList<Favorites>();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    favoritesList.add(new Favorites(
                            result.getInt("user_id"),
                            result.getInt("apartment_sale_id"),
                            result.getInt("apartment_rent_id"))
                    );
                }
            }
            return favoritesList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean toggleFavoriteSale(Integer userId, Integer apartmentSaleId) {
        try {
            String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND apartment_sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, apartmentSaleId);
            ResultSet result = statement.executeQuery();
            if (result.next() && result.getInt(1) > 0) {
                PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM favorites WHERE user_id = ? AND apartment_sale_id = ?");
                deleteStmt.setInt(1, userId);
                deleteStmt.setInt(2, apartmentSaleId);
                deleteStmt.executeUpdate();
                return false;
            } else {
                PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO favorites (user_id, apartment_sale_id) VALUES (?, ?)");
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, apartmentSaleId);
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean toggleFavoriteRent(Integer userId, Integer apartmentRentId) {
        try {
            String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND apartment_rent_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, apartmentRentId);
            ResultSet result = statement.executeQuery();
            if (result.next() && result.getInt(1) > 0) {
                PreparedStatement deleteStmt = connection.prepareStatement("DELETE FROM favorites WHERE user_id = ? AND apartment_rent_id = ?");
                deleteStmt.setInt(1, userId);
                deleteStmt.setInt(2, apartmentRentId);
                deleteStmt.executeUpdate();
                return false;
            } else {
                PreparedStatement insertStmt = connection.prepareStatement("INSERT INTO favorites (user_id, apartment_rent_id) VALUES (?, ?)");
                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, apartmentRentId);
                insertStmt.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isFavoriteSale(Integer userId, Integer apartmentSaleId) {
        try {
            String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND apartment_sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, apartmentSaleId);
            ResultSet result = statement.executeQuery();
            return result.next() && result.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isFavoriteRent(Integer userId, Integer apartmentRentId) {
        try {
            String sql = "SELECT COUNT(*) FROM favorites WHERE user_id = ? AND apartment_rent_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, apartmentRentId);
            ResultSet result = statement.executeQuery();
            return result.next() && result.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
