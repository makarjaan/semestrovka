package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.ApartPhotoDao;
import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.entity.ApartPhoto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartPhotoDaoImpl implements ApartPhotoDao {

    private final Connection connection;

    public ApartPhotoDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addApartPhoto(ApartPhoto apartPhoto) {
        try {
            String sql = "INSERT INTO apartment_photos (photo_url, type_of_advert, apartment_rent_id, apartment_sale_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, apartPhoto.getPhotoUrl());
            statement.setString(2, apartPhoto.getTypeApart());
            if ("rent".equals(apartPhoto.getTypeApart())) {
                statement.setInt(3, apartPhoto.getApartId());
                statement.setNull(4, java.sql.Types.INTEGER);
            } else if ("sale".equals(apartPhoto.getTypeApart())) {
                statement.setNull(3, java.sql.Types.INTEGER);
                statement.setInt(4, apartPhoto.getApartId());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteByApartId(Integer apartId) {
        try {
            String sql = "DELETE FROM apartment_photos WHERE apartment_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMainUrlPhotoSale(Integer apartId) {
        try {
            String sql = "SELECT * FROM apartment_photos WHERE apartment_sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartId);
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    return result.getString("photo_url");
                }
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getMainUrlPhotoRent(Integer apartId) {
        try {
            String sql = "SELECT * FROM apartment_photos WHERE apartment_rent_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartId);
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    return result.getString("photo_url");
                }
            }
            statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<String> getApartmentPhotosSale(Integer apartment_id) {
        try {
            String sql = "SELECT * FROM apartment_photos WHERE apartment_sale_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            List<String> apartPhotos = new ArrayList<>();
            if (result != null) {
                while (result.next()) {
                    apartPhotos.add(result.getString("photo_url"));
                }
            }
            return apartPhotos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getApartmentPhotosRent(Integer apartment_id) {
        try {
            String sql = "SELECT * FROM apartment_photos WHERE apartment_rent_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            List<String> apartPhotos = new ArrayList<>();
            if (result != null) {
                while (result.next()) {
                    apartPhotos.add(result.getString("photo_url"));
                }
            }
            return apartPhotos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
