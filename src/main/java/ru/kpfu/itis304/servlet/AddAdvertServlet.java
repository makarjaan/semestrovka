package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@WebServlet("/addadvert")
public class AddAdvertServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddAdvertServlet.class.getName());

    public UserService userService;
    public ApartmentService apartmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница размещения нового объявления");
        String dealType = req.getParameter("dealType");
        HttpSession session = req.getSession();
        LOG.info(dealType);

        if ("rent".equals(dealType)) {
            LOG.info("Выбрано: Аренда");
        } else if ("sale".equals(dealType)) {
            LOG.info("Выбрано: Продажа");
        }

        session.setAttribute("dealType", dealType);
        req.setAttribute("dealType", dealType);
        req.setAttribute("saleType", session.getAttribute("saleType"));
        req.setAttribute("rentType", session.getAttribute("rentType"));
        req.setAttribute("propertyType", session.getAttribute("propertyType"));
        req.setAttribute("title", session.getAttribute("title"));
        req.setAttribute("description", session.getAttribute("description"));
        req.setAttribute("roomCount", session.getAttribute("roomCount"));
        req.setAttribute("area", session.getAttribute("area"));
        req.setAttribute("address", session.getAttribute("address"));


        getServletContext().getRequestDispatcher("/WEB-INF/view/addapart.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rentDuration = req.getParameter("rentDuration");
        Integer rentPrice = req.getParameter("rentPrice") != null ? Integer.valueOf(req.getParameter("rentPrice")) : null;
        Integer salePrice = req.getParameter("salePrice") != null ? Integer.valueOf(req.getParameter("salePrice")) : null;
        String propertyType = req.getParameter("propertyType");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String roomCount = req.getParameter("roomCount");
        Integer area = req.getParameter("area") != null ? Integer.valueOf(req.getParameter("area")) : null;
        String address = req.getParameter("address");

        LOG.info(rentDuration + " " + rentPrice + " " + salePrice + " " + propertyType
                + " " + title + " " + description + " " + roomCount + " " + area + " " + address);

        HttpSession session = req.getSession();
        String dealType = (String) session.getAttribute("dealType");
        session.setAttribute("rentPrice", rentPrice);
        session.setAttribute("salePrice", salePrice);
        session.setAttribute("propertyType", propertyType);
        session.setAttribute("title", title);
        session.setAttribute("description", description);
        session.setAttribute("roomCount", roomCount);
        session.setAttribute("area", area);
        session.setAttribute("address", address);

        LOG.info(dealType + " " + rentDuration + " " + rentPrice + " " + salePrice + " " + propertyType
        + " " + title + " " + description + " " + roomCount + " " + area + " " + address);

        if (dealType == null || address == null || address.trim().isEmpty() || "Не выбран".equals(address)) {
            if (dealType == null) {
                req.setAttribute("errMessage", "Необходимо выбрать тип сделки");
            }
            if (address == null || address.trim().isEmpty() || "Не выбран".equals(address)) {
                req.setAttribute("mapErrMessage", "Необходимо выбрать адрес недвижимости");
            }
            getServletContext().getRequestDispatcher("/WEB-INF/view/addapart.jsp").forward(req, resp);
            return;
        }



        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        Integer userId = userService.getId(userDto);

        if (dealType != null && salePrice != null && propertyType != null
                && title != null && description != null && roomCount != null && area != null && address != null) {

            ApartmentSaleDto apartmentSaleDto = new ApartmentSaleDto(userId, title, description,
                    propertyType, roomCount, area, ApartmentStatus.SENT_FOR_VERIFICATION, address, salePrice);

            session.setAttribute("apartmentSaleDto", apartmentSaleDto);

            apartmentService.saveApaptSaleInBd(apartmentSaleDto);

        } else if (dealType != null && rentPrice != null && rentDuration != null && propertyType != null
                && title != null && description != null && roomCount != null && area != null && address != null) {
            ApartmentRentDto apartmentRentDto = new ApartmentRentDto(userId, title, description, propertyType,
                    roomCount, area, ApartmentStatus.SENT_FOR_VERIFICATION, address, rentDuration, rentPrice);

            apartmentService.saveApaptRentInBd(apartmentRentDto);

            session.setAttribute("apartmentRentDto", apartmentRentDto);
        }

        session.removeAttribute("dealType");
        session.removeAttribute("rentDuration");
        session.removeAttribute("rentPrice");
        session.removeAttribute("salePrice");
        session.removeAttribute("propertyType");
        session.removeAttribute("title");
        session.removeAttribute("description");
        session.removeAttribute("roomCount");
        session.removeAttribute("area");
        session.removeAttribute("address");
        resp.sendRedirect(getServletContext().getContextPath() + "/settings");
    }
}
