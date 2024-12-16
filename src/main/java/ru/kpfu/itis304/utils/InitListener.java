package ru.kpfu.itis304.utils;

import ru.kpfu.itis304.dao.ApartmentRentDao;
import ru.kpfu.itis304.dao.ApartmentSaleDao;
import ru.kpfu.itis304.dao.UserDao;
import ru.kpfu.itis304.dao.impl.ApartmentRentDaoImpl;
import ru.kpfu.itis304.dao.impl.ApartmentSaleDaoImpl;
import ru.kpfu.itis304.dao.impl.UserDaoImpl;
import ru.kpfu.itis304.service.impl.ApartmentServiceImpl;
import ru.kpfu.itis304.service.impl.PhotoServiceImpl;
import ru.kpfu.itis304.service.impl.ProfileServiceImpl;
import ru.kpfu.itis304.service.impl.UserServiceImpl;

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
            ApartmentRentDao apartmentRentDao = new ApartmentRentDaoImpl(connection);
            ApartmentSaleDao apartmentSaleDao = new ApartmentSaleDaoImpl(connection);

            sce.getServletContext().setAttribute("userService", new UserServiceImpl(userDao));
            sce.getServletContext().setAttribute("profileService", new ProfileServiceImpl(userDao));
            sce.getServletContext().setAttribute("photoService", new PhotoServiceImpl());
            sce.getServletContext().setAttribute("apartmentService", new ApartmentServiceImpl(apartmentRentDao, apartmentSaleDao));

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


