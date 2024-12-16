package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.ApartmentSaleDao;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentSaleDaoImpl implements ApartmentSaleDao {

    private final Connection connection;

    public ApartmentSaleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ApartmentSale apartmentSale) {
        try {
            String sql = "INSERT INTO apartment_sale (user_id, title, description, type_of_apartment, rooms_count, area, status, address, price_sale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentSale.getUserId());
            statement.setString(2, apartmentSale.getTitle());
            statement.setString(3, apartmentSale.getDescription());
            statement.setString(4, apartmentSale.getType());
            statement.setString(5, apartmentSale.getRoomsCount());
            statement.setInt(6, apartmentSale.getArea());
            statement.setString(7, apartmentSale.getStatus());
            statement.setString(8, apartmentSale.getAddress());
            statement.setInt(9, apartmentSale.getPriceSale());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentSale> getSaleList(Integer user_id) {
        try {
            String sql = "SELECT * FROM apartment_sale WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            List<ApartmentSale> apartmentSales = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    apartmentSales.add(new ApartmentSale(
                            result.getInt("id"),
                            result.getInt("user_id"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("type_of_apartment"),
                            result.getString("rooms_count"),
                            result.getInt("area"),
                            result.getString("status"),
                            result.getString("address"),
                            result.getInt("price_sale")
                    ));
                }
            }
            return apartmentSales;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
