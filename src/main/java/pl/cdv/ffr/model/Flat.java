package pl.cdv.ffr.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FLAT")
public class Flat {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private List<String> equipment;

    @ElementCollection
    private List<String> security;

    @ElementCollection
    private List<String> media;

    @ElementCollection
    private List<String> additionalInformation;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "FLAT_IMAGE",
            joinColumns = {@JoinColumn(name = "FLAT_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")})
    private List<Image> images;

    @Enumerated(EnumType.STRING)
    @Column(name = "FLAT_STATUS")
    private FlatStatus flatStatus;

    public Flat() {
    }

    public Flat(String title, String description, String price, String bail, String surface, String roomsNumber, String street, String postCode, String city, String buildingType, String floor, String floorsNumber, String buildingMaterial, String windows, String heating, Date availableFrom, List<String> equipment, List<String> security, List<String> media, List<String> additionalInformation, List<Image> images, FlatStatus flatStatus) {
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
        this.equipment = equipment;
        this.security = security;
        this.media = media;
        this.additionalInformation = additionalInformation;
        this.images = images;
        this.flatStatus = flatStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }

    public List<String> getSecurity() {
        return security;
    }

    public void setSecurity(List<String> security) {
        this.security = security;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public FlatStatus getFlatStatus() {
        return flatStatus;
    }

    public void setFlatStatus(FlatStatus flatStatus) {
        this.flatStatus = flatStatus;
    }
}
