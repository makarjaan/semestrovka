package ru.kpfu.itis304.dao.impl;

import ru.kpfu.itis304.dao.ApartmentDao;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentSaleDaoImpl implements ApartmentDao<ApartmentSale> {

    private final Connection connection;

    public ApartmentSaleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(ApartmentSale apartmentSale) {
        try {
            String sql = "INSERT INTO apartment_sale (user_id, title, description, type_of_apartment, " +
                    "rooms_count, area, status, address, price_sale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    public Integer getApartmentId(ApartmentSale apartment) {
        try {
            String sql = "SELECT * FROM apartment_sale WHERE user_id = ? AND title = ? " +
                    "AND description = ? AND type_of_apartment = ? AND rooms_count = ? " +
                    "AND area = ? AND status = ? AND address = ? AND  price_sale = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment.getUserId());
            statement.setString(2, apartment.getTitle());
            statement.setString(3, apartment.getDescription());
            statement.setString(4, apartment.getType());
            statement.setString(5, apartment.getRoomsCount());
            statement.setInt(6, apartment.getArea());
            statement.setString(7, apartment.getStatus());
            statement.setString(8, apartment.getAddress());
            statement.setInt(9, apartment.getPriceSale());
            ResultSet result = statement.executeQuery();
            if (result != null) {
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
    public List<ApartmentSale> getListByUserId(Integer user_id) {
        try {
            String sql = "SELECT * FROM apartment_sale WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user_id);
            List<ApartmentSale> apartmentSales = new ArrayList<>();
            ResultSet result = statement.executeQuery();
            if (result != null) {
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


    @Override
    public Timestamp getCreatedTime(Integer apartment_id) {
        try {
            String sql = "SELECT created_at FROM apartment_sale WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            if (result != null) {
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
            String sql = "DELETE FROM apartment_sale WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentSale> getAllList() {
        try {
            String sql = "SELECT * FROM apartment_sale ORDER BY created_at DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            List<ApartmentSale> apartmentSales = new ArrayList<>();
            if (result != null) {
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

    @Override
    public ApartmentSale getApartmentById(Integer apartment_id) {
        try {
            String sql = "SELECT * FROM apartment_sale WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartment_id);
            ResultSet result = statement.executeQuery();
            if (result != null) {
                while (result.next()) {
                    return (new ApartmentSale(
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String getUserPhone(ApartmentSale apartment, Integer apartId) {
        try {
            String phone = "";
            String sql = "SELECT users.phone " +
                    "FROM apartment_sale " +
                    "INNER JOIN users ON apartment_sale.user_id = users.id " +
                    "WHERE apartment_sale.id = ?";

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
            String sql = "UPDATE apartment_sale SET status = ? WHERE id = ?";
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
        return List.of();
    }

    @Override
    public List<ApartmentSale> filterApartmentsSale(Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM apartment_sale WHERE 1=1");

            List<Object> params = new ArrayList<>();


            if (priceMin != null) {
                query.append(" AND price_sale >= ?");
                params.add(priceMin);
            }

            if (priceMax != null) {
                query.append(" AND price_sale <= ?");
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
                statement.setObject(i + 1, params.get(i));
            }

            ResultSet result = statement.executeQuery();
            List<ApartmentSale> apartmentsSale = new ArrayList<>();
            if (result != null) {
                while (result.next()) {
                    apartmentsSale.add(new ApartmentSale(
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
            return apartmentsSale;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ApartmentRent> filterApartmentsRent(String type, Integer priceMin, Integer priceMax, String address, String rooms, String propertyType) {
        return List.of();
    }
}
