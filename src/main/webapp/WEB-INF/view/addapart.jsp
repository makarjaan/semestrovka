<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Новое объявление">
    <div class="container">
        <form action="<c:url value='/addadvert'/>" method="post">
            <div class="row mt-4">
                <div class="col-md-3 me-4">
                    <div class="btn-group mb-3">
                        <a href="<c:url value='/addadvert?dealType=rent'/>" class="custom-btn me-3
                            <c:if test="${dealType == 'rent'}">rounded-custom-btn</c:if>
                            <c:if test="${dealType != 'rent'}">custom-btn</c:if>">Аренда</a>
                        <a href="<c:url value='/addadvert?dealType=sale'/>" class="custom-btn
                            <c:if test="${dealType == 'sale'}">rounded-custom-btn</c:if>
                            <c:if test="${dealType != 'sale'}">custom-btn</c:if>">Продажа</a>
                    </div>

                    <c:if test="${not empty errMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${errMessage}
                        </div>
                    </c:if>


                    <c:if test="${dealType == 'rent'}">
                        <div class="mb-3">
                            <label for="rentDuration" class="form-label">Тип аренды</label>
                            <select class="form-select" id="rentDuration" name="rentDuration" required>
                                <option value=""
                                        <c:if test="${empty rentDuration}">selected</c:if>>Выберите тип аренды</option>
                                <option value="short-term"
                                        <c:if test="${rentDuration == 'short-term'}">selected</c:if>>Посуточно</option>
                                <option value="long-term"
                                        <c:if test="${rentDuration == 'long-term'}">selected</c:if>>Долгосрочно</option>
                            </select>
                        </div>

                        <div class="p-3 mt-4 border rounded">
                            <div class="mb-3">
                                <label for="rentPrice" class="form-label fw-bold" style="color: #1a1d20">Цена аренды</label>
                                <div class="input-group">
                                    <span class="input-group-text">₽</span>
                                    <input type="number" class="form-control" id="rentPrice" name="rentPrice" placeholder="Введите цену аренды"
                                           value="${not empty rentPrice ? rentPrice : ''}" required>
                                </div>
                                <div class="form-text">Укажите цену в рублях.</div>
                            </div>
                        </div>

                    </c:if>

                    <c:if test="${dealType == 'sale'}">
                        <div class="p-3 mt-4 border rounded" >
                            <div class="mb-3">
                                <label for="salePrice" class="form-label fw-bold" style="color: #1a1d20">Цена продажи</label>
                                <div class="input-group">
                                    <span class="input-group-text">₽</span>
                                    <input type="number" class="form-control" id="salePrice" name="salePrice" placeholder="Введите цену продажи"
                                           value="${not empty salePrice ? salePrice : ''}" required>
                                </div>
                                <div class="form-text">Укажите цену в рублях.</div>
                            </div>
                        </div>
                    </c:if>

                    <div class="mt-3 mb-3" >
                        <label for="propertyType" class="form-label">Тип недвижимости</label>
                        <select class="form-select" id="propertyType" name="propertyType" required>
                            <option value="" disabled
                                    <c:if test="${empty propertyType}">selected</c:if>>
                                    Выберите тип недвижимости
                            </option>
                            <option value="apartmentSale"
                                    <c:if test="${propertyType == 'apartmentSale'}">selected</c:if>>
                                Квартира
                            </option>
                            <option value="house"
                                    <c:if test="${propertyType == 'house'}">selected</c:if>>
                                Дом
                            </option>
                            <option value="room"
                                    <c:if test="${propertyType == 'room'}">selected</c:if>>
                                Комната
                            </option>
                        </select>
                    </div>


                    <hr class="my-3">


                    <button type="submit" class="rounded-custom-btn" style="width: auto">Сохранить</button>

                </div>

                <div class="col-md-7 ms-md-5">
                    <h2>Добавить объявление</h2>

                    <div class="mb-3">
                        <label for="title" class="form-label">Заголовок</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Введите заголовок"
                               value="${not empty title ? title : ''}" required>
                    </div>

                    <div class="mb-3">
                        <label for="description" class="form-label">Описание</label>
                        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Введите описание">${not empty description ? description : ''}</textarea>
                    </div>

                    <div class="mb-3">
                        <label for="roomCount" class="form-label">Количество комнат</label>
                        <select class="form-select" id="roomCount" name="roomCount" required>
                            <option value="" disabled
                                    <c:if test="${empty roomCount}">selected</c:if>>Выберите количество комнат</option>
                            <option value="studio"
                                    <c:if test="${roomCount == 'studio'}">selected</c:if>>Студия</option>
                            <option value="one-bedroom"
                                    <c:if test="${roomCount == 'one-bedroom'}">selected</c:if>>1-комнатная</option>
                            <option value="two-bedroom"
                                    <c:if test="${roomCount == 'two-bedroom'}">selected</c:if>>2-комнатная</option>
                            <option value="three-bedroom"
                                    <c:if test="${roomCount == 'three-bedroom'}">selected</c:if>>3-комнатная</option>
                            <option value="four-bedroom"
                                    <c:if test="${roomCount == 'four-bedroom'}">selected</c:if>>4-комнатная</option>
                            <option value="five-plus-bedroom"
                                    <c:if test="${roomCount == 'five-plus-bedroom'}">selected</c:if>>5+ комнат</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="area" class="form-label">Площадь (м²)</label>
                        <input type="number" class="form-control" id="area" name="area" placeholder="Введите площадь"
                               value="${not empty area ? area : ''}" required>
                    </div>

                    <div class="my-3">
                        <label for="selectedAddress" class="title mb-2" style="font-weight: normal"> Адрес:</label>
                        <input type="text" class="form-control" style="font-weight: normal; color: #1a1d20" id="selectedAddress"
                               name="address" value="${address != null && !address.equals("Не выбран") ? address : 'Не выбран'}" readonly>
                    </div>

                    <c:if test="${not empty mapErrMessage}">
                        <div class="alert alert-danger" role="alert">
                                ${mapErrMessage}
                        </div>
                    </c:if>

                    <div id="map" style="width: 100%; height: 400px;"></div>





                </div>
            </div>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/js/map.js"></script>

</t:nav>