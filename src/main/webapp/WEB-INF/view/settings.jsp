<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Settings page">
  <div class="container">
      <form id="profileForm" class="form-horizontal" action="<c:url value = "/settings"/>" method="post" enctype="multipart/form-data">
          <div class="row mt-4">
              <div class="col-md-3 me-2 border-end" >
                  <div class="d-flex flex-column align-items-center text-center">
                        <img src="${user.profilePhotoUrl != null ? user.profilePhotoUrl : 'https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780390/%D0%94%D0%B5%D1%84%D0%BE%D0%BB%D1%82%D0%BD%D0%B0%D1%8F_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D0%B0_adpx3f.jpg'}"
                             class="rounded-circle mb-3" width="150" height="150">
                        <h5 class="fw-bold user-name fs-4 text-center">${user.name}</h5>
                  </div>

                  <hr class="my-4">

                  <div class="nav flex-column">
                      <a href="?activeTab=advertisement"
                         class="m-2 px-3 fs-6 <c:if test='${activeTab == "advertisement"}'>title</c:if>">Мои объявления</a>
                      <a href="?activeTab=profile"
                         class="m-2 px-3 fs-6 <c:if test='${activeTab == "profile"}'>title</c:if>">Настройки профиля</a>
                      <a href="?activeTab=security"
                         class="m-2 px-3 fs-6 <c:if test='${activeTab == "security"}'>title</c:if>">Безопасность</a>
                      <a href="?activeTab=notifications"
                         class="m-2 px-3 fs-6  <c:if test='${activeTab == "notifications"}'>title</c:if>">Уведомления</a>
                      <a href="?activeTab=bookings"
                         class="m-2 px-3 fs-6 <c:if test='${activeTab == "bookings"}'>title</c:if>">Управление бронированиями</a>
                  </div>

                  <hr class="my-2">

                  <div class="nav flex-column">
                      <a href="<c:url value='/logout'/>" class="m-2 px-3 fs-6">Выйти</a>
                  </div>

              </div>

              <div class="col-md-7 ms-md-5">
                  <section id="advertisement"
                           class="<c:if test="${activeTab == 'advertisement' || empty param.activeTab}">d-block</c:if>
                                  <c:if test="${activeTab != 'advertisement'}">d-none</c:if>">
                    <h2>Мои объявления</h2>

                    <form action="<c:url value="/addadvert"/>" method="post">
                        <button type="submit" class="btn-primary mt-4" >Разместить объявление</button>
                    </form>

                  </section>

                  <section id="profile"
                           class="<c:if test="${activeTab == 'profile'}">d-block</c:if>
                                  <c:if test="${activeTab != 'profile'}">d-none</c:if>">
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
                        <input type="text" name="changeUserName" class="form-control" id="firstName"
                               value="${user.name}">
                      </div>

                      <button type="submit" class="btn-primary" name="action" value="uploadPhoto">Сохранить изменения</button>

                    </form>
                  </section>

                  <section id="security"
                           class="<c:if test="${activeTab == 'security'}">d-block</c:if>
                                  <c:if test="${activeTab != 'security'}">d-none</c:if>">
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
                  </section>
              </div>
          </div>
      </form>
  </div>
</t:nav>
