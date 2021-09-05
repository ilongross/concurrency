package com.ilongross.concurrency.prof_library.company.employes;

public class Address {

    private String country;
    private String state;
    private String city;
    private String street;
    private String house;
    private String apartment;

    public Address(String country, String state, String city, String street, String house, String apartment) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public String getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        return "Address: " + country +
                ", обл. " + state +
                ", г. " + city +
                ", ул. " + street +
                ", д. " + house +
                ", кв. " + apartment + ".";
    }
}
