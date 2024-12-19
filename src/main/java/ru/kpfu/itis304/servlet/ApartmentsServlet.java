package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.ApartmentSale;
import ru.kpfu.itis304.service.ApartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/apartments")
public class ApartmentsServlet extends HttpServlet {

    public ApartmentService apartmentService;
    private static final Logger LOG = Logger.getLogger(ApartmentsServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получение всех данных
        //List<ApartmentSaleDto> apartmentsSale = apartmentService.getAllApartmentSale();
        // List<ApartmentRentDto> apartmentsShortRent = apartmentService.getAllRentShortType();
        // List<ApartmentRentDto> apartmentsLongRent = apartmentService.getAllRentLongType();

        String priceMin = req.getParameter("priceMin");
        String priceMax = req.getParameter("priceMax");
        String address = req.getParameter("address");
        String roomsCount = req.getParameter("rooms");
        String propertyType = req.getParameter("propertyType");

        LOG.info("priceMin: " + priceMin);
        LOG.info("priceMax: " + priceMax);
        LOG.info("address: " + address);
        LOG.info("roomsCount: " + roomsCount);
        LOG.info("propertyType: " + propertyType);

        Integer minPrice = (priceMin != null && !priceMin.isEmpty()) ? Integer.parseInt(priceMin) : null;
        Integer maxPrice = (priceMax != null && !priceMax.isEmpty()) ? Integer.parseInt(priceMax) : null;


        List<ApartmentSaleDto> apartmentsSale = apartmentService.filterApartmentsSale(minPrice, maxPrice, address, roomsCount, propertyType);
        List<ApartmentRentDto> apartmentsShortRent = apartmentService.filterApartmentsShortRent(minPrice, maxPrice, address, roomsCount, propertyType);
        List<ApartmentRentDto> apartmentsLongRent = apartmentService.filterApartmentsLongRent(minPrice, maxPrice, address, roomsCount, propertyType);

        req.setAttribute("priceMin", priceMin);
        req.setAttribute("priceMax", priceMax);
        req.setAttribute("address", address);
        req.setAttribute("rooms", roomsCount);
        req.setAttribute("propertyType", propertyType);

        req.setAttribute("apartmentsSale", apartmentsSale);
        req.setAttribute("apartmentsShortRent", apartmentsShortRent);
        req.setAttribute("apartmentsLongRent", apartmentsLongRent);

        // Перенаправление на страницу JSP
        getServletContext().getRequestDispatcher("/WEB-INF/view/apartments.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(getServletContext().getContextPath() + "/apartments");

    }
}
