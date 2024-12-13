<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Регистрация">
  <div class="registration-page">
    <div class="background-container">
      <div class="registration-form">

        <div style="display: flex; justify-content: space-between; align-items: center;">
          <h2 class="text-center title">Регистрация</h2>
          <a href="<c:url value="/main"/>" class="btn-close"></a>
        </div>

        <form id="registrationForm" class="form-horizontal" action="<c:url value = "/registration"/>" method="post">

          <div class="mb-3">
            <p>Имя пользователя</p>
            <input type="text" name="username" class="form-control" required>
          </div>

          <div class="mb-3">
            <p>Электронная почта</p>
            <input type="email" name="email" class="form-control" required>
          </div>

          <div class="mb-3">
            <p>Мобильный телефон</p>
            <input type="tel" name="phone" class="form-control" placeholder="+7 (XXX) XXX-XX-XX" pattern="^(\+7|7|8)[0-9]{10}$" required >
          </div>

          <div class="mb-3">
            <p>Пароль</p>
            <input type="password" name="password" class="form-control" required>
          </div>

          <div class="mb-3">
            <label class="form-check-label">Уже есть аккаунт? <a href=<c:url value = "/authorization"/>>Войти</a></label>
          </div>

          <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">${errorMessage}</div>
          </c:if>

          <button type="submit" class="btn-primary w-100" >Зарегистрироваться</button>
        </form>
      </div>
    </div>
  </div>
</t:mainLayout>

