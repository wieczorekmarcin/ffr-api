package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
public class Property extends BaseEntity {

    private String title;
    private String description;
    private String price;
    private String bail;
    private String surface;
    private String roomsNumber;
    private String street;
    private String postCode;
    private String city;
    private String buildingType;
    private String floor;
    private String floorsNumber;
    private String buildingMaterial;
    private String windows;
    private String heating;
    private Date availableFrom;

    @ElementCollection
    private List<String> additionalInformation;

    @ElementCollection
    @Transient
    private List<String> images;

    @ElementCollection
    private List<String> imagesUrls;

    @Enumerated(EnumType.STRING)
    private PropertyStatus propertyStatus;

    @OneToMany
    private List<Bill> bills;

    @OneToMany
    private List<Alert> alerts;

    @OneToMany
    private List<Invoice> invoices;

    public Property() {
    }

    public Property(String title, String description, String price, String bail, String surface, String roomsNumber, String street, String postCode, String city, String buildingType, String floor, String floorsNumber, String buildingMaterial, String windows, String heating, Date availableFrom, List<String> additionalInformation, List<String> images, List<String> imagesUrls, PropertyStatus propertyStatus, List<Bill> bills, List<Alert> alerts, List<Invoice> invoices) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.bail = bail;
        this.surface = surface;
        this.roomsNumber = roomsNumber;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
        this.buildingType = buildingType;
        this.floor = floor;
        this.floorsNumber = floorsNumber;
        this.buildingMaterial = buildingMaterial;
        this.windows = windows;
        this.heating = heating;
        this.availableFrom = availableFrom;
        this.additionalInformation = additionalInformation;
        this.images = images;
        this.imagesUrls = imagesUrls;
        this.propertyStatus = propertyStatus;
        this.bills = bills;
        this.alerts = alerts;
        this.invoices = invoices;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBail() {
        return bail;
    }

    public void setBail(String bail) {
        this.bail = bail;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(String roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloorsNumber() {
        return floorsNumber;
    }

    public void setFloorsNumber(String floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    public String getBuildingMaterial() {
        return buildingMaterial;
    }

    public void setBuildingMaterial(String buildingMaterial) {
        this.buildingMaterial = buildingMaterial;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public String getHeating() {
        return heating;
    }

    public void setHeating(String heating) {
        this.heating = heating;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getImagesUrls() {
        return imagesUrls;
    }

    public void setImagesUrls(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
