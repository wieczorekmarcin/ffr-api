package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Tenat extends BaseEntity {

    @Column(name = "FIRSTNAME", length = 50)
    private String firstName;

    @Column(name = "LASTNAME", length = 50)
    private String lastName;

    private String pesel;
    private String idNumber;
    private String email;
    private String phoneNumber;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TENAT_PROPERTY",
            joinColumns = {@JoinColumn(name = "TENAT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PROPERTY_ID", referencedColumnName = "ID")})
    private Property property;

    public Tenat() {
    }

    public Tenat(String firstName, String lastName, String pesel, String idNumber, String email, String phoneNumber, Property property) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.property = property;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
