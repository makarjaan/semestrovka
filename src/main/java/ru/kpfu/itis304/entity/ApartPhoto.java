package ru.kpfu.itis304.entity;

public class ApartPhoto {
    private int id;
    private int apartId;
    private String photoUrl;

    public ApartPhoto(int id, int apartId, String photoUrl) {
        this.id = id;
        this.apartId = apartId;
        this.photoUrl = photoUrl;
    }

    public ApartPhoto(int apartId, String photoUrl) {
        this.apartId = apartId;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApartId() {
        return apartId;
    }

    public void setApartId(int apartId) {
        this.apartId = apartId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
