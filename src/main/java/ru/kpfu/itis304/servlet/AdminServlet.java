package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    public ApartmentService apartmentService;

    public static final Logger LOG = Logger.getLogger(AdminServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Запрошена страница администрирования");


        List<ApartmentRentDto> apartmentsRent = apartmentService.getAllApartmentRent();
        List<ApartmentSaleDto> apartmentsSale = apartmentService.getAllApartmentSale();

        req.setAttribute("apartmentsRentAdmin", apartmentsRent);
        req.setAttribute("apartmentsSaleAdmin", apartmentsSale);
        req.setAttribute("userRole", "admin");

        getServletContext().getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String dealType = req.getParameter("dealType");
        String apartmentId = req.getParameter("apartmentId");

        LOG.info(action);


        if (action != null && apartmentId != null && !apartmentId.isEmpty()) {
            int id = Integer.parseInt(apartmentId);

            if ("approve".equals(action)) {
                if ("sale".equals(dealType)) {
                    apartmentService.updateStatus(id, "sale", ApartmentStatus.PUBLISHED);
                } else if ("rent".equals(dealType)) {
                    apartmentService.updateStatus(id, "rent", ApartmentStatus.PUBLISHED);
                }
            } else if ("reject".equals(action)) {
                if ("sale".equals(dealType)) {
                    apartmentService.updateStatus(id, "sale", ApartmentStatus.REFUSED);
                } else if ("rent".equals(dealType)) {
                    apartmentService.updateStatus(id, "rent", ApartmentStatus.REFUSED);
                }
            }
        }


        resp.sendRedirect(getServletContext().getContextPath() + "/admin");
    }
}
