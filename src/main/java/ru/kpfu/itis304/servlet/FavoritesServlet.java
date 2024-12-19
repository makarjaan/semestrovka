package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.dto.FavoritesDto;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.entity.ApartmentRent;
import ru.kpfu.itis304.entity.Favorites;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.FavoriteService;
import ru.kpfu.itis304.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/favorites")
public class FavoritesServlet extends HttpServlet {

    public FavoriteService favoriteService;
    public UserService userService;
    public ApartmentService apartmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        favoriteService = (FavoriteService) getServletContext().getAttribute("favoritesService");
        userService = (UserService) getServletContext().getAttribute("userService");
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        List<FavoritesDto> list = favoriteService.getFavoritesById(userService.getId(user));
        List<ApartmentRentDto> listRent = new ArrayList<>();
        List<ApartmentSaleDto> listSale = new ArrayList<>();
        for (FavoritesDto favorite : list) {
            if (favorite.getApartment_rent_id() != 0) {
                ApartmentRentDto apartmentRent = apartmentService.getApartmentRentInfo(
                        favorite.getApartment_rent_id());
                listRent.add(apartmentRent);
            } else if (favorite.getApartment_sale_id() != 0) {
                ApartmentSaleDto apartmentSale = apartmentService.getApartmentSaleInfo(
                        favorite.getApartment_sale_id());
                listSale.add(apartmentSale);
            }
        }
        req.setAttribute("list", list);
        req.setAttribute("listRent", listRent);
        req.setAttribute("listSale", listSale);

        getServletContext().getRequestDispatcher("/WEB-INF/view/favorites.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apartmentId = req.getParameter("apartmentId");
        String type = req.getParameter("type");
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        Integer userId = userService.getId(user);

        if (userId != null && apartmentId != null && type != null) {
            boolean success = favoriteService.toggleFavorite(userId, Integer.parseInt(apartmentId), type);
            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/view/favorites.jsp").forward(req, resp);
    }
}
