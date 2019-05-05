package pl.cdv.ffr.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
public class Trash extends BaseMedia {

    private String unit = "os.";

    public Trash() {
    }

    public Trash(String status, String rate, String used, String date, String amount, String unit) {
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
