<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Избранные">
    <div class="container mt-4">
        <h2 class="text-center">Мои избранные квартиры</h2>

        <c:if test="${empty list}">
            <hr class="my-3">
            <h3>У вас пока нет избранных квартир</h3>

        </c:if>

        <c:if test="${not empty listRent || not empty listSale}">
            <div class="container mt-4">
                <nav>
                    <div class="nav nav-tabs justify-content-center border-bottom-0" id="nav-tab" role="tablist">
                        <button class="nav-link active" id="nav-sale-tab" data-bs-toggle="tab" data-bs-target="#nav-sale" type="button"
                                role="tab" aria-controls="nav-sale" aria-selected="true">Продажа</button>
                        <button class="nav-link" id="nav-rent-tab" data-bs-toggle="tab" data-bs-target="#nav-rent" type="button"
                                role="tab" aria-controls="nav-rent" aria-selected="false">Аренда</button>
                    </div>
                </nav>
                <div class="tab-content mt-3" id="nav-tabContent1">
                    <div class="tab-pane fade show active" id="nav-sale" role="tabpanel" aria-labelledby="nav-sale-tab">
                        <div class="container">
                            <c:if test="${not empty listSale}">
                                <c:forEach var="apartment" items="${listSale}">
                                    <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">
                                        <div class="flex-shrink-0 me-3">
                                            <a href="<c:url value='/details?type=sale&id=${apartmentService.getApartId(apartment)}'/>">
                                                <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "sale")}" class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                                            </a>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h5 class="title mb-1">${apartment.title}</h5>
                                            <p class="fw-bold mb-1">${apartment.priceSale} ₽</p>
                                            <p class="text-muted mb-0">${apartment.address}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <c:if test="${empty listSale}">
                                <p>Нет избранных квартир на продажу</p>
                            </c:if>
                        </div>
                    </div>


                    <div class="tab-pane fade" id="nav-rent" role="tabpanel" aria-labelledby="nav-rent-tab">
                        <div class="container">
                            <c:if test="${not empty listRent}">
                                <c:forEach var="apartment" items="${listRent}">
                                    <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">
                                        <div class="flex-shrink-0 me-3">
                                            <a href="<c:url value='/details?type=rent&id=${apartmentService.getApartId(apartment)}'/>">
                                                <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "sale")}" class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                                            </a>
                                        </div>
                                        <div class="flex-grow-1">
                                            <h5 class="title mb-1">${apartment.title}</h5>
                                            <p class="fw-bold mb-1">${apartment.priceRent} ₽/месяц</p>
                                            <p class="text-muted mb-0">${apartment.address}</p>
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>

                            <c:if test="${empty listRent}">
                                <p>Нет избранных квартир для аренды</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>


</t:nav>

