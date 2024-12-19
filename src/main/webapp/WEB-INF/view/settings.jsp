<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Настройки">
    <div class="container">
        <div class="row mt-4">
            <div class="col-md-3 me-4 border-end" >
                <div class="d-flex flex-column align-items-center text-center">
                    <img src="${user.profilePhotoUrl != null ? user.profilePhotoUrl : 'https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780390/%D0%94%D0%B5%D1%84%D0%BE%D0%BB%D1%82%D0%BD%D0%B0%D1%8F_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D0%B0_adpx3f.jpg'}"
                         class="rounded-circle mb-3" width="150" height="150">
                    <h5 class="text-center text-break" style="font-size: 20px; color: #1a1d20; max-width: 200px;">${user.name}</h5>
                </div>

                <hr class="my-3">

                <form action="<c:url value="/settings"/>" method="get">
                    <div class="nav flex-column">
                        <a href="#" class="m-2 px-3 fs-6" data-tab="advertisement">Мои объявления</a>
                        <a href="#" class="m-2 px-3 fs-6" data-tab="profile">Настройки профиля</a>
                        <a href="#" class="m-2 px-3 fs-6" data-tab="security">Безопасность</a>
                        <a href="#" class="m-2 px-3 fs-6" data-tab="notifications">Уведомления</a>
                        <a href="#" class="m-2 px-3 fs-6" data-tab="bookings">Управление бронированиями</a>
                    </div>
                </form>


                <hr class="my-2">

                <div class="nav flex-column">
                    <a href="<c:url value='/logout'/>" class="m-2 px-3 fs-6" style="color: #75000E">Выйти</a>
                </div>

            </div>

            <div class="col-md-7 ms-md-5" id="tabContent">
                <section id="advertisement" class="tab-section d-none">
                    <h2>Мои объявления</h2>

                    <c:if test="${empty apartmentsSale && empty apartmentsRent}">

                        <hr class="my-3">

                        <h3>У вас пока нет объявлений</h3>

                        <form action="<c:url value="/addadvert"/>" method="get">
                            <button type="submit" class="custom-btn  mt-4">Разместить объявление</button>
                        </form>
                    </c:if>

                    <c:if test="${not empty apartmentsSale || not empty apartmentsRent}">
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
                                        <c:if test="${not empty apartmentsSale}">
                                            <c:forEach var="apartment" items="${apartmentsSale}">
                                                <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">

                                                    <div class="flex-shrink-0 me-3">
                                                        <a href="<c:url value="/details?type=sale&id=${apartmentService.getApartId(apartment)}"/>">
                                                            <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "sale")}" class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                                                        </a>
                                                    </div>

                                                    <div class="flex-grow-1">
                                                        <h5 class="title mb-1">${apartment.title}</h5>
                                                        <p class="fw-bold mb-1">${apartment.priceSale} ₽</p>
                                                        <p class="text-muted mb-0">${apartment.address}</p>
                                                    </div>

                                                    <div class="text-end ms-auto">
                                                        <div class="dropdown">
                                                            <button class="btn btn-light border-0" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                                <i class="bi bi-three-dots"></i>
                                                            </button>
                                                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton1">
                                                                <form name="unpublishAddvert" method="post" onsubmit="return confirm('Вы уверены, что хотите снять с публикации это объявление?');">
                                                                    <input type="hidden" name="dealType" value="sale">
                                                                    <input type="hidden" name="addvertApartIdForUnpublish" value="${apartmentService.getApartId(apartment)}">
                                                                    <input type="hidden" name="status" value="Снято с публикации">
                                                                    <button class="dropdown-item text-warning" type="submit">Снять с публикации</button>
                                                                </form>
                                                                <li>
                                                                    <form name="deleteAddvert" method="post" onsubmit="return confirm('Вы уверены, что хотите удалить это объявление?');">
                                                                        <input type="hidden" name="dealType" value="sale">
                                                                        <input type="hidden" name="addvertApartIdForDelete" value="${apartmentService.getApartId(apartment)}">
                                                                        <button class="dropdown-item text-danger" type="submit">Удалить</button>
                                                                    </form>
                                                                </li>
                                                            </ul>
                                                        </div>

                                                        <c:if test="${apartment.status == 'Отправлено на проверку'}">
                                                            <p class="text-info mb-1 me-3">${apartment.status}</p>
                                                        </c:if>

                                                        <c:if test="${apartment.status == 'Отказано в публикации'}">
                                                            <p class="text-danger mb-1 me-3">${apartment.status}</p>
                                                        </c:if>

                                                        <c:if test="${apartment.status == 'Опубликовано'}">
                                                            <p class="text-success mb-1 me-3">${apartment.status}</p>
                                                        </c:if>
                                                        <c:if test="${apartment.status == 'Снято с публикации'}">
                                                            <p class="text-dark mb-1 me-3">${apartment.status}</p>
                                                        </c:if>
                                                    </div>
                                                </div>


                                            </c:forEach>
                                        </c:if>

                                        <c:if test="${empty apartmentsSale}">
                                            <p>Нет доступных объявлений</p>
                                        </c:if>
                                    </div>
                                </div>


                                <div class="tab-content mt-3" id="nav-tabContent2">
                                    <div class="tab-pane fade show" id="nav-rent" role="tabpanel" aria-labelledby="nav-sale-tab">
                                        <div class="container">
                                            <c:if test="${not empty apartmentsRent}">
                                                <c:forEach var="apartment" items="${apartmentsRent}">
                                                    <div class="card mb-3 shadow-sm border-0 d-flex flex-row align-items-center">

                                                        <div class="flex-shrink-0 me-3">
                                                            <a href="<c:url value="details?type=rent&id=${apartmentService.getApartId(apartment)}"/>" class="text-decoration-none">
                                                                <img src="${photoService.getMainPhoto(apartmentService.getApartId(apartment), "rent")}" class="rounded" style="width: 150px; height: 150px; object-fit: cover;">
                                                            </a>
                                                        </div>

                                                        <div class="flex-grow-1">
                                                            <h5 class="title mb-1">${apartment.title}</h5>
                                                            <p class="fw-bold mb-1">${apartment.priceRent} ₽</p>
                                                            <p class="text-muted mb-0">${apartment.address}</p>
                                                        </div>

                                                        <div class="text-end ms-auto">
                                                            <div class="dropdown">
                                                                <button class="btn btn-light border-0" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                                                                    <i class="bi bi-three-dots"></i>
                                                                </button>
                                                                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton2">
                                                                    <li>
                                                                        <a class="dropdown-item" href="<c:url value="/addadvert"/>">Изменить публикацию</a>
                                                                    </li>
                                                                    <li>
                                                                        <form name="deleteAddvert" method="post" onsubmit="return confirm('Вы уверены, что хотите удалить это объявление?');">
                                                                            <input type="hidden" name="dealType" value="rent">
                                                                            <input type="hidden" name="addvertApartIdForDelete" value="${apartmentService.getApartId(apartment)}">
                                                                            <button class="dropdown-item text-danger" type="submit">Удалить</button>
                                                                        </form>
                                                                    </li>
                                                                </ul>
                                                            </div>

                                                            <c:if test="${apartment.status == 'Отправлено на проверку'}">
                                                                <p class="text-info mb-1 me-3">${apartment.status}</p>
                                                            </c:if>

                                                            <c:if test="${apartment.status == 'Отказано в публикации'}">
                                                                <p class="text-danger mb-1 me-3">${apartment.status}</p>
                                                            </c:if>

                                                            <c:if test="${apartment.status == 'Опубликовано'}">
                                                                <p class="text-success mb-1 me-3">${apartment.status}</p>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:if>

                                            <c:if test="${empty apartmentsRent}">
                                                <p>Нет доступных объявлений</p>
                                            </c:if>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </c:if>
                </section>

                <section id="profile" class="tab-section d-none">
                    <h2>Настройки профиля</h2>
                    <form action="<c:url value="/settings"/>" method="post" enctype="multipart/form-data">

                        <div class="mb-3">
                            <label for="profilePhoto" class="form-label">Фото профиля:</label>
                            <div>
                                <img src="${user.profilePhotoUrl != null ?
                             user.profilePhotoUrl : 'https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780390/%D0%94%D0%B5%D1%84%D0%BE%D0%BB%D1%82%D0%BD%D0%B0%D1%8F_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D0%B0_adpx3f.jpg'}"
                                     class="rounded-circle ms-5 mt-2" width="100" height="100">
                            </div>

                            <button type="submit" name="action" value="deletePhoto" class="btn mt-2">Удалить фотографию</button>
                        </div>


                        <div class="mb-3">
                            <label for="profilePhoto" class="form-label">Загрузить новое фото:</label>
                            <input type="file" class="form-control" id="profilePhoto" name="profilePhoto">
                        </div>


                        <div class="mb-3">
                            <label for="firstName" class="form-label">Имя:</label>
                            <input type="text" name="changeUserName" class="form-control" id="firstName" value="${user.name}">
                        </div>

                        <button type="submit" class="btn-primary" name="action" value="uploadPhoto">Сохранить изменения</button>

                    </form>
                </section>

                <section id="security" class="tab-section d-none">
                    <h2>Безопасность</h2>

                    <form action="<c:url value="/settings"/>" method="post">

                        <div class="mb-3">
                            <label for="currentPassword" class="form-label">Текущий пароль:</label>
                            <input type="password" class="form-control" id="currentPassword" name="currentPassword">
                        </div>

                        <div class="mb-3">
                            <label for="newPassword" class="form-label">Новый пароль:</label>
                            <input type="password" class="form-control" id="newPassword" name="newPassword">
                        </div>

                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">${errorMessage}</div>
                        </c:if>

                        <button type="submit" class="btn-primary">Обновить пароль</button>
                    </form>

                    <hr class="my-2">



                    <form  action="<c:url value="/settings"/>" method="post" onsubmit="return confirm('Вы уверены, что хотите удалить этот аккаунт?');">
                        <button class="text-danger custom-btn" type="submit" name="action" value="deleteAccount">Удалить</button>
                    </form>
                </section>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/js/api-sidebar.js"></script>

</t:nav>


