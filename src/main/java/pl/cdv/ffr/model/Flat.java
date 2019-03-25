package pl.cdv.ffr.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FLAT")
public class Flat {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FLAT_NUMBER")
    private String flatNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
    name = "FLAT_IMAGE",
    joinColumns = {@JoinColumn(name = "FLAT_ID", referencedColumnName = "ID")},
    inverseJoinColumns = {@JoinColumn(name = "IMAGE_ID", referencedColumnName = "ID")})
    private List<Image> images;

    @Column(name = "PRICE_PER_YARD")
    private String pricePerYard;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "FOR_RENT")
    private boolean forRent;

    public Flat() {
    }

    public Flat(String flatNumber, List<Image> images, String pricePerYard, String title, String description, boolean forRent) {
        this.flatNumber = flatNumber;
        this.images = images;
        this.pricePerYard = pricePerYard;
        this.title = title;
        this.description = description;
        this.forRent = forRent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getPricePerYard() {
        return pricePerYard;
    }

    public void setPricePerYard(String pricePerYard) {
        this.pricePerYard = pricePerYard;
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

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }
}
