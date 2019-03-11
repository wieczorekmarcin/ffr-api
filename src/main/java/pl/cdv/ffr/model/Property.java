package pl.cdv.ffr.model;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class Property {
    private Long id;
    private String street;
    private String number;
    private String city;
    private String yardage;
    private List<Flat> flats;

    public Property() {
    }

    public Property(Long id, String street, String number, String city, String yardage, List<Flat> flats) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.yardage = yardage;
        this.flats = flats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getYardage() {
        return yardage;
    }

    public void setYardage(String yardage) {
        this.yardage = yardage;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }
}
