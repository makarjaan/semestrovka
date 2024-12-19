<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <t:nav title="Квартиры">
        <div class="container">
            <form method="get" action="<c:url value='/apartments'/>" class="mb-4">
                <div class="row g-3 align-items-end">
                    <div class="col-md-2">
                        <label for="priceMin" class="form-label">Цена (от):</label>
                        <input type="number" id="priceMin" name="priceMin" class="form-control" value="${priceMin}">
                    </div>

                    <div class="col-md-2">
                        <label for="priceMax" class="form-label">Цена (до):</label>
                        <input type="number" id="priceMax" name="priceMax" class="form-control" value="${priceMax}">
                    </div>

                    <div class="col-md-2">
                        <label for="address" class="form-label">Адрес:</label>
                        <input type="text" id="address" name="address" class="form-control" value="${address}">
                    </div>

                    <div class="col-md-2">
                        <label for="rooms" class="form-label">Комнаты:</label>
                        <select id="rooms" name="rooms" class="form-select">
                            <option value="" ${empty rooms ? 'selected' : ''}>Любое</option>
                            <option value="Студия" ${rooms == 'Студия' ? 'selected' : ''}>Студия</option>
                            <option value="1-комнатная" ${rooms == '1-комнатная' ? 'selected' : ''}>1-комнатная</option>
                            <option value="2-комнатная" ${rooms == '2-комнатная' ? 'selected' : ''}>2-комнатная</option>
                            <option value="3-комнатная" ${rooms == '3-комнатная' ? 'selected' : ''}>3-комнатная</option>
                            <option value="4-комнатная" ${rooms == '4-комнатная' ? 'selected' : ''}>4-комнатная</option>
                            <option value="5+ комнат" ${rooms == '5+ комнат' ? 'selected' : ''}>5+ комнат</option>
                        </select>
                    </div>

                    <div class="col-md-2">
                        <label for="propertyType" class="form-label">Тип:</label>
                        <select id="propertyType" name="propertyType" class="form-select">
                            <option value="" ${empty propertyType ? 'selected' : ''}>Любой</option>
                            <option value="Квартира" ${propertyType == 'Квартира' ? 'selected' : ''}>Квартира</option>
                            <option value="Дом" ${propertyType == 'Дом' ? 'selected' : ''}>Дом</option>
                            <option value="Комната" ${propertyType == 'Комната' ? 'selected' : ''}>Комната</option>
                        </select>
                    </div>

                    <div class="col-md-2 d-flex gap-2">
                        <button type="submit" class="custom-btn w-100">Поиск</button>
                        <button type="reset" class="custom-btn w-100" onclick="window.location.href='<c:url value="/apartments"/>'">Сброс</button>
                    </div>
                </div>
            </form>



            <nav>
                <div class="nav nav-tabs justify-content-center border-bottom-0" id="nav-tab" role="tablist">
                    <button class="nav-link active" id="nav-sale-tab" data-bs-toggle="tab" data-bs-target="#nav-sale"
                            type="button" role="tab" aria-controls="nav-sale" aria-selected="true">Продажа</button>
                    <button class="nav-link" id="nav-rent-tab" data-bs-toggle="tab" data-bs-target="#nav-rent"
                            type="button" role="tab" aria-controls="nav-rent" aria-selected="false">Аренда</button>
                </div>
            </nav>

            <div class="tab-content mt-3" id="nav-tabContent1">
                <div class="tab-pane fade show active" id="nav-sale" role="tabpanel" aria-labelledby="nav-sale-tab">
                    <div class="container">
                        <h2 class="mb-3 text-center">Объявления о продаже</h2>
                        <div class="row justify-content-center row-cols-1 row-cols-md-3 g-4">
                            <c:forEach var="apartment" items="${apartmentsSale}">
                                <c:if test="${apartment.status.equals('Опубликовано')}">
                                    <div class="col">
                                        <a href="<c:url value='/details?id=${apartmentService.getApartId(apartment)}&type=sale'/>"
                                           class="card mb-3 shadow-sm border-0 text-decoration-none">
                                            <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "sale")}"
                                                 class="card-img-top" style="height: 200px; object-fit: cover;">
                                            <div class="card-body">
                                                <h5 class="title mb-2 fs-3">${apartment.title}</h5>
                                                <p class="fw-bold text-dark mb-2">${apartment.priceSale} ₽</p>
                                                <p class="text-dark mb-2">${apartment.address}</p>
                                                <p class="text-muted small mb-0">Опубликовано: ${apartmentService.getDate(apartmentService.getCreatedTimeSale(apartmentService.getApartId(apartment)))}</p>
                                            </div>
                                        </a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>


                <div class="tab-pane fade" id="nav-rent" role="tabpanel" aria-labelledby="nav-rent-tab">
                    <div class="container">
                        <h2 class="mb-3 text-center">Объявления об аренде</h2>
                        <nav>
                            <div class="nav nav-tabs justify-content-center border-bottom-0" id="rent-tab" role="tablist">
                                <button class="nav-link active" id="rent-short-tab" data-bs-toggle="tab"
                                        data-bs-target="#rent-short" type="button" role="tab" aria-controls="rent-short"
                                        aria-selected="true">Посуточно</button>
                                <button class="nav-link" id="rent-long-tab" data-bs-toggle="tab"
                                        data-bs-target="#rent-long" type="button" role="tab" aria-controls="rent-long"
                                        aria-selected="false">Долгосрочно</button>
                            </div>
                        </nav>

                        <div class="tab-content mt-3" id="rent-tabContent">
                            <div class="tab-pane fade show active" id="rent-short" role="tabpanel"
                                 aria-labelledby="rent-short-tab">
                                <div class="row justify-content-center row-cols-1 row-cols-md-3 g-4">
                                    <c:forEach var="apartment" items="${apartmentsShortRent}">
                                        <c:if test="${apartment.status.equals('Опубликовано')}">
                                            <div class="col">
                                                <a href="<c:url value='/details?id=${apartmentService.getApartId(apartment)}&type=rent'/>"
                                                   class="card mb-3 shadow-sm border-0 text-decoration-none">
                                                    <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "rent")}"
                                                         class="card-img-top" style="height: 200px; object-fit: cover;">
                                                    <div class="card-body">
                                                        <h5 class="title mb-2 fs-3">${apartment.title}</h5>
                                                        <p class="fw-bold text-dark mb-2">${apartment.priceRent} ₽</p>
                                                        <p class="text-dark mb-2">${apartment.address}</p>
                                                        <p class="text-muted small mb-0">Опубликовано: ${apartmentService.getDate(apartmentService.getCreatedTimeRent(apartmentService.getApartId(apartment)))}</p>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>


                            <div class="tab-pane fade" id="rent-long" role="tabpanel" aria-labelledby="rent-long-tab">
                                <div class="row justify-content-center row-cols-1 row-cols-md-3 g-4">
                                    <c:forEach var="apartment" items="${apartmentsLongRent}">
                                        <c:if test="${apartment.status.equals('Опубликовано')}">
                                            <div class="col">
                                                <a href="<c:url value='/details?id=${apartmentService.getApartId(apartment)}&type=rent'/>"
                                                   class="card mb-3 shadow-sm border-0 text-decoration-none">
                                                    <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "rent")}"
                                                         class="card-img-top" style="height: 200px; object-fit: cover;">
                                                    <div class="card-body">
                                                        <h5 class="title mb-2 fs-3">${apartment.title}</h5>
                                                        <p class="fw-bold text-dark mb-2">${apartment.priceRent} ₽</p>
                                                        <p class="text-dark mb-2">${apartment.address}</p>
                                                        <p class="text-muted small mb-0">Опубликовано: ${apartmentService.getDate(apartmentService.getCreatedTimeRent(apartmentService.getApartId(apartment)))}</p>
                                                    </div>
                                                </a>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </t:nav>


