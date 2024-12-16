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
                        <div class="tab-content mt-3" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-sale" role="tabpanel" aria-labelledby="nav-sale-tab">
                                <div class="container">
                                    <div class="card flex-row align-items-center" style="border: none; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
                                        <img src="https://via.placeholder.com/150" class="card-img-left" style="width: 150px; height: 150px; object-fit: cover; border-radius: 8px;">
                                        <div class="card-body">
                                            <h5 class="card-title">Card Title</h5>
                                            <p class="card-text">This is an example of a card where the image is on the left and the text is on the right, spanning the full width of the container.</p>
                                            <a href="#" class="custom-btn">Read More</a>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="tab-pane fade" id="nav-rent" role="tabpanel" aria-labelledby="nav-rent-tab">
                                Контент для вкладки "Аренда"
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


                <div class="container mt-3">
                    <a href="#" id="deleteAccountLink" class="text fw-bold" style="color: #75000E">Удалить аккаунт</a>
                </div>


                <form id="deleteForm" action="<c:url value="/settings"/>" method="post">
                    <div class="modal" id="confirmationModal" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title fw-bold fs-4" style="color: #1a1d20" id="confirmationModalLabel">Подтверждение удаления</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                </div>
                                <div class="modal-body" style="color: #555555">
                                    Вы уверены, что хотите удалить аккаунт?
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn" style="color: #75000E" id="confirmDelete" name="action" value="deleteAccount">Удалить</button>
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Отмена</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

                <script>
                    const deleteLink = document.getElementById("deleteAccountLink");
                    const confirmationModal = new bootstrap.Modal(document.getElementById('confirmationModal'));

                    deleteLink.addEventListener("click", function (e) {
                        e.preventDefault()
                        confirmationModal.show();
                    });
                </script>

            </section>
        </div>
    </div>
  </div>

  <script src="${pageContext.request.contextPath}/js/api-sidebar.js"></script>

</t:nav>

