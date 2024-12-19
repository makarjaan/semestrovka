package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.ApartmentDao;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentRentDaoImpl implements ApartmentDao<ApartmentRent> {

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
    public Integer getApartmentId(ApartmentRent apartment) {
        try {
            String sql = "SELECT * FROM apartment_rent WHERE user_id = ? AND title = ? AND description = ? AND type_of_apartment = ? AND rooms_count = ? AND area = ? AND status = ? AND address = ? AND type_of_rent = ? AND price_rent = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment.getUserId());
            statement.setString(2, apartment.getTitle());
            statement.setString(3, apartment.getDescription());
            statement.setString(4, apartment.getType());
            statement.setString(5, apartment.getRoomsCount());
            statement.setInt(6, apartment.getArea());
            statement.setString(7, apartment.getStatus());
            statement.setString(8, apartment.getAddress());
            statement.setString(9, apartment.getRentType());
            statement.setInt(10, apartment.getPriceRent());
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    return result.getInt("id");
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentRent> getListByUserId(Integer user_id) {
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

    @Override
    public Timestamp getCreatedTime(Integer apartment_id) {
        try {
            String sql = "SELECT created_at FROM apartment_rent WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    return result.getTimestamp("created_at");
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer apartment_id) {
        try {
            String sql = "DELETE FROM apartment_rent WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentRent> getAllList() {
        try {
            String sql = "SELECT * FROM apartment_rent ORDER BY created_at DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<ApartmentRent> apartmentRents = new ArrayList<>();
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

    @Override
    public ApartmentRent getApartmentById(Integer apartment_id) {
        try {
            String sql = "SELECT * FROM apartment_rent WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            if (result != null ) {
                while (result.next()) {
                    return new ApartmentRent(
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
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getUserPhone(ApartmentRent apartment, Integer apartId) {
        try {
            String phone = "";
            String sql = "SELECT users.phone " +
                    "FROM apartment_rent " +
                    "INNER JOIN users ON apartment_rent.user_id = users.id " +
                    "WHERE apartment_rent.id = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    phone = resultSet.getString("phone");
                }
            }
            return phone;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatus(Integer apartmentId, String status) {
        try {
            String sql = "UPDATE apartment_rent SET status = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, apartmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentRent> getRent(String typeRent) {
        try {
            String sql = "SELECT * FROM apartment_rent WHERE type_of_rent = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, typeRent);
            ResultSet result = statement.executeQuery();
            List<ApartmentRent> apartmentRents = new ArrayList<>();
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
                            result.getInt("price_rent"))
                    );
                }
            }
            return apartmentRents;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentSale> filterApartmentsSale(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        return List.of();
    }

    @Override
    public List<ApartmentRent> filterApartmentsRent(String typeRent, Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM apartment_rent WHERE 1=1");
            List<Object> params = new ArrayList<>();

            if (typeRent != null) {
                query.append(" AND type_of_rent = ?");
                params.add(typeRent);
            }
            if (priceMin != null) {
                query.append(" AND price_rent >= ?");
                params.add(priceMin);
            }
            if (priceMax != null) {
                query.append(" AND price_rent <= ?");
                params.add(priceMax);
            }
            if (address != null && !address.isEmpty()) {
                query.append(" AND address LIKE ?");
                params.add("%" + address + "%");
            }
            if (rooms != null && !rooms.isEmpty()) {
                query.append(" AND rooms_count = ?");
                params.add(rooms);
            }
            if (propertyType != null && !propertyType.isEmpty()) {
                query.append(" AND type_of_apartment = ?");
                params.add(propertyType);
            }

            PreparedStatement statement = connection.prepareStatement(query.toString());

            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof String) {
                    statement.setString(i + 1, (String) param);
                } else if (param instanceof Integer) {
                    statement.setInt(i + 1, (Integer) param);
                } else {
                    statement.setObject(i + 1, param);
                }
            }

            ResultSet result = statement.executeQuery();
            List<ApartmentRent> apartmentRents = new ArrayList<>();
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
                            result.getInt("price_rent"))
                    );
                }
            }
            return apartmentRents;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
