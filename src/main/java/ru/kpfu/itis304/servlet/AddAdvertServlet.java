package ru.kpfu.itis304.servlet;

import ru.kpfu.itis304.dto.ApartPhotoDto;
import ru.kpfu.itis304.dto.ApartmentRentDto;
import ru.kpfu.itis304.dto.ApartmentSaleDto;
import ru.kpfu.itis304.dto.UserDto;
import ru.kpfu.itis304.enums.ApartmentStatus;
import ru.kpfu.itis304.service.ApartmentService;
import ru.kpfu.itis304.service.PhotoService;
import ru.kpfu.itis304.service.UserService;
import ru.kpfu.itis304.utils.CloudinaryUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet("/addadvert")
public class AddAdvertServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AddAdvertServlet.class.getName());
    private static final int MAX_IMAGE_COUNT = 5; // Максимум 5 изображений

    public UserService userService;
    public ApartmentService apartmentService;
    public PhotoService photoService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        apartmentService = (ApartmentService) getServletContext().getAttribute("apartmentService");
        photoService = (PhotoService) getServletContext().getAttribute("photoService");
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
        req.setAttribute("uploadedImages", session.getAttribute("uploadedImages"));


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
        Integer apartmentId = null;

        if (dealType != null && salePrice != null && propertyType != null
                && title != null && description != null && roomCount != null && area != null && address != null) {

            ApartmentSaleDto apartmentSaleDto = new ApartmentSaleDto(userId, title, description,
                    apartmentService.getPropertyTypeDisplayValue(propertyType),
                    apartmentService.getRoomCountDisplayValue(roomCount), area,
                    ApartmentStatus.SENT_FOR_VERIFICATION, address, salePrice);

            apartmentService.saveApaptInBd(apartmentSaleDto);
            apartmentId = apartmentService.getApartId(apartmentSaleDto);
            session.setAttribute("apartmentSaleDto", apartmentSaleDto);

        } else if (dealType != null && rentPrice != null && rentDuration != null && propertyType != null
                && title != null && description != null && roomCount != null && area != null && address != null) {
            ApartmentRentDto apartmentRentDto = new ApartmentRentDto(userId, title, description,
                    apartmentService.getPropertyTypeDisplayValue(propertyType),
                    apartmentService.getRoomCountDisplayValue(roomCount), area,
                    ApartmentStatus.SENT_FOR_VERIFICATION, address,
                    apartmentService.getRentTypeValue(rentDuration), rentPrice);

            apartmentService.saveApaptInBd(apartmentRentDto);
            apartmentId = apartmentService.getApartId(apartmentRentDto);
            session.setAttribute("apartmentRentDto", apartmentRentDto);
        }

        List<Part> fileParts = req.getParts().stream()
                .filter(part -> "images".equals(part.getName()))
                .collect(Collectors.toList());

        if (!fileParts.isEmpty()) {

            List<String> filenames = fileParts.stream()
                    .map(part -> Paths.get(part.getSubmittedFileName()).getFileName().toString())
                    .collect(Collectors.toList());

            List<File> tempFiles = new ArrayList<>();

            int imageCount = Math.min(fileParts.size(), MAX_IMAGE_COUNT);

            for (int i = 0; i < imageCount; i++) {
                tempFiles.add(photoService.makeFile(fileParts.get(i), filenames.get(i)));
            }


            for (int i = 0; i < imageCount; i++) {
                String fileUrl = photoService.uploadPhoto(tempFiles.get(i), filenames.get(i));
                LOG.info(fileUrl);
                if (dealType.equals("sale")) {
                    ApartPhotoDto apartPhotoDto = new ApartPhotoDto(apartmentId, fileUrl, "sale");
                    photoService.addApartPhoto(apartPhotoDto);
                } else if (dealType.equals("rent")) {
                    ApartPhotoDto apartPhotoDto = new ApartPhotoDto(apartmentId, fileUrl, "rent");
                    photoService.addApartPhoto(apartPhotoDto);
                }
            }
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
