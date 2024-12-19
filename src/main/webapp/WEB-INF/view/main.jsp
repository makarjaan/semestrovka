<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Главная страница">
    <div class="main-page">
        <div class="background-container-main">
            <div class="search-container text-center py-5">
                <div class="text-center">
                    <h1 class="text-white text-shadow search-heading">Найдите жильё рядом</h1>
                </div>
                <form method="get" action="<c:url value='/apartments'/>" class="search-form d-flex justify-content-center gap-2">
                    <input
                            type="text"
                            id="address"
                            name="address"
                            placeholder="Введите адрес, город или регион (например, Татарстан)"
                            class="form-control w-50">
                    <button type="submit" class="btn btn-primary">Найти</button>
                </form>
            </div>
        </div>
        <div class="container py-5">
            <h2 class="text-center mb-4">Популярные категории</h2>
            <div class="row g-4">

                <div class="col-md-4">
                    <div class="card">
                        <img src="https://res.cloudinary.com/dqm8yufmb/image/upload/v1734635415/i_sftbit.jpg" class="card-img-top" alt="Квартира">
                        <div class="card-body text-center">
                            <h5 class="card-title">Квартиры</h5>
                            <p class="card-text">Идеально подходит для жизни в городе.</p>
                            <a href="<c:url value="/apartments"/>" class="custom-btn">Посмотреть</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card">
                        <img src="https://res.cloudinary.com/dqm8yufmb/image/upload/v1734635381/home-house-pool-derevyannyy_nvumc0.jpg" class="card-img-top" alt="Вилла">
                        <div class="card-body text-center">
                            <h5 class="card-title">Дома</h5>
                            <p class="card-text">Просторные и роскошные дома для отдыха.</p>
                            <a href="<c:url value="/apartments"/>" class="custom-btn">Посмотреть</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card">
                        <img src="https://res.cloudinary.com/dqm8yufmb/image/upload/v1734635462/contemporary-residence-boca-raton-florida-interiors-by-steven-g-img_b0c1626802b1b5d1_9-6223-1-56d34ae_xbsvxv.jpg" class="card-img-top" alt="Студия">
                        <div class="card-body text-center">
                            <h5 class="card-title">Студии</h5>
                            <p class="card-text">Доступные варианты для студентов и молодых профессионалов.</p>
                            <a href="<c:url value="/apartments"/>" class="custom-btn">Посмотреть</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:nav>

