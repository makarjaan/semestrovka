package ru.kpfu.itis304.servlet;


import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.entity.User;
import ru.kpfu.itis304.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

    public UserService userService;

    private static final Logger LOG = Logger.getLogger(AuthorizationServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница входа на сайт");
        getServletContext().getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        LOG.info("Попытка входа пользователя: " + email);

        if (email != null && password != null) {
            try{
                if (userService.addDatabase(email, password) && "admin@mail.ru".equals(email)) {
                    LOG.info("Администратор вошёл в систему");
                    HttpSession session = req.getSession(true);
                    req.getSession().setAttribute("adminLogin", email);
                    req.getSession().setAttribute("adminPassword", password);
                    req.getSession().setAttribute("userRole", "admin");
                    resp.sendRedirect(getServletContext().getContextPath() + "/admin");
                    return;
                }

                if (userService.addDatabase(email, password)) {
                    HttpSession oldSession = req.getSession(false);
                    if (oldSession != null) {
                        oldSession.invalidate();
                    }
                    UserDto userDto = userService.getByEmailAndPassword(email, password);
                    LOG.info("Начинаю сессию для пользователя с email: " + email);
                    userService.authenticateUser(userDto, req, resp);
                    LOG.info("Пользователь с email " + email + " авторизован в системе.");

                    resp.sendRedirect(getServletContext().getContextPath() + "/main");
                } else {
                    LOG.info("Неудачная попытка авторизации пользователя: " + email);
                    req.setAttribute("errorMessage", "Неверно введён пароль или же такого аккаунта не существует");
                    getServletContext().getRequestDispatcher("/WEB-INF/view/auth.jsp").forward(req, resp);
                }
            } catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
