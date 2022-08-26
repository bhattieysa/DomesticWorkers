package com.example.projectotp;

public class MaidRegistrationModel {
    String FullName,Number,Category,Gender,Religion,Education,City,Age,Experience,Cnic,Address;

    public MaidRegistrationModel(){

    }


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }

    public String getCnic() {
        return Cnic;
    }

    public void setCnic(String cnic) {
        Cnic = cnic;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public MaidRegistrationModel(String fullName, String mobile, String category, String gender,
                                 String religion, String education, String city, String age, String experience,
                                 String cnic, String address){

    this.FullName = fullName;
    this.Number = mobile;
    this.Category = category;
    this.Gender = gender;
    this.Religion = religion;
    this.Education = education;
    this.City = city;
    this.Age = age;
    this.Experience = experience;
    this.Cnic = cnic;
    this.Address = address;

    }


}

