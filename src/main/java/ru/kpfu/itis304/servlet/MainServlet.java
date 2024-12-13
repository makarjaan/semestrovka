package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.entity.User;
import ru.kpfu.itis304.service.UserService;
import ru.kpfu.itis304.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    public UserService userService;

    private static final Logger LOG  = Logger.getLogger(MainServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена главная страница");
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        if (userDto != null) {
            req.setAttribute("user", userService.getByEmail(userDto.getEmail()));
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
    }

}