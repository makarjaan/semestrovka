package ru.kpfu.itis304.dto;

public class ApartPhotoDto {
    private int apartId;
    private String photoUrl;
    private String typeApart;

    public ApartPhotoDto(int apartId, String photoUrl, String typeApart) {
        this.apartId = apartId;
        this.photoUrl = photoUrl;
        this.typeApart = typeApart;
    }

    public int getApartId() {
        return apartId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTypeApart() {
        return typeApart;
    }
}
