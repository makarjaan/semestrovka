<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:nav title="Новое объявление">
    <div class="container col-md-8 mt-3">
        <h1 class="text-center mb-4">Добавить новую публикацию</h1>
        <form action="/addadvert" method="post" enctype="multipart/form-data" class="shadow p-4 rounded">
            <div class="mb-3">
                <label for="type" class="form-label">Тип объявления</label>

                <select class="form-select" id="type" name="type" required>
                    <option value="" disabled selected>Выберите тип</option>
                    <option value="rent">Аренда</option>
                    <option value="sale">Продажа</option>
                </select>

            </div>

            <div class="mb-3">
                <label for="title" class="form-label">Заголовок</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="Введите заголовок" required>
            </div>


            <div class="mb-3">
                <label for="description" class="form-label">Описание</label>
                <textarea class="form-control" id="description" name="description" rows="4" placeholder="Введите описание" required></textarea>
            </div>


            <div class="mb-3">
                <label for="address" class="form-label">Адрес</label>
                <input type="text" class="form-control" id="address" name="address" placeholder="Введите адрес" required>
            </div>

            <div class="mb-3">
                <label for="typeOfHousing" class="form-label">Вид сдачи в аренду</label>

                <select class="form-select" id="typeOfHousing" name="typeOfHousing" required>
                    <option value="" disabled selected>Выберите тип</option>
                    <option value="apartment">Квартира</option>
                    <option value="studio">Студия</option>
                    <option value="house">Дом</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="area" class="form-label">Площадь (м²)</label>
                <input type="number" class="form-control" id="area" name="area" placeholder="Введите площадь" required>
            </div>

            <div class="mb-3">
                <label for="typeOfDelivery" class="form-label">Вид сдачи в аренду</label>

                <select class="form-select" id="typeOfDelivery" name="typeOfDelivery" required>
                    <option value="" disabled selected>Выберите тип</option>
                    <option value="dailyRent">Посуточно</option>
                    <option value="longRent">На долгий срок</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">Цена (₽)</label>
                <input type="number" class="form-control" id="price" name="price" placeholder="Введите цену" required>
            </div>


            <div class="mb-3">
                <label for="rooms" class="form-label">Количество комнат</label>
                <select class="form-select" id="rooms" name="rooms" required>
                    <option value="" disabled selected>Выберите количество комнат</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5+</option>
                </select>
            </div>


            <div class="mb-3">
                <label for="images" class="form-label">Добавить изображения</label>
                <input type="file" class="form-control" id="images" name="images[]" accept="image/*" multiple>
            </div>



            <button type="submit" class="btn btn-primary">Добавить публикацию</button>
        </form>
    </div>
</t:nav>