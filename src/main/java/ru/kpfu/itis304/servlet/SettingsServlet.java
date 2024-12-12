package ru.kpfu.itis304.servlet;


import ru.kpfu.itis304.dto.UserDto;
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
import java.util.logging.Logger;


@WebServlet("/settings")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class SettingsServlet extends HttpServlet {

    public ProfileService profileService;
    public UserService userService;

    public static final Logger LOG = Logger.getLogger(SettingsServlet.class.getName());


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        profileService = (ProfileService) getServletContext().getAttribute("profileService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница настройки профиля");

        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        req.setAttribute("user", userService.getByEmail(userDto.getEmail()));

        String activeTab = req.getParameter("activeTab");
        if (activeTab == null) {
            activeTab = "advertisement";
        }
        req.setAttribute("activeTab", activeTab);

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

                File tempFile = File.createTempFile("profile_", filename);

                try (InputStream content = part.getInputStream();
                     OutputStream out = Files.newOutputStream(tempFile.toPath())) {
                    byte[] buffer = new byte[1024];
                    int count;
                    while ((count = content.read(buffer)) != -1) {
                        out.write(buffer, 0, count);
                    }
                }

                try {
                    String fileUrl = profileService.uploadProfilePhoto(tempFile, filename);
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
        resp.sendRedirect(getServletContext().getContextPath() + "/settings");
    }
}

