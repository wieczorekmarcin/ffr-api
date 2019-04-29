package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Bill extends BaseEntity {

    @OneToOne(cascade=CascadeType.ALL)
    private Electricity electricity;

    @OneToOne(cascade=CascadeType.ALL)
    private Heating heating;

    @OneToOne(cascade=CascadeType.ALL)
    private ColdWater coldWater;

    @OneToOne(cascade=CascadeType.ALL)
    private HotWater hotWater;

    @OneToOne(cascade=CascadeType.ALL)
    private CommonPart commonPart;

    @OneToOne(cascade=CascadeType.ALL)
    private RepairFund repairFund;

    @OneToOne(cascade=CascadeType.ALL)
    private Trash trash;

    @OneToOne(cascade=CascadeType.MERGE)
    private Tenat tenat;

    public Bill() {
    }

    public Bill(Electricity electricity, Heating heating, ColdWater coldWater, HotWater hotWater, CommonPart commonPart, RepairFund repairFund, Trash trash, Tenat tenat) {
        this.electricity = electricity;
        this.heating = heating;
        this.coldWater = coldWater;
        this.hotWater = hotWater;
        this.commonPart = commonPart;
        this.repairFund = repairFund;
        this.trash = trash;
        this.tenat = tenat;
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

    public CommonPart getCommonPart() {
        return commonPart;
    }

    public void setCommonPart(CommonPart commonPart) {
        this.commonPart = commonPart;
    }

    public RepairFund getRepairFund() {
        return repairFund;
    }

    public void setRepairFund(RepairFund repairFund) {
        this.repairFund = repairFund;
    }

    public Trash getTrash() {
        return trash;
    }

    public void setTrash(Trash trash) {
        this.trash = trash;
    }

    public Tenat getTenat() {
        return tenat;
    }

    public void setTenat(Tenat tenat) {
        this.tenat = tenat;
    }
}
