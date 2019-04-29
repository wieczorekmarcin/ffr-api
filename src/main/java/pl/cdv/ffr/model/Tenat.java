package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class Tenat extends BaseEntity {

    private String firstName;
    private String lastName;
    private String pesel;
    private String idNumber;
    private String email;
    private String phoneNumber;
    private String propertyAddress;

    public Tenat() {
    }

    public Tenat(String firstName, String lastName, String pesel, String idNumber, String email, String phoneNumber, String propertyAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.propertyAddress = propertyAddress;
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

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}
