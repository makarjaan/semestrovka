package ru.kpfu.itis304.enums;

public enum ApartmentStatus {
    SENT_FOR_VERIFICATION("Отправлено на проверку"),
    APPROVED("Публикация одобрена"),
    REFUSED("Отказано в публикации"),
    PUBLISHED("Опубликовано"),
    COMPLETED("Сделка завершена");

    private final String apartStatus;


    ApartmentStatus(String apartStatus) {
        this.apartStatus = apartStatus;
    }

    public String getApartStatus() {
        return apartStatus;
    }

    public static ApartmentStatus fromString(String status) {
        for (ApartmentStatus apartmentStatus : ApartmentStatus.values()) {
            if (apartmentStatus.getApartStatus().equalsIgnoreCase(status)) {
                return apartmentStatus;
            }
        }
        throw new IllegalArgumentException("Неизвестный статус: " + status);
    }

}
