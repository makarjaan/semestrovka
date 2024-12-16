<%@tag description="Navigation menu tag" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:mainLayout title="${title}">
  <nav class="navbar navbar-expand-md px-4">
    <div class="container">

      <a class="navbar-brand d-flex align-items-center">
        <img src="<c:url value='https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780257/IMG_7118_ljnhbj.jpg'/>" width="30" height="30" class="d-inline-block align-text-top m-2" alt="logo">
        <span class="logo">Lodgify</span>
      </a>


      <div class="collapse navbar-collapse">
        <ul class="navbar-nav ms-auto">
          <c:if test="${user != null}">
            <c:set var="profilePhotoUrl"
                   value="${user.profilePhotoUrl != null ?
                              user.profilePhotoUrl : 'https://res.cloudinary.com/dqm8yufmb/image/upload/v1733780390/%D0%94%D0%B5%D1%84%D0%BE%D0%BB%D1%82%D0%BD%D0%B0%D1%8F_%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D0%B0_adpx3f.jpg'}" />
            <li class="nav-item dropdown d-flex align-items-center">
              <a class="nav-link dropdown-toggle d-flex align-items-center" id="profileDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <img
                        src="${profilePhotoUrl}"
                        class="rounded-circle me-2"
                        width="40"
                        height="40">
                <span class="me-2 fw-bold user-name">${user.name}</span>
              </a>
              <ul class="dropdown-menu text-small" aria-labelledby="profileDropdown">
                <li><a class="dropdown-item" href="<c:url value='/settings'/>">Настройки</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="<c:url value='/logout'/>">Выйти</a></li>
              </ul>
            </li>

            <div class="nav-item ms-2 d-flex align-items-center">
              <a href="<c:url value='/addadvert'/>" class="custom-btn" style="font-size: 13px">Разместить объявление</a>
            </div>
          </c:if>

          <c:if test="${user == null}">
            <li class="nav-item">
              <a href="<c:url value='/authorization'/>" class="custom-btn">Войти</a>
            </li>
          </c:if>

        </ul>
      </div>


    </div>
  </nav>

  <div class="custom-divider"></div>

  <nav class="navbar navbar-expand-md px-4">
    <div class="container">
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav me-auto">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/main"/>">Главная</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/apartments">Квартиры</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/calculator">Калькулятор</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <jsp:doBody/>
</t:mainLayout>