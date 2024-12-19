package ru.kpfu.itis304.utils;

import ru.kpfu.itis304.dao.ApartPhotoDao;
import ru.kpfu.itis304.dao.ApartmentDao;
import ru.kpfu.itis304.dao.FavoritesDao;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dao.impl.*;
import ru.kpfu.itis304.dto.ApartPhotoDto;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;
import ru.kpfu.itis304.service.impl.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connection connection = DatabaseConnectionUtil.getConnection();
            UserDao userDao = new UserDaoImpl(connection);
            ApartmentDao<ApartmentRent> apartmentRentDao = new ApartmentRentDaoImpl(connection);
            ApartmentDao<ApartmentSale> apartmentSaleDao = new ApartmentSaleDaoImpl(connection);
            ApartPhotoDao apartPhotoDao = new ApartPhotoDaoImpl(connection);
            FavoritesDao favoritesDao = new FavoritesDaoImpl(connection);

            sce.getServletContext().setAttribute("userService", new UserServiceImpl(userDao));
            sce.getServletContext().setAttribute("profileService", new ProfileServiceImpl(userDao));
            sce.getServletContext().setAttribute("photoService", new PhotoServiceImpl(apartPhotoDao));
            sce.getServletContext().setAttribute("apartmentService", new ApartmentServiceImpl(apartmentRentDao, apartmentSaleDao));
            sce.getServletContext().setAttribute("favoritesService", new FavoriteServiceImpl(favoritesDao));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


