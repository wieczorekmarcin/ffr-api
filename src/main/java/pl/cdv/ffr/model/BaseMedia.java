package pl.cdv.ffr.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseMedia extends BaseEntity {

    private String value;
    private String rate;
    private String unit;

    public BaseMedia() {
    }

    public BaseMedia(String value, String rate, String unit) {
        this.value = value;
        this.rate = rate;
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
