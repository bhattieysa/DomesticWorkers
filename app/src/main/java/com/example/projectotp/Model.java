package com.example.projectotp;

public class Model {
    String FullName,Email,Password,CountryCode,Phone;

    public Model(){


    }

    public Model(String Fullname, String email, String password, String countrycode, String phone) {
        this.FullName = Fullname;
        this.Email = email;
        this.Password = password;
        this.CountryCode = countrycode;
        this.Phone = phone;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
