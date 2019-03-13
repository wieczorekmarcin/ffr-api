package pl.cdv.ffr.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "PROPERTY")
public class Property {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_seq")
    @SequenceGenerator(name = "property_seq", sequenceName = "property_seq", allocationSize = 1)
    private Long id;

    @Column(name = "STREET")
    @NotNull
    private String street;

    @Column(name = "STREET_NUMBER")
    @NotNull
    private String streetNumber;

    @Column(name = "CITY")
    @NotNull
    private String city;

    @Column(name = "YARDAGE")
    @NotNull
    private String yardage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PROPERTY_FLAT",
            joinColumns = {@JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "FLAT_ID", referencedColumnName = "ID")})
    private List<Flat> flats;

    public Property() {
    }

    public Property(Long id, String street, String streetNumber, String city, String yardage, List<Flat> flats) {
        this.id = id;
        this.street = street;
        this.streetNumber = streetNumber;
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

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String number) {
        this.streetNumber = streetNumber;
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
