<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Страница администратора">
  <h1 class="my-4 text-center">Администрирование объявлений</h1>

  <div class="container">
    <nav>
      <div class="nav nav-tabs justify-content-center border-bottom-0" id="nav-tab" role="tablist">
        <button class="nav-link active" id="nav-sale-tab" data-bs-toggle="tab" data-bs-target="#nav-sale" type="button"
                role="tab" aria-controls="nav-sale" aria-selected="true">Продажа</button>
        <button class="nav-link" id="nav-rent-tab" data-bs-toggle="tab" data-bs-target="#nav-rent" type="button"
                role="tab" aria-controls="nav-rent" aria-selected="false">Аренда</button>
      </div>
    </nav>

    <div class="tab-content mt-3" id="nav-tabContent1">
      <div class="tab-pane fade" id="nav-rent" role="tabpanel" aria-labelledby="nav-rent-tab">
        <div class="container">
          <h2 class="mb-3 text-center">Объявления об аренде</h2>
          <div class="row justify-content-center row-cols-1 row-cols-md-2 g-4">
            <c:forEach var="apartment" items="${apartmentsRentAdmin}">
              <c:if test="${apartment.getStatus().equals('Отправлено на проверку')}">
                <div class="col">
                  <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">
                    <div class="flex-shrink-0 me-3">
                      <a href="<c:url value='/details?id=${apartmentService.getApartId(apartment)}&type=rent'/>" class="text-decoration-none">
                        <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), 'rent')}"
                             class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                      </a>
                    </div>

                    <div class="flex-grow-1">
                      <h5 class="title mb-1">${apartment.title}</h5>
                      <p class="fw-bold mb-1">${apartment.priceRent} ₽</p>
                      <p class="text-muted mb-0">${apartment.address}</p>
                    </div>

                    <div class="text-end ms-auto">
                      <p class="text-warning mb-1">${apartment.status}</p>
                      <div class="btn-group">
                        <form method="post" action="<c:url value='/admin'/>">
                          <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                          <input type="hidden" name="dealType" value="rent">
                          <button class="btn btn-success btn-sm me-2" type="submit" name="action" value="approve">Одобрить</button>
                        </form>
                        <form method="post" action="<c:url value='/admin'/>">
                          <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                          <input type="hidden" name="dealType" value="rent">
                          <button class="btn btn-danger btn-sm" type="submit" name="action" value="reject">Отклонить</button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </c:if>
            </c:forEach>
          </div>
        </div>
      </div>

      <div class="tab-pane fade show active" id="nav-sale" role="tabpanel" aria-labelledby="nav-sale-tab">
        <div class="container">
          <h2 class="mb-3 text-center">Объявления о продаже</h2>
          <div class="row justify-content-center row-cols-1 row-cols-md-2 g-4">
            <c:forEach var="apartment" items="${apartmentsSaleAdmin}">
              <c:if test="${apartment.getStatus().equals('Отправлено на проверку')}">
                <div class="col">
                  <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">
                    <div class="flex-shrink-0 me-3">
                      <a href="<c:url value='/details?id=${apartmentService.getApartId(apartment)}&type=sale'/>" class="text-decoration-none">
                        <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), 'sale')}"
                             class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                      </a>
                    </div>

                    <div class="flex-grow-1">
                      <h5 class="title mb-1">${apartment.title}</h5>
                      <p class="fw-bold mb-1">${apartment.priceSale} ₽</p>
                      <p class="text-muted mb-0">${apartment.address}</p>
                    </div>

                    <div class="text-end ms-auto">
                      <p class="text-warning mb-1">${apartment.status}</p>
                      <div class="btn-group">
                        <form method="post" action="<c:url value='/admin'/>">
                          <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                          <input type="hidden" name="dealType" value="sale">
                          <button class="btn btn-success btn-sm me-2" type="submit" name="action" value="approve">Одобрить</button>
                        </form>
                        <form method="post" action="<c:url value='/admin'/>">
                          <input type="hidden" name="apartmentId" value="${apartmentService.getApartId(apartment)}">
                          <input type="hidden" name="dealType" value="sale">
                          <button class="btn btn-danger btn-sm" type="submit" name="action" value="reject">Отклонить</button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </c:if>
            </c:forEach>
          </div>
        </div>
      </div>
    </div>
  </div>

</t:nav>