package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

@Entity
@DynamicUpdate
public class CommonPart extends BaseMedia {

    private String unit = "";

    public CommonPart() {
    }

    public CommonPart(String status, String rate, String used, String date, String amount, String unit, String unit1) {
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
