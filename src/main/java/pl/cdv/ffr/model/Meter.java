package pl.cdv.ffr.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@JsonPropertyOrder({ "id", "currentMeterStatus", "media", "property" })
@DynamicUpdate
public class Meter {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Media media;

    private String currentMeterStatus;

    @OneToOne(cascade=CascadeType.ALL)
    private Property property;

    public Meter() {
    }

    public Meter(Media media, Property property, String currentMeterStatus) {
        this.media = media;
        this.property = property;
        this.currentMeterStatus = currentMeterStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getCurrentMeterStatus() {
        return currentMeterStatus;
    }

    public void setCurrentMeterStatus(String currentMeterStatus) {
        this.currentMeterStatus = currentMeterStatus;
    }
}
