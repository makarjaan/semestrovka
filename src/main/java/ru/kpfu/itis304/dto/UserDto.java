package ru.kpfu.itis304.dto;

public class UserDto {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String profilePhotoUrl;

    public UserDto(String name, String email, String phone, String password, String profilePhotoUrl) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}
