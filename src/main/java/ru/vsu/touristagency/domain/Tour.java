package ru.vsu.touristagency.domain;

import java.util.Objects;

public class Tour {
    private Long id;
    private String country;
    private String hotel;
    private Boolean nutrition;
    private Double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public boolean getNutrition() {
        return nutrition;
    }

    public void setNutrition(boolean nutrition) {
        this.nutrition = nutrition;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Double.compare(tour.cost, cost) == 0 &&
                Objects.equals(id, tour.id) &&
                country.equals(tour.country) &&
                hotel.equals(tour.hotel) &&
                nutrition.equals(tour.nutrition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, hotel, nutrition, cost);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", hotel='" + hotel + '\'' +
                ", nutrition='" + nutrition + '\'' +
                ", cost=" + cost +
                '}';
    }

    public Tour() {
    }

    public Tour(Long id) {
        this.id = id;
    }

    public Tour(String country, String hotel, Boolean nutrition, Double cost) {
        this.country = country;
        this.hotel = hotel;
        this.nutrition = nutrition;
        this.cost = cost;
    }

    public Tour(Long id, String country, String hotel, Boolean nutrition, Double cost) {
        this.id = id;
        this.country = country;
        this.hotel = hotel;
        this.nutrition = nutrition;
        this.cost = cost;
    }
}
