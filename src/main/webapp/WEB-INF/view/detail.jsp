<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Детальная информация">
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 mb-4">
                <div id="carouselExampleRide" class="carousel slide mb-4 shadow" data-bs-ride="carousel">
                    <div class="carousel-inner">
                        <c:forEach var="photo" items="${apartmnetPhoto}" varStatus="status">
                            <div class="carousel-item ${status.first ? 'active' : ''}">
                                <img src="${photo}" class="d-block w-100" alt="Фото квартиры">
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleRide" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>


            <div class="col-md-6">
                <h2 class="apartment-title fw-bold" style="color: #1a1d20">${apartment.title}</h2>

                <p class="price fs-4">
                    <c:if test="${type == 'sale'}">
                        Цена продажи: ${apartment.priceSale} ₽
                    </c:if>

                    <c:if test="${type == 'rent'}">

                        <c:if test="${apartment.rentType == 'Посуточно'}">
                            Цена аренды: ${apartment.priceRent} ₽/сутки
                        </c:if>

                        <c:if test="${apartment.rentType == 'Долгосрочно'}">
                            Цена аренды: ${apartment.priceRent} ₽/мес
                        </c:if>

                    </c:if>
                </p>

                <p><strong>Адрес:</strong> ${apartment.address}</p>
                <p><strong>Тип недвижимости:</strong> ${apartment.getType()}</p>
                <p><strong>Количество комнат:</strong> ${apartment.getRoomsCount()}</p>
                <p><strong>Площадь:</strong> ${apartment.area} м²</p>
                <c:if test="${type == 'rent'}">
                    <p><strong>Тип аренды:</strong> ${apartment.rentType}</p>
                </c:if>

                <div class="d-flex gap-3 mt-4">
                    <c:if test="${not empty userRole}">
                        <form method="post" action="<c:url value='/admin'/>">
                            <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                            <input type="hidden" name="dealType" value="${type}">
                            <button class="btn btn-success w-100" type="submit" name="action" value="approve">Одобрить</button>
                        </form>

                        <form method="post" action="<c:url value='/admin'/>">
                            <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                            <input type="hidden" name="dealType" value="${type}">
                            <button class="btn btn-danger w-100" type="submit" name="action" value="reject">Отклонить</button>
                        </form>
                    </c:if>

                    <c:if test="${empty userRole}">
                        <c:if test="${not empty user}">
                            <button id="phoneButton" class="btn-primary" type="button" onclick="showPhoneNumber()">Показать телефон</button>

                            <form method="post" action="<c:url value="/details"/>">
                                <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                                <input type="hidden" name="dealType" value="${type}">
                                <c:if test="${isFavorite == 'false'}">
                                    <button class="custom-btn" type="submit" name="action" value="addFavorite">Добавить в избранные</button>
                                </c:if>

                                <c:if test="${isFavorite == 'true'}">
                                     <button class="custom-btn" type="submit" name="action" value="deleteFavorite">Удалить из избранных</button>
                                </c:if>
                            </form>

                            <div id="phoneNumber" style="display: none; margin-top: 10px;">
                                <p><strong>Телефон: </strong></p><span style="font-weight: bold; font-size: 1.2em; color: #28a745;">${phone}</span>
                            </div>
                        </c:if>

                        <c:if test="${empty user}">
                            <a href="<c:url value='/authorization'/>">Зарегистрируйтесь, чтобы связаться с хозяином</a>
                        </c:if>
                    </c:if>
                </div>
            </div>
        </div>


        <div class="row mt-4 justify-content-center">
            <div class="col-8 text-center">
                <p class="description" style="word-wrap: break-word;
                                      overflow-wrap: break-word;
                                      white-space: normal;
                                      word-break: break-all;">
                        ${apartment.description}
                </p>
            </div>
        </div>

    </div>



</t:nav>