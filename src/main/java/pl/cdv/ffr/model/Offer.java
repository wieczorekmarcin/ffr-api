package pl.cdv.ffr.model;

import javax.persistence.*;

@Entity
@Table(name = "OFFER")
public class Offer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SHORT_DESCRIPTION")
    private String shortDescription;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "OFFER_FLAT",
            joinColumns = {@JoinColumn(name = "OFFER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "FLAT_ID", referencedColumnName = "ID")})
    private Flat flat;

    public Offer() {
    }

    public Offer(String title, String shortDescription, String description, Flat flat) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.flat = flat;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}