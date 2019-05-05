package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class ColdWater extends BaseMedia {

    private String unit = "m3";

    public ColdWater() {
    }

    public ColdWater(String status, String rate, String used, String date, String amount, String unit, String unit1) {
        super(status, rate, used, date, amount, unit);
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
