package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
public class Rentier extends BaseEntity {

    @Column(name = "FIRSTNAME", length = 50)
    private String firstName;

    @Column(name = "LASTNAME", length = 50)
    private String lastName;

    private String pesel;
    private String idNumber;
    private String email;
    private String phoneNumber;

    @OneToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name = "RENTIER_PROPERTY",
            joinColumns = {@JoinColumn(name = "RENTIER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID")})
    private List<Property> properties;

    @OneToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name = "RENTIER_BILL",
            joinColumns = {@JoinColumn(name = "RENTIER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "BILL_ID", referencedColumnName = "ID")})
    private List<Bill> bills;

    @OneToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name = "RENTIER_TENAT",
            joinColumns = {@JoinColumn(name = "RENTIER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "TENAT_ID", referencedColumnName = "ID")})
    private List<Tenat> tenats;

    public Rentier() {
    }

    public Rentier(String firstName, String lastName, String pesel, String idNumber, String email, String phoneNumber, List<Property> properties, List<Bill> bills, List<Tenat> tenats) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.properties = properties;
        this.bills = bills;
        this.tenats = tenats;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Tenat> getTenats() {
        return tenats;
    }

    public void setTenats(List<Tenat> tenats) {
        this.tenats = tenats;
    }
}
