package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.ApartmentRentDao;
import ru.kpfu.itis304.entity.ApartmentRent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentRentDaoImpl implements ApartmentRentDao {

    private final Connection connection;

    public ApartmentRentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ApartmentRent apartmentRent) {
        try {
            String sql = "INSERT INTO apartment_rent (user_id, title, description, type_of_apartment, rooms_count, area, status, address, type_of_rent, price_rent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentRent.getUserId());
            statement.setString(2, apartmentRent.getTitle());
            statement.setString(3, apartmentRent.getDescription());
            statement.setString(4, apartmentRent.getType());
            statement.setString(5, apartmentRent.getRoomsCount());
            statement.setInt(6, apartmentRent.getArea());
            statement.setString(7, apartmentRent.getStatus());
            statement.setString(8, apartmentRent.getAddress());
            statement.setString(9, apartmentRent.getRentType());
            statement.setInt(10, apartmentRent.getPriceRent());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentRent> getRentList(Integer user_id) {
        try {
            String sql = "SELECT * FROM apartment_rent WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            List<ApartmentRent> apartmentRents = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    apartmentRents.add(new ApartmentRent(
                            result.getInt("id"),
                            result.getInt("user_id"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("type_of_apartment"),
                            result.getString("rooms_count"),
                            result.getInt("area"),
                            result.getString("status"),
                            result.getString("address"),
                            result.getString("type_of_rent"),
                            result.getInt("price_rent")
                    ));
                }
            }
            return apartmentRents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
