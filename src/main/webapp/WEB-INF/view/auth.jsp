<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Автороизация">
  <div class="registration-page">
    <div class="background-container">
      <div class="registration-form">

        <div style="display: flex; justify-content: flex-end;">
          <a href="<c:url value="/main"/>" class="btn-close"></a>
        </div>

        <h2 class="text-center title">Войдите в аккаунт</h2>

        <form id="registrationForm" class="form-horizontal" action="<c:url value = "/authorization"/>" method="post">
          <div class="mb-3">
            <p>Электронная почта</p>
            <input type="email" name="email" class="form-control" required>
          </div>

          <div class="mb-3">
            <p>Пароль</p>
            <input type="password" name="password" class="form-control" required>
          </div>

          <div class="mb-3">
            <label class="form-check-label">Ещё нет аккаунта? <a href=<c:url value = "/registration"/>>Зарегистрироваться</a></label>
          </div>

          <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger" role="alert">
                ${errorMessage}
            </div>
          </c:if>

          <button type="submit" class="btn-primary w-100">Войти</button>
        </form>

      </div>
    </div>
  </div>
</t:mainLayout>