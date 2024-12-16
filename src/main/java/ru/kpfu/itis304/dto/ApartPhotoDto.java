package ru.kpfu.itis304.dto;

public class ApartPhotoDto {
    private int apartId;
    private String photoUrl;

    public ApartPhotoDto(int apartId, String photoUrl) {
        this.apartId = apartId;
        this.photoUrl = photoUrl;
    }

    public int getApartId() {
        return apartId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
