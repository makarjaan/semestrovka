package ru.kpfu.itis304.servlet;


import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.PhotoService;
import ru.kpfu.itis304.service.ProfileService;
import ru.kpfu.itis304.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@WebServlet("/settings")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SettingsServlet extends HttpServlet {

    public ProfileService profileService;
    public UserService userService;
    public PhotoService photoService;
    public ApartmentService apartmentService;

    public static final Logger LOG = Logger.getLogger(SettingsServlet.class.getName());


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        profileService = (ProfileService) getServletContext().getAttribute("profileService");
        userService = (UserService) getServletContext().getAttribute("userService");
        photoService = (PhotoService) getServletContext().getAttribute("photoService");
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница настройки профиля");

        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        req.setAttribute("user", userService.getByEmail(userDto.getEmail()));

        ApartmentSaleDto apartmentSaleDto = (ApartmentSaleDto) req.getSession().getAttribute("apartmentSaleDto");
        ApartmentRentDto apartmentRentDto = (ApartmentRentDto) req.getSession().getAttribute("apartmentRentDto");

        List<ApartmentRentDto> apartmentsRent = apartmentService.getApartmentRentListByUserId(userService.getId(userDto));
        List<ApartmentSaleDto> apartmentsSale = apartmentService.getApartmentSaleListByUserId(userService.getId(userDto));


        apartmentsRent.sort((a1, a2) -> {
            Timestamp t1 = apartmentService.getCreatedTimeRent(apartmentService.getApartId(a1));
            Timestamp t2 = apartmentService.getCreatedTimeRent(apartmentService.getApartId(a2));

            if (t1 == null && t2 == null) return 0;
            if (t1 == null) return 1;
            if (t2 == null) return -1;
            return t2.compareTo(t1);
        });

        apartmentsSale.sort((a1, a2) -> {
            Timestamp t1 = apartmentService.getCreatedTimeSale(apartmentService.getApartId(a1));
            Timestamp t2 = apartmentService.getCreatedTimeSale(apartmentService.getApartId(a2));

            if (t1 == null && t2 == null) return 0;
            if (t1 == null) return 1;
            if (t2 == null) return -1;
            return t2.compareTo(t1);
        });

        req.setAttribute("apartmentsSale", apartmentsSale);
        req.setAttribute("apartmentsRent", apartmentsRent);

        getServletContext().getRequestDispatcher("/WEB-INF/view/settings.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        String newUserName = req.getParameter("changeUserName");

        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");


        if ("uploadPhoto".equals(action)) {
            Part part = req.getPart("profilePhoto");
            if (part != null && part.getSize() > 0) {

                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                File tempFile = photoService.makeFile(part, filename);

                try {
                    String fileUrl = photoService.uploadPhoto(tempFile, filename);
                    profileService.updatePhoto(user, fileUrl);
                    LOG.info("Пользователь: " + user.getEmail() + "изменил фото профиля");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else if ("deletePhoto".equals(action)){
            profileService.updatePhoto(user, null);
            LOG.info("Пользователь: " + user.getEmail() + "удалил фото профиля");
        }

        if (newUserName != null) {
            profileService.updateUserName(user, newUserName);
        }

        if (currentPassword != null && newPassword != null) {
            if (!profileService.checkPassword(user, currentPassword)) {
                req.setAttribute("errorMessage", "Неверно введён текущий пароль");
                getServletContext().getRequestDispatcher("/WEB-INF/view/settings.jsp").forward(req, resp);
                return;
            }
            profileService.changePassword(user, newPassword);
            LOG.info("Пользователь: " + user.getEmail() + "изменил пароль");
        }

        if ("deleteAccount".equals(action)) {
            userService.deleteUser(user, req);
            resp.sendRedirect(getServletContext().getContextPath() + "/main");
            return;
        }


        String dealType = req.getParameter("dealType");
        String apartmentIdForDelete = req.getParameter("addvertApartIdForDelete");
        if (apartmentIdForDelete != null && !apartmentIdForDelete.isEmpty()) {
            int apartmentId = Integer.parseInt(apartmentIdForDelete);
            if ("sale".equals(dealType)) {
                apartmentService.deleteApartmentSaleById(apartmentId);
            } else if ("rent".equals(dealType)) {
                apartmentService.deleteApartmentRentById(apartmentId);
            }
        }

        String addvertApartIdForUnpublish = req.getParameter("addvertApartIdForUnpublish");
        if (addvertApartIdForUnpublish != null && !addvertApartIdForUnpublish.isEmpty()) {
            int addvertId = Integer.parseInt(addvertApartIdForUnpublish);
            if ("sale".equals(dealType)) {
                apartmentService.updateStatus(addvertId, "sale", ApartmentStatus.COMPLETED);
            } else if ("rent".equals(dealType)) {
                apartmentService.updateStatus(addvertId, "rent", ApartmentStatus.COMPLETED);
            }
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/settings");
    }
}

