<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Настройки">
  <div class="container">
    <div class="row mt-4">
        <div class="col-md-3 me-2 border-end" >
            <div class="d-flex flex-column align-items-center text-center">
                <img src="${user.profilePhotoUrl != null ? user.profilePhotoUrl : 'https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780390/%D0%94%D0%B5%D1%84%D0%BE%D0%BB%D1%82%D0%BD%D0%B0%D1%8F_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D0%B0_adpx3f.jpg'}"
                     class="rounded-circle mb-3" width="150" height="150">
                <h5 class="fw-bold user-name fs-4 text-center" style="color: #1a1d20">${user.name}</h5>
            </div>

            <hr class="my-4">

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

                <form id="addnewappartment" class="form-horizontal" action="<c:url value="/addadvert"/>" method="post">
                    <button type="submit" class="btn-primary mt-4" >Разместить объявление</button>
                </form>
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
</t:nav>

<script>

    $(document).ready(function () {
        $("a[data-tab]").click(function (e) {
            e.preventDefault();
            var tabName = $(this).data("tab");

            loadTabContent(tabName);

            $(".tab-section").addClass("d-none");
            $("#" + tabName).removeClass("d-none");

            $("a[data-tab]").removeClass("title");
            $(this).addClass("title");
        });


        function loadTabContent(tabName) {
            $.get('/settings', { activeTab: tabName }, function(response) {
                $('#tabContent').html(response);
            });
        }


        var initialTab = 'advertisement';
        loadTabContent(initialTab);
        $("#" + initialTab).removeClass("d-none");
    });


</script>