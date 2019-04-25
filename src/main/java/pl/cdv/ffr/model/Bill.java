package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Bill {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Electricity electricity;

    @OneToOne(cascade=CascadeType.ALL)
    private Heating heating;

    @OneToOne(cascade=CascadeType.ALL)
    private ColdWater coldWater;

    @OneToOne(cascade=CascadeType.ALL)
    private HotWater hotWater;

    @OneToOne(cascade=CascadeType.ALL)
    private Trash trash;

    @OneToOne(cascade=CascadeType.ALL)
    private Property property;

    public Bill() {
    }

    public Bill(Electricity electricity, Heating heating, ColdWater coldWater, HotWater hotWater, Trash trash, Property property) {
        this.electricity = electricity;
        this.heating = heating;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.trash = trash;
        this.property = property;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Electricity getElectricity() {
        return electricity;
    }

    public void setElectricity(Electricity electricity) {
        this.electricity = electricity;
    }

    public Heating getHeating() {
        return heating;
    }

    public void setHeating(Heating heating) {
        this.heating = heating;
    }

    public ColdWater getColdWater() {
        return coldWater;
    }

    public void setColdWater(ColdWater coldWater) {
        this.coldWater = coldWater;
    }

    public HotWater getHotWater() {
        return hotWater;
    }

    public void setHotWater(HotWater hotWater) {
        this.hotWater = hotWater;
    }

    public Trash getTrash() {
        return trash;
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
