package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.FavoriteService;
import ru.kpfu.itis304.service.PhotoService;
import ru.kpfu.itis304.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/details")
public class DetailServlet extends HttpServlet {

    public ApartmentService apartmentService;
    public PhotoService photoService;
    public FavoriteService favoriteService;
    public UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
        photoService = (PhotoService) getServletContext().getAttribute("photoService");
        favoriteService = (FavoriteService) getServletContext().getAttribute("favoritesService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apartmentId = req.getParameter("id");
        String dealType = req.getParameter("type");
        Object apartment = new Object();
        boolean isFavorite = false;

        if (dealType.equals("sale")) {
            apartment = apartmentService.getApartmentSaleInfo(Integer.valueOf(apartmentId));
        } else if (dealType.equals("rent")) {
            apartment = apartmentService.getApartmentRentInfo(Integer.valueOf(apartmentId));
        }

        if (apartment instanceof ApartmentSaleDto) {
            ApartmentSaleDto saleDto = (ApartmentSaleDto) apartment;
            int apaptId = apartmentService.getApartId(saleDto);
            List<String> photoUrl =  photoService.getPhotosByApartmentId(apaptId, "sale");
            req.setAttribute("apartmnetPhoto", photoUrl);
            req.setAttribute("type", "sale");
            req.setAttribute("phone", apartmentService.getUserPhone(apartment, apaptId, "sale"));
            isFavorite = favoriteService.isApartmentInFavorites(userService.getId((UserDto) req.getSession().getAttribute("user")), apaptId, "sale");
        } else if (apartment instanceof ApartmentRentDto) {
            ApartmentRentDto rentDto = (ApartmentRentDto) apartment;
            int apartId = apartmentService.getApartId(rentDto);
            List<String> photoUrl =  photoService.getPhotosByApartmentId(apartId, "rent");
            req.setAttribute("apartmnetPhoto", photoUrl);
            req.setAttribute("type", "rent");
            req.setAttribute("phone", apartmentService.getUserPhone(apartment, apartId, "rent"));
            isFavorite = favoriteService.isApartmentInFavorites(userService.getId((UserDto) req.getSession().getAttribute("user")), apartId, "rent");
        }

        req.setAttribute("apartment", apartment);
        req.setAttribute("isFavorite", isFavorite);

        req.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String apartmentId = req.getParameter("apartmentId");  // Поменяли id на apartmentId
        String dealType = req.getParameter("dealType");  // Поменяли type на dealType
        UserDto userDto = (UserDto) req.getSession().getAttribute("user");

        boolean isFavorite = false;
        if ("addFavorite".equals(action)) {
            isFavorite = favoriteService.toggleFavorite(userService.getId(userDto), Integer.parseInt(apartmentId), dealType);
        } else if ("deleteFavorite".equals(action)) {
            isFavorite = favoriteService.toggleFavorite(userService.getId(userDto), Integer.parseInt(apartmentId), dealType);
        }

        req.setAttribute("isFavorite", isFavorite);

        resp.sendRedirect(getServletContext().getContextPath() + "/details?id=" + apartmentId + "&type=" + dealType);
    }
}

