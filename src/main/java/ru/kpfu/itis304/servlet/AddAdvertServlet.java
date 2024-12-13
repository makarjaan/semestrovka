package ru.kpfu.itis304.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/addadvert")
public class AddAdvertServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddAdvertServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница размещения нового объявления");
        getServletContext().getRequestDispatcher("/WEB-INF/view/addapart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/view/addapart.jsp").forward(req, resp);
    }
}
